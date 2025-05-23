package com.webeye.backend.cosmetic.infrastructure.mapper;

import com.webeye.backend.cosmetic.domain.Cosmetic;
import com.webeye.backend.cosmetic.dto.response.CosmeticResponse;
import com.webeye.backend.product.domain.Product;

public class CosmeticMapper {

    public static Cosmetic toEntity(CosmeticResponse response, Product product) {
        return Cosmetic.builder().build();
    }

    public static CosmeticResponse toResponse (Cosmetic cosmetic) {
        return CosmeticResponse.builder()

                .build();
    }
}
