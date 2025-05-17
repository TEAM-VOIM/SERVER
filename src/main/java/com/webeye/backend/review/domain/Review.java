package com.webeye.backend.review.domain;

import com.webeye.backend.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @Column(name = "review_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double averageRating;

    @Column(nullable = false)
    private String positiveSummary;

    @Column(nullable = false)
    private String negativeSummary;

    @Column(nullable = false)
    private String keywords;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", unique = true)
//    private Product product;

    @Builder
    public Review(
            Double averageRating,
            String positiveSummary,
            String negativeSummary,
            String keywords
    ) {
        this.averageRating = averageRating;
        this.positiveSummary = positiveSummary;
        this.negativeSummary = negativeSummary;
        this.keywords = keywords;
    }

    public List<String> getKeywordList() {
        return Arrays.asList(this.keywords.split(","));
    }
}
