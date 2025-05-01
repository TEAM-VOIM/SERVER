package com.webeye.backend.review.application;

import com.webeye.backend.review.dto.request.ReviewSummaryRequest;
import com.webeye.backend.review.dto.response.ReviewResponse;
import com.webeye.backend.review.infrastructure.clovaX.ClovaXClient;
import com.webeye.backend.review.infrastructure.clovaX.model.ContentType;
import com.webeye.backend.review.infrastructure.clovaX.model.Role;
import com.webeye.backend.review.infrastructure.clovaX.dto.request.ClovaXContent;
import com.webeye.backend.review.infrastructure.clovaX.dto.request.ClovaXMessage;
import com.webeye.backend.review.infrastructure.clovaX.dto.request.ClovaXRequest;
import com.webeye.backend.review.infrastructure.clovaX.dto.response.ClovaXResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ClovaXClient clovaXClient;

    @Value("${clova.secret-key}")
    private String secretKey;

    @Value("${clova.request-id}")
    private String requestId;

    public ReviewResponse summarizeReview(ReviewSummaryRequest request) {
        String reviewText = String.join("\n", request.reviews());

        ClovaXRequest clovaXRequest = new ClovaXRequest(List.of(
                new ClovaXMessage(Role.SYSTEM, List.of(
                        new ClovaXContent(ContentType.TEXT,
                                "너는 사용자의 리뷰들을 읽고 다음 3가지로 요약하는 AI야:" +
                                     "1. 긍정적 내용 요약 (한 문장으로)" +
                                     "2. 부정적 내용 요약 (한 문장으로)" +
                                     "3. 대표 키워드 3개 추출 (한 문장으로, 콤마로 구분)" +

                                     "결과는 다음 형식으로 반환해:" +
                                     "긍정 리뷰: ..." +
                                     "부정 리뷰: ..." +
                                     "키워드: 키워드1, 키워드2, 키워드3"
                        )
                )),
                new ClovaXMessage(Role.USER, List.of(
                        new ClovaXContent(ContentType.TEXT, reviewText)
                ))
        ));
        ClovaXResponse clovaXResponse = clovaXClient.createReviewSummary("Bearer "+ secretKey, requestId, clovaXRequest);

        return parseResponse(clovaXResponse.result().message().content());
    }

    private ReviewResponse parseResponse(String content) {
        String[] lines = content.split("\n");

        String positive = "";
        String negative = "";
        List<String> keywords = new ArrayList<>();

        for (String line : lines) {
            if (line.startsWith("긍정 리뷰:")) {
                positive = line.replace("긍정 리뷰:", "").trim();
            } else if (line.startsWith("부정 리뷰:")) {
                negative = line.replace("부정 리뷰:", "").trim();
            } else if (line.startsWith("키워드:")) {
                String[] tokens = line.replace("키워드:", "").split(",");
                keywords = Arrays.stream(tokens).map(String::trim).collect(Collectors.toList());
            }
        }

        return new ReviewResponse(positive, negative, keywords);
    }
}
