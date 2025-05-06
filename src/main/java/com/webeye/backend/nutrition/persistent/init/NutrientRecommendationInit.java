package com.webeye.backend.nutrition.persistent.init;

import com.webeye.backend.global.error.BusinessException;
import com.webeye.backend.global.error.ErrorCode;
import com.webeye.backend.global.util.DummyDataInit;
import com.webeye.backend.nutrition.domain.Nutrient;
import com.webeye.backend.nutrition.domain.NutrientRecommendation;
import com.webeye.backend.nutrition.domain.type.Gender;
import com.webeye.backend.nutrition.domain.type.NutrientType;
import com.webeye.backend.nutrition.persistent.NutrientRecommendationRepository;
import com.webeye.backend.nutrition.persistent.NutrientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

@Slf4j
@RequiredArgsConstructor
@Order(2)
@DummyDataInit
public class NutrientRecommendationInit implements ApplicationRunner {

    private final NutrientRecommendationRepository recommendationRepository;
    private final NutrientRepository nutrientRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (recommendationRepository.count() > 0) {
            return;
        }

        Nutrient carbohydrate = nutrientRepository.findByType(NutrientType.CARBOHYDRATE)
                .orElseThrow(() -> new BusinessException(ErrorCode.NUTRIENT_NOT_FOUND));
        Nutrient protein = nutrientRepository.findByType(NutrientType.PROTEIN)
                .orElseThrow(() -> new BusinessException(ErrorCode.NUTRIENT_NOT_FOUND));
        Nutrient calcium = nutrientRepository.findByType(NutrientType.CALCIUM)
                .orElseThrow(() -> new BusinessException(ErrorCode.NUTRIENT_NOT_FOUND));
        Nutrient sodium = nutrientRepository.findByType(NutrientType.SODIUM)
                .orElseThrow(() -> new BusinessException(ErrorCode.NUTRIENT_NOT_FOUND));
        Nutrient vitaminE = nutrientRepository.findByType(NutrientType.VITAMIN_E)
                .orElseThrow(() -> new BusinessException(ErrorCode.NUTRIENT_NOT_FOUND));
        Nutrient vitaminB = nutrientRepository.findByType(NutrientType.VITAMIN_B)
                .orElseThrow(() -> new BusinessException(ErrorCode.NUTRIENT_NOT_FOUND));
        Nutrient fat = nutrientRepository.findByType(NutrientType.FAT)
                .orElseThrow(() -> new BusinessException(ErrorCode.NUTRIENT_NOT_FOUND));
        Nutrient transFat = nutrientRepository.findByType(NutrientType.TRANS_FAT)
                .orElseThrow(() -> new BusinessException(ErrorCode.NUTRIENT_NOT_FOUND));
        Nutrient saturatedFat = nutrientRepository.findByType(NutrientType.SATURATED_FAT)
                .orElseThrow(() -> new BusinessException(ErrorCode.NUTRIENT_NOT_FOUND));

        // 탄수화물 권장 섭취량
        saveRecommendation(carbohydrate, Gender.MALE, 0, 0, 75);
        saveRecommendation(carbohydrate, Gender.MALE, 1, 2, 130);
        saveRecommendation(carbohydrate, Gender.MALE, 3, 5, 130);
        saveRecommendation(carbohydrate, Gender.MALE, 6, 8, 130);
        saveRecommendation(carbohydrate, Gender.MALE, 9, 11, 130);
        saveRecommendation(carbohydrate, Gender.MALE, 12, 14, 130);
        saveRecommendation(carbohydrate, Gender.MALE, 15, 18, 130);
        saveRecommendation(carbohydrate, Gender.MALE, 19, 29, 130);
        saveRecommendation(carbohydrate, Gender.MALE, 30, 49, 130);
        saveRecommendation(carbohydrate, Gender.MALE, 50, 64, 130);
        saveRecommendation(carbohydrate, Gender.MALE, 65, 74, 130);
        saveRecommendation(carbohydrate, Gender.MALE, 75, 200, 130);

        saveRecommendation(carbohydrate, Gender.FEMALE, 0, 0, 75);
        saveRecommendation(carbohydrate, Gender.FEMALE, 1, 2, 130);
        saveRecommendation(carbohydrate, Gender.FEMALE, 3, 5, 130);
        saveRecommendation(carbohydrate, Gender.FEMALE, 6, 8, 130);
        saveRecommendation(carbohydrate, Gender.FEMALE, 9, 11, 130);
        saveRecommendation(carbohydrate, Gender.FEMALE, 12, 14, 130);
        saveRecommendation(carbohydrate, Gender.FEMALE, 15, 18, 130);
        saveRecommendation(carbohydrate, Gender.FEMALE, 19, 29, 130);
        saveRecommendation(carbohydrate, Gender.FEMALE, 30, 49, 130);
        saveRecommendation(carbohydrate, Gender.FEMALE, 50, 64, 130);
        saveRecommendation(carbohydrate, Gender.FEMALE, 65, 74, 130);
        saveRecommendation(carbohydrate, Gender.FEMALE, 75, 200, 130);


        // 단백질 권장 섭취량
        saveRecommendation(protein, Gender.MALE, 0, 0, 12.5);
        saveRecommendation(protein, Gender.MALE, 1, 2, 20);
        saveRecommendation(protein, Gender.MALE, 3, 5, 25);
        saveRecommendation(protein, Gender.MALE, 6, 8, 35);
        saveRecommendation(protein, Gender.MALE, 9, 11, 50);
        saveRecommendation(protein, Gender.MALE, 12, 14, 60);
        saveRecommendation(protein, Gender.MALE, 15, 18, 65);
        saveRecommendation(protein, Gender.MALE, 19, 29, 65);
        saveRecommendation(protein, Gender.MALE, 30, 49, 65);
        saveRecommendation(protein, Gender.MALE, 50, 64, 60);
        saveRecommendation(protein, Gender.MALE, 65, 74, 60);
        saveRecommendation(protein, Gender.MALE, 75, 200, 60);

        saveRecommendation(protein, Gender.FEMALE, 0, 0, 12.5);
        saveRecommendation(protein, Gender.FEMALE, 1, 2, 20);
        saveRecommendation(protein, Gender.FEMALE, 3, 5, 25);
        saveRecommendation(protein, Gender.FEMALE, 6, 8, 35);
        saveRecommendation(protein, Gender.FEMALE, 9, 11, 45);
        saveRecommendation(protein, Gender.FEMALE, 12, 14, 55);
        saveRecommendation(protein, Gender.FEMALE, 15, 18, 55);
        saveRecommendation(protein, Gender.FEMALE, 19, 29, 55);
        saveRecommendation(protein, Gender.FEMALE, 30, 49, 50);
        saveRecommendation(protein, Gender.FEMALE, 50, 64, 50);
        saveRecommendation(protein, Gender.FEMALE, 65, 74, 50);
        saveRecommendation(protein, Gender.FEMALE, 75, 200, 50);


        // 칼슘 권장 섭취량
        saveRecommendation(calcium, Gender.MALE, 0, 0, 275);
        saveRecommendation(calcium, Gender.MALE, 1, 2, 500);
        saveRecommendation(calcium, Gender.MALE, 3, 5, 600);
        saveRecommendation(calcium, Gender.MALE, 6, 8, 700);
        saveRecommendation(calcium, Gender.MALE, 9, 11, 800);
        saveRecommendation(calcium, Gender.MALE, 12, 14, 1000);
        saveRecommendation(calcium, Gender.MALE, 15, 18, 900);
        saveRecommendation(calcium, Gender.MALE, 19, 29, 800);
        saveRecommendation(calcium, Gender.MALE, 30, 49, 800);
        saveRecommendation(calcium, Gender.MALE, 50, 64, 750);
        saveRecommendation(calcium, Gender.MALE, 65, 74, 700);
        saveRecommendation(calcium, Gender.MALE, 75, 200, 700);

        saveRecommendation(calcium, Gender.FEMALE, 0, 0, 275);
        saveRecommendation(calcium, Gender.FEMALE, 1, 2, 500);
        saveRecommendation(calcium, Gender.FEMALE, 3, 5, 600);
        saveRecommendation(calcium, Gender.FEMALE, 6, 8, 700);
        saveRecommendation(calcium, Gender.FEMALE, 9, 11, 800);
        saveRecommendation(calcium, Gender.FEMALE, 12, 14, 900);
        saveRecommendation(calcium, Gender.FEMALE, 15, 18, 800);
        saveRecommendation(calcium, Gender.FEMALE, 19, 29, 700);
        saveRecommendation(calcium, Gender.FEMALE, 30, 49, 700);
        saveRecommendation(calcium, Gender.FEMALE, 50, 64, 800);
        saveRecommendation(calcium, Gender.FEMALE, 65, 74, 800);
        saveRecommendation(calcium, Gender.FEMALE, 75, 200, 800);


        // 나트륨 권장 섭취량
        saveRecommendation(sodium, Gender.MALE, 0, 0, 240);
        saveRecommendation(sodium, Gender.MALE, 1, 2, 810);
        saveRecommendation(sodium, Gender.MALE, 3, 5, 1000);
        saveRecommendation(sodium, Gender.MALE, 6, 8, 1200);
        saveRecommendation(sodium, Gender.MALE, 9, 11, 1500);
        saveRecommendation(sodium, Gender.MALE, 12, 14, 1500);
        saveRecommendation(sodium, Gender.MALE, 15, 18, 1500);
        saveRecommendation(sodium, Gender.MALE, 19, 29, 1500);
        saveRecommendation(sodium, Gender.MALE, 30, 49, 1500);
        saveRecommendation(sodium, Gender.MALE, 50, 64, 1500);
        saveRecommendation(sodium, Gender.MALE, 65, 74, 1300);
        saveRecommendation(sodium, Gender.MALE, 75, 200, 1100);

        saveRecommendation(sodium, Gender.FEMALE, 0, 0, 240);
        saveRecommendation(sodium, Gender.FEMALE, 1, 2, 810);
        saveRecommendation(sodium, Gender.FEMALE, 3, 5, 1000);
        saveRecommendation(sodium, Gender.FEMALE, 6, 8, 1200);
        saveRecommendation(sodium, Gender.FEMALE, 9, 11, 1500);
        saveRecommendation(sodium, Gender.FEMALE, 12, 14, 1500);
        saveRecommendation(sodium, Gender.FEMALE, 15, 18, 1500);
        saveRecommendation(sodium, Gender.FEMALE, 19, 29, 1500);
        saveRecommendation(sodium, Gender.FEMALE, 30, 49, 1500);
        saveRecommendation(sodium, Gender.FEMALE, 50, 64, 1500);
        saveRecommendation(sodium, Gender.FEMALE, 65, 74, 1300);
        saveRecommendation(sodium, Gender.FEMALE, 75, 200, 1100);


        // 비타민 E 권장 섭취량
        saveRecommendation(vitaminE, Gender.MALE, 0, 0, 3.5);
        saveRecommendation(vitaminE, Gender.MALE, 1, 2, 5);
        saveRecommendation(vitaminE, Gender.MALE, 3, 5, 6);
        saveRecommendation(vitaminE, Gender.MALE, 6, 8, 7);
        saveRecommendation(vitaminE, Gender.MALE, 9, 11, 9);
        saveRecommendation(vitaminE, Gender.MALE, 12, 14, 11);
        saveRecommendation(vitaminE, Gender.MALE, 15, 18, 12);
        saveRecommendation(vitaminE, Gender.MALE, 19, 29, 12);
        saveRecommendation(vitaminE, Gender.MALE, 30, 49, 12);
        saveRecommendation(vitaminE, Gender.MALE, 50, 64, 12);
        saveRecommendation(vitaminE, Gender.MALE, 65, 74, 12);
        saveRecommendation(vitaminE, Gender.MALE, 75, 200, 12);

        saveRecommendation(vitaminE, Gender.FEMALE, 0, 0, 3.5);
        saveRecommendation(vitaminE, Gender.FEMALE, 1, 2, 5);
        saveRecommendation(vitaminE, Gender.FEMALE, 3, 5, 6);
        saveRecommendation(vitaminE, Gender.FEMALE, 6, 8, 7);
        saveRecommendation(vitaminE, Gender.FEMALE, 9, 11, 9);
        saveRecommendation(vitaminE, Gender.FEMALE, 12, 14, 11);
        saveRecommendation(vitaminE, Gender.FEMALE, 15, 18, 12);
        saveRecommendation(vitaminE, Gender.FEMALE, 19, 29, 12);
        saveRecommendation(vitaminE, Gender.FEMALE, 30, 49, 12);
        saveRecommendation(vitaminE, Gender.FEMALE, 50, 64, 12);
        saveRecommendation(vitaminE, Gender.FEMALE, 65, 74, 12);
        saveRecommendation(vitaminE, Gender.FEMALE, 75, 200, 12);


        // 비타민 B 권장 섭취량
        saveRecommendation(vitaminB, Gender.MALE, 0, 0, 0.2);
        saveRecommendation(vitaminB, Gender.MALE, 1, 2, 20);
        saveRecommendation(vitaminB, Gender.MALE, 3, 5, 30);
        saveRecommendation(vitaminB, Gender.MALE, 6, 8, 45);
        saveRecommendation(vitaminB, Gender.MALE, 9, 11, 60);
        saveRecommendation(vitaminB, Gender.MALE, 12, 14, 80);
        saveRecommendation(vitaminB, Gender.MALE, 15, 18, 95);
        saveRecommendation(vitaminB, Gender.MALE, 19, 200, 100);

        saveRecommendation(vitaminB, Gender.FEMALE, 0, 0, 0.2);
        saveRecommendation(vitaminB, Gender.FEMALE, 1, 2, 20);
        saveRecommendation(vitaminB, Gender.FEMALE, 6, 8, 45);
        saveRecommendation(vitaminB, Gender.FEMALE, 9, 11, 60);
        saveRecommendation(vitaminB, Gender.FEMALE, 12, 14, 80);
        saveRecommendation(vitaminB, Gender.FEMALE, 15, 18, 95);
        saveRecommendation(vitaminB, Gender.FEMALE, 19, 200, 100);


        // 지방 권장 섭취량
        saveRecommendation(fat, Gender.MALE, 1, 2, 27.5);
        saveRecommendation(fat, Gender.MALE, 3, 5, 40);
        saveRecommendation(fat, Gender.MALE, 6, 8, 47.5);
        saveRecommendation(fat, Gender.MALE, 9, 11, 57.5);
        saveRecommendation(fat, Gender.MALE, 12, 14, 72.5);
        saveRecommendation(fat, Gender.MALE, 15, 18, 80.0);
        saveRecommendation(fat, Gender.MALE, 19, 29, 72.5);
        saveRecommendation(fat, Gender.MALE, 30, 49, 70.0);
        saveRecommendation(fat, Gender.MALE, 50, 64, 62.5);
        saveRecommendation(fat, Gender.MALE, 65, 74, 57.5);
        saveRecommendation(fat, Gender.MALE, 75, 200, 55.0);

        saveRecommendation(fat, Gender.FEMALE, 1, 2, 27.5);
        saveRecommendation(fat, Gender.FEMALE, 3, 5, 40);
        saveRecommendation(fat, Gender.FEMALE, 6, 8, 42.5);
        saveRecommendation(fat, Gender.FEMALE, 9, 11, 50.0);
        saveRecommendation(fat, Gender.FEMALE, 12, 14, 57.5);
        saveRecommendation(fat, Gender.FEMALE, 15, 18, 57.5);
        saveRecommendation(fat, Gender.FEMALE, 19, 29, 57.5);
        saveRecommendation(fat, Gender.FEMALE, 30, 49, 55.0);
        saveRecommendation(fat, Gender.FEMALE, 50, 64, 50.0);
        saveRecommendation(fat, Gender.FEMALE, 65, 74, 45.0);
        saveRecommendation(fat, Gender.FEMALE, 75, 200, 42.5);


        // 트랜스 지방 권장 섭취량
        saveRecommendation(transFat, Gender.MALE, 0, 2, 0);
        saveRecommendation(transFat, Gender.MALE, 3, 5, 1.78);
        saveRecommendation(transFat, Gender.MALE, 6, 8, 2.11);
        saveRecommendation(transFat, Gender.MALE, 9, 11, 2.56);
        saveRecommendation(transFat, Gender.MALE, 12, 14, 3.22);
        saveRecommendation(transFat, Gender.MALE, 15, 18, 3.56);
        saveRecommendation(transFat, Gender.MALE, 19, 29, 3.22);
        saveRecommendation(transFat, Gender.MALE, 30, 49, 3.11);
        saveRecommendation(transFat, Gender.MALE, 50, 64, 2.78);
        saveRecommendation(transFat, Gender.MALE, 65, 74, 2.56);
        saveRecommendation(transFat, Gender.MALE, 75, 200, 2.44);

        saveRecommendation(transFat, Gender.FEMALE, 0, 2, 0);
        saveRecommendation(transFat, Gender.FEMALE, 3, 5, 1.78);
        saveRecommendation(transFat, Gender.FEMALE, 6, 8, 1.89);
        saveRecommendation(transFat, Gender.FEMALE, 9, 11, 2.22);
        saveRecommendation(transFat, Gender.FEMALE, 12, 14, 2.56);
        saveRecommendation(transFat, Gender.FEMALE, 15, 18, 2.56);
        saveRecommendation(transFat, Gender.FEMALE, 19, 29, 2.56);
        saveRecommendation(transFat, Gender.FEMALE, 30, 49, 2.44);
        saveRecommendation(transFat, Gender.FEMALE, 50, 64, 2.22);
        saveRecommendation(transFat, Gender.FEMALE, 65, 74, 2.00);
        saveRecommendation(transFat, Gender.FEMALE, 75, 200, 1.89);


        // 포화 지방 권장 섭취량
        saveRecommendation(saturatedFat, Gender.MALE, 0, 2, 0);
        saveRecommendation(saturatedFat, Gender.MALE, 3, 5, 14.22);
        saveRecommendation(saturatedFat, Gender.MALE, 6, 8, 16.89);
        saveRecommendation(saturatedFat, Gender.MALE, 9, 11, 20.44);
        saveRecommendation(saturatedFat, Gender.MALE, 12, 14, 25.78);
        saveRecommendation(saturatedFat, Gender.MALE, 15, 18, 28.44);
        saveRecommendation(saturatedFat, Gender.MALE, 19, 29, 22.56);
        saveRecommendation(saturatedFat, Gender.MALE, 30, 49, 21.78);
        saveRecommendation(saturatedFat, Gender.MALE, 50, 64, 19.44);
        saveRecommendation(saturatedFat, Gender.MALE, 65, 74, 17.89);
        saveRecommendation(saturatedFat, Gender.MALE, 75, 200, 17.11);

        saveRecommendation(saturatedFat, Gender.FEMALE, 0, 2, 0);
        saveRecommendation(saturatedFat, Gender.FEMALE, 3, 5, 14.22);
        saveRecommendation(saturatedFat, Gender.FEMALE, 6, 8, 15.11);
        saveRecommendation(saturatedFat, Gender.FEMALE, 9, 11, 17.78);
        saveRecommendation(saturatedFat, Gender.FEMALE, 12, 14, 20.44);
        saveRecommendation(saturatedFat, Gender.FEMALE, 15, 18, 20.44);
        saveRecommendation(saturatedFat, Gender.FEMALE, 19, 29, 17.89);
        saveRecommendation(saturatedFat, Gender.FEMALE, 30, 49, 17.11);
        saveRecommendation(saturatedFat, Gender.FEMALE, 50, 64, 15.56);
        saveRecommendation(saturatedFat, Gender.FEMALE, 65, 74, 14.00);
        saveRecommendation(saturatedFat, Gender.FEMALE, 75, 200, 13.22);

    }

    private void saveRecommendation(Nutrient nutrient, Gender gender, int minAge, int maxAge, double amount) {
        NutrientRecommendation recommendation = NutrientRecommendation.builder()
                .nutrient(nutrient)
                .gender(gender)
                .minAge(minAge)
                .maxAge(maxAge)
                .amount(amount)
                .build();

        recommendationRepository.save(recommendation);
    }
}