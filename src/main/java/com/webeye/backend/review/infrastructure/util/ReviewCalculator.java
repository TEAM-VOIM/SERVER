package com.webeye.backend.review.infrastructure.util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ReviewCalculator {

    public static double calculateAverageRating(Map<String, Integer> ratings, int totalCount) {
        Map<String, Integer> ratingPoint = Map.of(
                "최고", 5,
                "좋음", 4,
                "보통", 3,
                "별로", 2,
                "나쁨", 1
        );
        int totalScore = ratings.entrySet().stream()
                .mapToInt(entry -> {
                    Integer rating = ratingPoint.get(entry.getKey());
                    Integer score = entry.getValue();

                    return rating != null ? rating * score : 0;
                })
                .sum();

        log.info("[평균 별점 계산] ratings: {}", ratings);
        log.info("[평균 별점 계산] totalCount: {}", totalCount);

        return totalCount == 0 ? 0.0 : Math.round((totalScore / (double) totalCount) * 100.0) / 100.0;
    }

    public static Map<String, Integer> convertToRatingMap(List<Integer> ratingsList) {
        List<String> keys = List.of("최고", "좋음", "보통", "별로", "나쁨");
        Map<String, Integer> ratingMap = new LinkedHashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            ratingMap.put(keys.get(i), i < ratingsList.size() ? ratingsList.get(i) : 0);
        }
        return ratingMap;
    }
}
