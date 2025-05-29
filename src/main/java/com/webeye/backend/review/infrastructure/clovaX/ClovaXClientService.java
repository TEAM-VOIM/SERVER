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

        try {
            ClovaXResponse clovaXResponse = clovaXClient.createReviewSummary("Bearer "+ secretKey, requestId, request);

            log.info("reviewText 길이: {}", reviewText.length());
            log.info("[Clova 요약 응답] content = {}", clovaXResponse.result().message().content());

            return parseResponse(clovaXResponse.result().message().content(), totalCount, averageRating);
        } catch (Exception e) {
            log.error("Clova 요약 실패: {}", e.getMessage(), e);
            throw new RuntimeException("Clova 요약 중 오류 발생", e);
        }
    }

    private ClovaXRequest buildReviewSummaryPrompt(String inputText) {
        String prompt = """
        너는 사용자의 리뷰 데이터를 분석하여 아래 3가지 항목으로 요약하는 AI야:
        **번호 및 글머리 기호를 붙이지 말고**, 정확한 형식을 지켜줘.
        '~며', '~고', '~하며' 같은 연결어도 절대 쓰지 마. 문장이 길어지지 않게 최대한 간결하게 써.
        
        1. 긍정적인 내용 요약 (한 문장으로, 마침표로 구분, 서로 독립된 짧은 문장으로 작성)
        2. 부정적인 내용 요약 (한 문장으로, 마침표로 구분, 서로 독립된 짧은 문장으로 작성)
        3. 대표 키워드 3개 추출 (한 문장으로, 콤마로 구분)

        결과는 반드시 다음 형식으로 반환해:
        긍정 리뷰: 긍정 리뷰 1, 긍정 리뷰 2, 긍정 리뷰 3
        부정 리뷰: 부정 리뷰 1, 부정 리뷰 2, 부정 리뷰 3
        키워드: 키워드 1, 키워드 2, 키워드 3

        예시:
        긍정 리뷰: 맛있다는 평가가 많습니다, 달콤하다는 평가가 많습니다
        부정 리뷰: 배송이 느리다는 평가가 많습니다, 포장이 별로라는 평가가 많습니다
        키워드: 맛있어요, 신선해요, 배송이 느려요
        
        주의사항:
        1. 반드시 **예시의 형식**과 동일하게 반환해.
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

            if (line.startsWith("긍정 리뷰:")) {
                String text = line.replace("긍정 리뷰:", "").trim();

                positiveReviews = Arrays.stream(text.split("[.]"))
                        .map(String::trim)
                        .filter(s -> !s.isBlank())
                        .limit(3)
                        .toList();
            } else if (line.startsWith("부정 리뷰:")) {
                String text = line.replace("부정 리뷰:", "").trim();

                negativeReviews = Arrays.stream(text.split("[.]"))
                        .map(String::trim)
                        .filter(s -> !s.isBlank())
                        .limit(3)
                        .toList();
            } else if (line.startsWith("키워드:")) {
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
