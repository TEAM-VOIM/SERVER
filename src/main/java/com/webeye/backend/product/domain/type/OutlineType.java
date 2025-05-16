package com.webeye.backend.product.domain.type;

import lombok.Getter;

@Getter
public enum OutlineType {
    MAIN("Analyze the product description image I send and provide information about the main ingredients, materials, functions, and components of the product. If it is a food item, nutritional information is required. Respond in Korean. (e.g., ingredients/nutritional content, material, functionality, components, country of origin, manufacturer)"),
    USAGE("Analyze the product description image I send and provide information about how to use (consume/assemble/install/utilize) the product and the intended user. Respond in Korean. (e.g., consumption method, usage steps, recommended users, age group, installation method)"),
    WARNING("Analyze the product description image I send and provide information about storage methods, expiration date, safety precautions, allergies, cleaning, etc. If it is a food item, allergy information is required. Respond in Korean. (e.g., storage method, avoid direct sunlight, expiration date, washing, prohibitions)"),
    SPECS("Analyze the product description image I send and provide information about the product’s color, size, weight, capacity, compatibility, and purchasing options. Respond in Korean. (e.g., size, weight, capacity, size options, color, option configuration, coverage)"),
    CERTIFICATION("Analyze the product description image I send and provide information about the product’s certifications, after-sales service, packaging, brand, delivery, and marks. Respond in Korean. (e.g., KC certification, GMP, vegan, KFDA, free A/S, packaging)")
    ;

    private final String prompt;

    OutlineType(String prompt) {
        this.prompt = prompt;
    }
}
