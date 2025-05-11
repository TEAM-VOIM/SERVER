package com.webeye.backend.healthfood.dto;

import com.webeye.backend.healthfood.domain.type.HealthFoodType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
@Schema(description = "건강 기능 식품 분석")
public record HealthFoodAiResponse(
        boolean immune,
        boolean skin,
        boolean blood,
        boolean bodyFat,
        boolean bloodSugar,
        boolean memory,
        boolean antioxidant,
        boolean gut,
        boolean liver,
        boolean eye,
        boolean joint,
        boolean sleep,
        boolean stressFatigue,
        boolean menopause,
        boolean prostate,
        boolean urinary,
        boolean energy,
        boolean bone,
        boolean muscle,
        boolean cognition,
        boolean stomach,
        boolean oral,
        boolean hair,
        boolean growth,
        boolean bloodPressure,
        boolean urination,
        boolean folate,
        boolean nose,
        boolean maleHealth,
        boolean electrolyte,
        boolean dietaryFiber,
        boolean essentialFattyAcid
) {
    public List<HealthFoodType> getHealthFoodTypes() {
        List<HealthFoodType> healthFoodTypes = new ArrayList<>();

        if (immune) healthFoodTypes.add(HealthFoodType.IMMUNE);
        if (skin) healthFoodTypes.add(HealthFoodType.SKIN);
        if (blood) healthFoodTypes.add(HealthFoodType.BLOOD);
        if (bodyFat) healthFoodTypes.add(HealthFoodType.BODY_FAT);
        if (bloodPressure) healthFoodTypes.add(HealthFoodType.BLOOD_PRESSURE);
        if (memory) healthFoodTypes.add(HealthFoodType.MEMORY);
        if (antioxidant) healthFoodTypes.add(HealthFoodType.ANTIOXIDANT);
        if (gut) healthFoodTypes.add(HealthFoodType.GUT);
        if (liver) healthFoodTypes.add(HealthFoodType.LIVER);
        if (eye) healthFoodTypes.add(HealthFoodType.EYE);
        if (joint) healthFoodTypes.add(HealthFoodType.JOINT);
        if (sleep) healthFoodTypes.add(HealthFoodType.SLEEP);
        if (stressFatigue) healthFoodTypes.add(HealthFoodType.STRESS_FATIGUE);
        if (menopause) healthFoodTypes.add(HealthFoodType.MENOPAUSE);
        if (prostate) healthFoodTypes.add(HealthFoodType.PROSTATE);
        if (urinary) healthFoodTypes.add(HealthFoodType.URINARY);
        if (energy) healthFoodTypes.add(HealthFoodType.ENERGY);
        if (bone) healthFoodTypes.add(HealthFoodType.BONE);
        if (muscle) healthFoodTypes.add(HealthFoodType.MUSCLE);
        if (cognition) healthFoodTypes.add(HealthFoodType.COGNITION);
        if (stomach) healthFoodTypes.add(HealthFoodType.STOMACH);
        if (oral) healthFoodTypes.add(HealthFoodType.ORAL);
        if (hair) healthFoodTypes.add(HealthFoodType.HAIR);
        if (growth) healthFoodTypes.add(HealthFoodType.GROWTH);
        if (bloodPressure) healthFoodTypes.add(HealthFoodType.BLOOD_PRESSURE);
        if (urination) healthFoodTypes.add(HealthFoodType.URINATION);
        if (folate) healthFoodTypes.add(HealthFoodType.FOLATE);
        if (nose) healthFoodTypes.add(HealthFoodType.NOSE);
        if (maleHealth) healthFoodTypes.add(HealthFoodType.MALE_HEALTH);
        if (electrolyte) healthFoodTypes.add(HealthFoodType.ELECTROLYTE);
        if (dietaryFiber) healthFoodTypes.add(HealthFoodType.DIETARY_FIBER);
        if (essentialFattyAcid) healthFoodTypes.add(HealthFoodType.ESSENTIAL_FATTY_ACID);

        return healthFoodTypes;
    }
}
