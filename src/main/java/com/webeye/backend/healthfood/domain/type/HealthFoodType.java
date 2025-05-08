package com.webeye.backend.healthfood.domain.type;

import lombok.Getter;

@Getter
public enum HealthFoodType {
    IMMUNE("면역기능"),
    SKIN("피부건강"),
    BLOOD("혈액건강"),
    BODY_FAT("체지방 감소"),
    BLOOD_SUGAR("혈당조절"),
    MEMORY("기억력"),
    ANTIOXIDANT("항산화"),
    GUT("장건강"),
    LIVER("간건강"),
    EYE("눈건강"),
    JOINT("관절건강"),
    SLEEP("수면건강"),
    STRESS_FATIGUE("스트레스/피로개선"),
    MENOPAUSE("갱년기건강"),
    PROSTATE("전립선건강"),
    URINARY("요로건강"),
    ENERGY("에너지대사"),
    BONE("뼈건강"),
    MUSCLE("근력/운동수행능력"),
    COGNITION("인지기능"),
    STOMACH("위건강"),
    ORAL("구강건강"),
    HAIR("모발건강"),
    GROWTH("어린이 성장"),
    BLOOD_PRESSURE("혈압"),
    URINATION("배뇨건강"),
    FOLATE("엽산대사"),
    NOSE("코건강"),
    MALE_HEALTH("남성건강"),
    ELECTROLYTE("전해질 균형"),
    DIETARY_FIBER("식이섬유"),
    ESSENTIAL_FATTY_ACID("필수지방산");

    private final String description;

    HealthFoodType(String description) {
        this.description = description;
    }
}
