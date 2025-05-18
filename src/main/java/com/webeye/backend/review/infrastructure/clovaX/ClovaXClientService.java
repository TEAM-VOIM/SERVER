package com.webeye.backend.review.infrastructure.clovaX;

import com.webeye.backend.review.dto.response.ReviewSummaryResponse;
import com.webeye.backend.review.infrastructure.clovaX.dto.request.ClovaXContent;
import com.webeye.backend.review.infrastructure.clovaX.dto.request.ClovaXMessage;
import com.webeye.backend.review.infrastructure.clovaX.dto.request.ClovaXRequest;
import com.webeye.backend.review.infrastructure.clovaX.dto.response.ClovaXResponse;
import com.webeye.backend.review.infrastructure.clovaX.model.ContentType;
import com.webeye.backend.review.infrastructure.clovaX.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClovaXClientService {

    private final ClovaXClient clovaXClient;

    @Value("${clova.secret-key}")
    private String secretKey;

    @Value("${clova.request-id}")
    private String requestId;

    public ReviewSummaryResponse summarizeReviewText(Map<String, Map<String, Integer>> reviewText) {
        String inputText = convertReviewMapToText(reviewText);

        double averageRating = 0.0;
        if (reviewText.containsKey("별점")) {
            averageRating = calculateAverageRating(reviewText.get("별점"));
        }

        ClovaXRequest clovaXRequest = new ClovaXRequest(List.of(
                new ClovaXMessage(Role.SYSTEM, List.of(
                        new ClovaXContent(ContentType.TEXT,
                                """
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
                                
                                주의:
                                1. 긍정 리뷰와 부정 리뷰는 최대 3개까지 표시할 것
                                2. 제공된 입력 내용을 기반으로만 판단하고, 임의로 생성하지 말 것
                                3. 별점과 관련된 내용은 요약에 포함하지 말 것
                                4. 각 항목에서 수치가 가장 높은 선택지만 참고할 것
                                """
                        )
                )),
                new ClovaXMessage(Role.USER, List.of(
                        new ClovaXContent(ContentType.TEXT, inputText)
                ))
        ));
        ClovaXResponse clovaXResponse = clovaXClient.createReviewSummary("Bearer "+ secretKey, requestId, clovaXRequest);

        return parseResponse(clovaXResponse.result().message().content(), averageRating);
    }

    private ReviewSummaryResponse parseResponse(String content, double averageRating) {
        String[] lines = content.split("\n");

        List<String> positiveReviews = new ArrayList<>();
        List<String> negativeReviews = new ArrayList<>();
        List<String> keywords = new ArrayList<>();

        for (String line : lines) {
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
        return new ReviewSummaryResponse(averageRating, positiveReviews, negativeReviews, keywords);
    }

    private String convertReviewMapToText(Map<String, Map<String, Integer>> reviewMap) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Map<String, Integer>> entry : reviewMap.entrySet()) {
            sb.append("[").append(entry.getKey()).append("]\n");
            for (Map.Entry<String, Integer> option : entry.getValue().entrySet()) {
                sb.append("- ").append(option.getKey()).append(": ").append(option.getValue()).append("%\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private double calculateAverageRating(Map<String, Integer> ratings) {
        Map<String, Integer> ratingPoint = Map.of(
                "최고", 5,
                "좋음", 4,
                "보통", 3,
                "별로", 2,
                "나쁨", 1
        );
        int totalScore = 0;
        int totalPercent = 0;

        for (Map.Entry<String, Integer> entry : ratings.entrySet()) {
            Integer point = ratingPoint.get(entry.getKey());
            Integer percent = entry.getValue();

            if (point != null) {
                totalScore += point * percent;
                totalPercent += percent;
            }
        }
        return totalPercent == 0 ? 0.0 : Math.round((totalScore / (double) totalPercent) * 100.0) / 100.0;
    }
}
