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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        ClovaXRequest clovaXRequest = new ClovaXRequest(List.of(
                new ClovaXMessage(Role.SYSTEM, List.of(
                        new ClovaXContent(ContentType.TEXT,
                                "너는 사용자의 리뷰들을 읽고 다음 3가지로 요약하는 AI야:" +
                                     "1. 긍정적 내용 요약 (한 문장으로)" +
                                     "2. 부정적 내용 요약 (한 문장으로)" +
                                     "3. 대표 키워드 3개 추출 (한 문장으로, 콤마로 구분)" +

                                     "결과는 정확하게 다음 형식으로 반환해:" +
                                     "긍정 리뷰: ..." +
                                     "부정 리뷰: ..." +
                                     "키워드: 키워드 1, 키워드 2, 키워드 3" +

                                     "예시:" +
                                     "긍정 리뷰: 맛있다는 평가가 많습니다." +
                                     "부정 리뷰: 배송이 느리다는 평가가 많습니다." +
                                     "키워드: 맛있어요, 신선해요, 배송이 느려요"
                        )
                )),
                new ClovaXMessage(Role.USER, List.of(
                        new ClovaXContent(ContentType.TEXT, inputText)
                ))
        ));
        ClovaXResponse clovaXResponse = clovaXClient.createReviewSummary("Bearer "+ secretKey, requestId, clovaXRequest);

        return parseResponse(clovaXResponse.result().message().content());
    }

    private ReviewSummaryResponse parseResponse(String content) {
        String[] lines = content.split("\n");

        String positive = "";
        String negative = "";
        List<String> keywords = new ArrayList<>();

        for (String line : lines) {
            if (line != null && line.startsWith("긍정 리뷰:")) {
                positive = line.replace("긍정 리뷰:", "").trim();
            } else if (line != null && line.startsWith("부정 리뷰:")) {
                negative = line.replace("부정 리뷰:", "").trim();
            } else if (line != null && line.startsWith("키워드:")) {
                String[] tokens = line.replace("키워드:", "").split(",");
                keywords = Arrays.stream(tokens).map(String::trim).collect(Collectors.toList());
            }
        }

        return new ReviewSummaryResponse(positive, negative, keywords);
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

}
