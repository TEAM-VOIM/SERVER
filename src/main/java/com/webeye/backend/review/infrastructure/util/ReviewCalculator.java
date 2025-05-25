package com.webeye.backend.review.infrastructure.util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ReviewCalculator {

    public static final List<String> RATING_LABELS = List.of("최고", "좋음", "보통", "별로", "나쁨");

    public static final Map<String, Integer> RATING_POINTS = Map.of(
            "최고", 5,
            "좋음", 4,
            "보통", 3,
            "별로", 2,
            "나쁨", 1
    );

    public static double calculateAverageRating(Map<String, Integer> ratings, int totalCount) {
        int totalScore = ratings.entrySet().stream()
                .mapToInt(entry -> {
                    Integer rating = RATING_POINTS.get(entry.getKey());
                    Integer score = entry.getValue();
                    return rating != null ? rating * score : 0;
                })
                .sum();

        log.info("[평균 별점 계산] ratings: {}", ratings);
        log.info("[평균 별점 계산] totalCount: {}", totalCount);

        return totalCount == 0 ? 0.0 : Math.round((totalScore / (double) totalCount) * 100.0) / 100.0;
    }

    public static Map<String, Integer> convertToRatingMap(List<Integer> ratingsList) {
        Map<String, Integer> ratingMap = new LinkedHashMap<>();
        for (int i = 0; i < RATING_LABELS.size(); i++) {
            ratingMap.put(RATING_LABELS.get(i), i < ratingsList.size() ? ratingsList.get(i) : 0);
        }
        return ratingMap;
    }
}
