package com.webeye.backend.review.infrastructure.persistence;

import com.webeye.backend.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
