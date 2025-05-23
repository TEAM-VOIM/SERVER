package com.webeye.backend.cosmetic.domain.type;

import lombok.Getter;

@Getter
public enum IngredientType {
    AVOBENZONE("아보벤존"),
    ISOPROPYL_ALCOHOL("이소프로필 알코올"),
    SODIUM_LAURYL_SULFATE("소듐 라우릴/라우레스 설페이트 (SLS, SLES)"),
    TRIETHANOLAMINE("트리에탄올아민"),
    POLYETHYLENE_GLYCOL("폴리에틸렌 글라이콜 (PEGs)"),
    SYNTHETIC_COLORANT("합성 착색료"),
    ISOPROPYL_METHYLPHENOL("이소프로필 메틸페놀"),
    SORBIC_ACID("소르빅 애씨드"),
    HORMONE("호르몬류"),
    DIBUTYL_HYDROXYTOLUENE("디부틸 하이드록시 톨루엔 (BHT)"),
    PARABENS("파라벤류 (Methyl-, Ethyl-, Propylparaben 등)"),
    TRICLOSAN("트리클로산"),
    BUTYLATED_HYDROXYANISOLE("부틸 하이드록시아니솔 (BHA)"),
    OXYBENZONE("옥시벤존"),
    IMIDAZOLIDINYL_UREA("이미다졸리디닐 우레아, 디아졸리디닐 우레아, DMDM 하이단토인 등"),
    MINERAL_OIL("미네랄 오일, 파라핀오일"),
    THYMOL("티몰"),
    TRIISOPROPANOLAMINE("트라이아이소프로판올아민"),
    SYNTHETIC_FRAGRANCE("인공 향료 (Synthetic Fragrance, Parfum)"),
    PHENOXYETHANOL("페녹시에탄올");

    private final String description;

    IngredientType(String description) {
        this.description = description;
    }
}
