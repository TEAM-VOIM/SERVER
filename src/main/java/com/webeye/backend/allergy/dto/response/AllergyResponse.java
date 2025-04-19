package com.webeye.backend.allergy.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record AllergyResponse (
    List<String> ingredients
) {
}