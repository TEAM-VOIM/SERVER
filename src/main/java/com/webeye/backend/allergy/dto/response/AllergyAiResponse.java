package com.webeye.backend.allergy.dto.response;

import com.webeye.backend.allergy.type.AllergyType;
import lombok.Builder;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "제품 알레르기 유발 성분 응답")
@Builder
public record AllergyAiResponse(
        @Schema(description = "계란 함유 여부")
        boolean egg,

        @Schema(description = "우유 함유 여부")
        boolean milk,

        @Schema(description = "메밀 함유 여부")
        boolean buckwheat,

        @Schema(description = "땅콩 함유 여부")
        boolean peanut,

        @Schema(description = "대두 함유 여부")
        boolean soybean,

        @Schema(description = "밀 함유 여부")
        boolean wheat,

        @Schema(description = "잣 함유 여부")
        boolean pineNut,

        @Schema(description = "호두 함유 여부")
        boolean walnut,

        @Schema(description = "게 함유 여부")
        boolean crab,

        @Schema(description = "새우 함유 여부")
        boolean shrimp,

        @Schema(description = "오징어 함유 여부")
        boolean squid,

        @Schema(description = "고등어 함유 여부")
        boolean mackerel,

        @Schema(description = "조개 함유 여부")
        boolean shellfish,

        @Schema(description = "복숭아 함유 여부")
        boolean peach,

        @Schema(description = "토마토 함유 여부")
        boolean tomato,

        @Schema(description = "닭고기 함유 여부")
        boolean chicken,

        @Schema(description = "돼지고기 함유 여부")
        boolean pork,

        @Schema(description = "쇠고기 함유 여부")
        boolean beef,

        @Schema(description = "아황산류 함유 여부")
        boolean sulfite
) {
        public List<AllergyType> getAllergyTypes() {
                List<AllergyType> result = new ArrayList<>();
                if (egg) result.add(AllergyType.EGG);
                if (milk) result.add(AllergyType.MILK);
                if (buckwheat) result.add(AllergyType.BUCKWHEAT);
                if (peanut) result.add(AllergyType.PEANUT);
                if (soybean) result.add(AllergyType.SOYBEAN);
                if (wheat) result.add(AllergyType.WHEAT);
                if (pineNut) result.add(AllergyType.PINE_NUT);
                if (walnut) result.add(AllergyType.WALNUT);
                if (crab) result.add(AllergyType.CRAB);
                if (shrimp) result.add(AllergyType.SHRIMP);
                if (squid) result.add(AllergyType.SQUID);
                if (mackerel) result.add(AllergyType.MACKEREL);
                if (shellfish) result.add(AllergyType.SHELLFISH);
                if (peach) result.add(AllergyType.PEACH);
                if (tomato) result.add(AllergyType.TOMATO);
                if (chicken) result.add(AllergyType.CHICKEN);
                if (pork) result.add(AllergyType.PORK);
                if (beef) result.add(AllergyType.BEEF);
                if (sulfite) result.add(AllergyType.SULFITE);
                return result;
        }
}