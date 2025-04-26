package com.webeye.backend.cosmetic.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "화장품 주의 성분")
public record CosmeticResponse(
        boolean amylCinnamal,
        boolean benzylAlcohol,
        boolean cinnamylAlcohol,
        boolean citral,
        boolean eugenol,
        boolean hydroxycitronellal,
        boolean isoeugenol,
        boolean amylCinnamylAlcohol,
        boolean benzylSalicylate,
        boolean cinnamal,
        boolean coumarin,
        boolean geraniol,
        boolean hicc,
        boolean anisylAlcohol,
        boolean benzylCinnamate
) {
}
