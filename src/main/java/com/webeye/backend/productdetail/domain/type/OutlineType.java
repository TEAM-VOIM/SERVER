package com.webeye.backend.productdetail.domain.type;

import lombok.Getter;

@Getter
public enum OutlineType {
    MAIN("Analyze the product description image I send and provide information about the main ingredients, materials, functions, and components of the product. If it is a food item, nutritional information and Manufacturer and Distributor(Business Name and Address) must be included without exception. (e.g., ingredients/nutritional content, country of origin, material, functionality, components, manufacturer)"),
    USAGE("Analyze the product description image I send and provide information about how to use (consume/assemble/install/utilize) the product and the intended user. (e.g., consumption method, usage steps, recommended users, age group, installation method)"),
    WARNING("Analyze the product description image I send and provide information about storage methods, expiration date, safety precautions, allergies, cleaning, etc. If it is a food item, allergy information is required. (e.g., storage method, avoid direct sunlight, expiration date, washing, prohibitions)"),
    SPECS("Analyze the product description image I send and provide information about the product’s color, size, weight, capacity, compatibility, and purchasing options. (e.g., size, weight, capacity, size options, color, option configuration, coverage)")
    ;

    private final String prompt;

    OutlineType(String prompt) {
        this.prompt = prompt;
    }
}
