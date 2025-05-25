package com.webeye.backend.cosmetic.infrastructure.mapper;

import com.webeye.backend.cosmetic.domain.CosmeticIngredient;
import com.webeye.backend.cosmetic.domain.Ingredient;
import com.webeye.backend.cosmetic.domain.type.IngredientType;
import com.webeye.backend.cosmetic.dto.response.CosmeticResponse;
import com.webeye.backend.product.domain.Product;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CosmeticIngredientMapper {

    public static CosmeticIngredient toEntity(Product product, Ingredient ingredient) {
        return CosmeticIngredient.builder()
                .product(product)
                .ingredient(ingredient)
                .build();
    }

    public static CosmeticResponse toResponse(List<CosmeticIngredient> ingredients) {
        Set<IngredientType> present = ingredients.stream()
                .map(ci -> ci.getIngredient().getIngredientType())
                .collect(Collectors.toSet());

        return CosmeticResponse.builder()
                .avobenzone(present.contains(IngredientType.AVOBENZONE))
                .isopropylAlcohol(present.contains(IngredientType.ISOPROPYL_ALCOHOL))
                .sodiumLaurylSulfate(present.contains(IngredientType.SODIUM_LAURYL_SULFATE))
                .triethanolamine(present.contains(IngredientType.TRIETHANOLAMINE))
                .polyethyleneGlycol(present.contains(IngredientType.POLYETHYLENE_GLYCOL))
                .syntheticColorant(present.contains(IngredientType.SYNTHETIC_COLORANT))
                .isopropylMethylphenol(present.contains(IngredientType.ISOPROPYL_METHYLPHENOL))
                .sorbicAcid(present.contains(IngredientType.SORBIC_ACID))
                .hormone(present.contains(IngredientType.HORMONE))
                .dibutylHydroxyToluene(present.contains(IngredientType.DIBUTYL_HYDROXYTOLUENE))
                .parabens(present.contains(IngredientType.PARABENS))
                .triclosan(present.contains(IngredientType.TRICLOSAN))
                .butylatedHydroxyanisole(present.contains(IngredientType.BUTYLATED_HYDROXYANISOLE))
                .oxybenzone(present.contains(IngredientType.OXYBENZONE))
                .imidazolidinylUrea(present.contains(IngredientType.IMIDAZOLIDINYL_UREA))
                .mineralOil(present.contains(IngredientType.MINERAL_OIL))
                .thymol(present.contains(IngredientType.THYMOL))
                .triisopropanolamine(present.contains(IngredientType.TRIISOPROPANOLAMINE))
                .syntheticFragrance(present.contains(IngredientType.SYNTHETIC_FRAGRANCE))
                .phenoxyethanol(present.contains(IngredientType.PHENOXYETHANOL))
                .build();
    }
}
