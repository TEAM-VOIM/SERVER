package com.webeye.backend.cosmetic.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "화장품 주의 성분")
public record CosmeticResponse(
        @Schema(description = "아밀신남알")
        boolean amylCinnamal,
        @Schema(description = "벤질알코올")
        boolean benzylAlcohol,
        @Schema(description = "신나밀알코올")
        boolean cinnamylAlcohol,
        @Schema(description = "시트랄")
        boolean citral,
        @Schema(description = "유제놀")
        boolean eugenol,
        @Schema(description = "하이드록시시트로넬알")
        boolean hydroxycitronellal,
        @Schema(description = "이소유제놀")
        boolean isoeugenol,
        @Schema(description = "아밀신나밀알코올")
        boolean amylCinnamylAlcohol,
        @Schema(description = "벤질살리실레이트")
        boolean benzylSalicylate,
        @Schema(description = "신남알")
        boolean cinnamal,
        @Schema(description = "쿠마린")
        boolean coumarin,
        @Schema(description = "제라니올")
        boolean geraniol,
        @Schema(description = "하이드록시이소헥실3-사이클로헥센카복스알데하이드")
        boolean hicc,
        @Schema(description = "아니스에탄올")
        boolean anisylAlcohol,
        @Schema(description = "벤질신나메이트")
        boolean benzylCinnamate
) {
}
