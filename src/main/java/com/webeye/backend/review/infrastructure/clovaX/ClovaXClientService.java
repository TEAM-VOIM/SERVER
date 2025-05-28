package com.webeye.backend.review.infrastructure.clovaX;

import com.webeye.backend.review.infrastructure.util.ReviewCalculator;
import com.webeye.backend.review.dto.response.ReviewSummaryResponse;
import com.webeye.backend.review.infrastructure.clovaX.dto.request.ClovaXContent;
import com.webeye.backend.review.infrastructure.clovaX.dto.request.ClovaXMessage;
import com.webeye.backend.review.infrastructure.clovaX.dto.request.ClovaXRequest;
import com.webeye.backend.review.infrastructure.clovaX.dto.response.ClovaXResponse;
import com.webeye.backend.review.infrastructure.clovaX.model.ContentType;
import com.webeye.backend.review.infrastructure.clovaX.model.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClovaXClientService {

    private final ClovaXClient clovaXClient;

    @Value("${clova.secret-key}")
    private String secretKey;

    @Value("${clova.request-id}")
    private String requestId;

    public ReviewSummaryResponse summarizeReviewText(String reviewText, Map<String, Integer> ratingMap, int totalCount) {
        double averageRating = ReviewCalculator.calculateAverageRating(ratingMap, totalCount);

        ClovaXRequest request = buildReviewSummaryPrompt(reviewText);

        ClovaXResponse clovaXResponse = clovaXClient.createReviewSummary("Bearer "+ secretKey, requestId, request);

        String content = clovaXResponse.result().message().content();
        log.info("[Clova 요약 응답] content = {}", content);

        return parseResponse(clovaXResponse.result().message().content(), totalCount, averageRating);
    }

    private ClovaXRequest buildReviewSummaryPrompt(String inputText) {
        String prompt = """
        너는 사용자의 리뷰 데이터를 분석하여 아래 3가지 항목으로 요약하는 AI야:
        1. 긍정적인 내용 요약 (한 문장으로, 콤마로 구분)
        2. 부정적인 내용 요약 (한 문장으로, 콤마로 구분)
        3. 대표 키워드 3개 추출 (한 문장으로, 콤마로 구분)

        결과는 정확하게 다음 형식으로 반환해:
        긍정 리뷰: 긍정 리뷰 1, 긍정 리뷰 2, 긍정 리뷰 3
        부정 리뷰: 부정 리뷰 1, 부정 리뷰 2, 부정 리뷰 3
        키워드: 키워드 1, 키워드 2, 키워드 3

        예시:
        긍정 리뷰: 맛있다는 평가가 많습니다, 달콤하다는 평가가 많습니다
        부정 리뷰: 배송이 느리다는 평가가 많습니다, 포장이 별로라는 평가가 많습니다
        키워드: 맛있어요, 신선해요, 배송이 느려요
        """;

        return new ClovaXRequest(List.of(
                new ClovaXMessage(Role.SYSTEM, List.of(
                        new ClovaXContent(ContentType.TEXT, prompt)
                )),
                new ClovaXMessage(Role.USER, List.of(
                        new ClovaXContent(ContentType.TEXT, inputText)
                ))
        ));
    }

    private ReviewSummaryResponse parseResponse(String content, int totalCount, double averageRating) {
        String[] lines = content.split("\n");

        List<String> positiveReviews = new ArrayList<>();
        List<String> negativeReviews = new ArrayList<>();
        List<String> keywords = new ArrayList<>();

        for (String rawLine : lines) {
            String line = rawLine.replaceAll("\\*\\*", "").trim();

            if (line != null && line.startsWith("긍정 리뷰:")) {
                String text = line.replace("긍정 리뷰:", "").trim();
                positiveReviews = Arrays.stream(text.split("[.,]"))
                        .map(String::trim)
                        .filter(s -> !s.isBlank())
                        .limit(3)
                        .toList();
            } else if (line != null && line.startsWith("부정 리뷰:")) {
                String text = line.replace("부정 리뷰:", "").trim();
                negativeReviews = Arrays.stream(text.split("[.,]"))
                        .map(String::trim)
                        .filter(s -> !s.isBlank())
                        .limit(3)
                        .toList();
            } else if (line != null && line.startsWith("키워드:")) {
                String[] tokens = line.replace("키워드:", "").split(",");
                keywords = Arrays.stream(tokens)
                        .map(String::trim)
                        .filter(s -> !s.isBlank())
                        .limit(3)
                        .toList();
            }
        }
        return new ReviewSummaryResponse(totalCount, averageRating, positiveReviews, negativeReviews, keywords);
    }
}
