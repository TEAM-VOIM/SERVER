package com.webeye.backend.cosmetic.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "화장품 주의 성분")
@Builder
public record CosmeticResponse(
        @Schema(description = "아보벤존 (Avobenzone)")
        boolean avobenzone,

        @Schema(description = "이소프로필 알코올 (Isopropyl Alcohol)")
        boolean isopropylAlcohol,

        @Schema(description = "소듐 라우릴/라우레스 설페이트 (SLS, SLES)")
        boolean sodiumLaurylSulfate,

        @Schema(description = "트리에탄올아민 (Triethanolamine, TEA)")
        boolean triethanolamine,

        @Schema(description = "폴리에틸렌 글라이콜 (Polyethylene Glycol, PEGs)")
        boolean polyethyleneGlycol,

        @Schema(description = "합성 착색료")
        boolean syntheticColorant,

        @Schema(description = "이소프로필 메틸페놀 (Isopropyl Methylphenol, IPMP)")
        boolean isopropylMethylphenol,

        @Schema(description = "소르빅 애씨드 (Sorbic Acid)")
        boolean sorbicAcid,

        @Schema(description = "호르몬류")
        boolean hormone,

        @Schema(description = "디부틸 하이드록시 톨루엔 (Dibutyl Hydroxy Toluene, BHT)")
        boolean dibutylHydroxyToluene,

        @Schema(description = "파라벤류 (Methyl-, Ethyl-, Propylparaben 등)")
        boolean parabens,

        @Schema(description = "트리클로산 (Triclosan)")
        boolean triclosan,

        @Schema(description = "부틸 하이드록시아니솔 (Butylated Hydroxyanisole, BHA)")
        boolean butylatedHydroxyanisole,

        @Schema(description = "옥시벤존 (Oxybenzone, Benzophenone-3)")
        boolean oxybenzone,

        @Schema(description = "포름알데히드 유도체 (Imidazolidinyl Urea, Diazolidinyl Urea, DMDM Hydantoin 등)")
        boolean imidazolidinylUrea,

        @Schema(description = "미네랄 오일 (Mineral Oil, Liquid Paraffin 등)")
        boolean mineralOil,

        @Schema(description = "티몰 (Thymol)")
        boolean thymol,

        @Schema(description = "트라이아이소프로판올아민 (Triisopropanolamine)")
        boolean triisopropanolamine,

        @Schema(description = "인공 향료 (Synthetic Fragrance, Parfum)")
        boolean syntheticFragrance,

        @Schema(description = "페녹시에탄올 (Phenoxyethanol)")
        boolean phenoxyethanol
) {
}
