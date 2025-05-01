package com.webeye.backend.disease.persistent.init;

import com.webeye.backend.disease.domain.Disease;
import com.webeye.backend.disease.domain.DiseaseNutrient;
import com.webeye.backend.disease.domain.type.DiseaseType;
import com.webeye.backend.disease.persistent.DiseaseNutrientRepository;
import com.webeye.backend.disease.persistent.DiseaseRepository;
import com.webeye.backend.global.util.DummyDataInit;
import com.webeye.backend.nutrition.domain.Nutrient;
import com.webeye.backend.nutrition.domain.type.NutrientType;
import com.webeye.backend.nutrition.persistent.NutrientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Order(2)
@DummyDataInit
public class DiseaseNutrientInit implements ApplicationRunner {
    static final int DISEASE_NUTRIENT_NUM = 18;

    private final DiseaseRepository diseaseRepository;
    private final NutrientRepository nutrientRepository;
    private final DiseaseNutrientRepository diseaseNutrientRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (diseaseNutrientRepository.count() >= DISEASE_NUTRIENT_NUM) {
            log.info("[DiseaseNutrient] 기본 데이터 존재");
            return;
        }

        Nutrient sodium = nutrientRepository.findByName(NutrientType.SODIUM)  // 나트륨
                .orElseThrow(() -> new RuntimeException("Nutrient sodium not found"));
        Nutrient carbohydrate = nutrientRepository.findByName(NutrientType.CARBOHYDRATE)  // 탄수화물
                .orElseThrow(() -> new RuntimeException("Nutrient carbohydrate not found"));
        Nutrient sugars = nutrientRepository.findByName(NutrientType.SUGARS)  // 당류
                .orElseThrow(() -> new RuntimeException("Nutrient sugars not found"));
        Nutrient fat = nutrientRepository.findByName(NutrientType.FAT)  // 지방
                .orElseThrow(() -> new RuntimeException("Nutrient fat not found"));
        Nutrient transFat = nutrientRepository.findByName(NutrientType.TRANS_FAT)  // 트랜스지방
                .orElseThrow(() -> new RuntimeException("Nutrient trans fat not found"));
        Nutrient saturatedFat = nutrientRepository.findByName(NutrientType.SATURATED_FAT)  // 포화지방
                .orElseThrow(() -> new RuntimeException("Nutrient saturated fat not found"));
        Nutrient cholesterol = nutrientRepository.findByName(NutrientType.CHOLESTEROL)  // 콜레스테롤
                .orElseThrow(() -> new RuntimeException("Nutrient cholesterol not found"));
        Nutrient protein = nutrientRepository.findByName(NutrientType.PROTEIN)  // 단백질
                .orElseThrow(() -> new RuntimeException("Nutrient protein not found"));
        Nutrient calcium = nutrientRepository.findByName(NutrientType.CALCIUM)  // 칼슘
                .orElseThrow(() -> new RuntimeException("Nutrient calcium not found"));
        Nutrient phosphorus = nutrientRepository.findByName(NutrientType.PHOSPHORUS)  // 인
                .orElseThrow(() -> new RuntimeException("Nutrient phosphorus not found"));
        Nutrient niacin = nutrientRepository.findByName(NutrientType.NIACIN)  // 나이아신
                .orElseThrow(() -> new RuntimeException("Nutrient niacin not found"));
        Nutrient vitaminB = nutrientRepository.findByName(NutrientType.VITAMIN_B)  // 비타민 B
                .orElseThrow(() -> new RuntimeException("Nutrient vitamin B not found"));
        Nutrient vitaminE = nutrientRepository.findByName(NutrientType.VITAMIN_E)  // 비타민 E
                .orElseThrow(() -> new RuntimeException("Nutrient vitamin E not found"));


        Disease diabetes = diseaseRepository.findByName(DiseaseType.DIABETES)  // 당뇨
                .orElseThrow(() -> new RuntimeException("Disease DIABETES not found"));
        Disease hypertension = diseaseRepository.findByName(DiseaseType.HYPERTENSION)  // 고혈압
                .orElseThrow(() -> new RuntimeException("Disease HYPERTENSION not found"));
        Disease osteoporosis = diseaseRepository.findByName(DiseaseType.OSTEOPOROSIS)  // 골다공증
                .orElseThrow(() -> new RuntimeException("Disease OSTEOPOROSIS not found"));
        Disease kidneyDisease = diseaseRepository.findByName(DiseaseType.KIDNEY_DISEASE)  // 신장질환
                .orElseThrow(() -> new RuntimeException("Disease KIDNEY_DISEASE not found"));
        Disease gout = diseaseRepository.findByName(DiseaseType.GOUT)  // 통풍
                .orElseThrow(() -> new RuntimeException("Disease GOUT not found"));
        Disease fattyLiver = diseaseRepository.findByName(DiseaseType.FATTY_LIVER)  // 지방간
                .orElseThrow(() -> new RuntimeException("Disease FATTY_LIVER not found"));


        diseaseNutrientRepository.saveAll(List.of(
                // 당뇨: 탄수화물, 당류, 포화지방, 트랜스지방, 콜레스테롤, 나트륨
                DiseaseNutrient.builder().disease(diabetes).nutrient(carbohydrate).build(),
                DiseaseNutrient.builder().disease(diabetes).nutrient(sugars).build(),
                DiseaseNutrient.builder().disease(diabetes).nutrient(saturatedFat).build(),
                DiseaseNutrient.builder().disease(diabetes).nutrient(transFat).build(),
                DiseaseNutrient.builder().disease(diabetes).nutrient(cholesterol).build(),
                DiseaseNutrient.builder().disease(diabetes).nutrient(sodium).build(),

                // 고혈압: 나트륨, 포화지방, 당류
                DiseaseNutrient.builder().disease(hypertension).nutrient(sodium).build(),
                DiseaseNutrient.builder().disease(hypertension).nutrient(saturatedFat).build(),
                DiseaseNutrient.builder().disease(hypertension).nutrient(sugars).build(),

                // 골다공증: 나트륨
                DiseaseNutrient.builder().disease(osteoporosis).nutrient(sodium).build(),

                // 신장질환: 나트륨, 인, 콜레스테롤
                DiseaseNutrient.builder().disease(kidneyDisease).nutrient(sodium).build(),
                DiseaseNutrient.builder().disease(kidneyDisease).nutrient(phosphorus).build(),
                DiseaseNutrient.builder().disease(kidneyDisease).nutrient(cholesterol).build(),

                // 통풍: 단백질, 지방
                DiseaseNutrient.builder().disease(gout).nutrient(protein).build(),
                DiseaseNutrient.builder().disease(gout).nutrient(fat).build(),

                // 지방간: 탄수화물, 포화지방, 당류
                DiseaseNutrient.builder().disease(fattyLiver).nutrient(carbohydrate).build(),
                DiseaseNutrient.builder().disease(fattyLiver).nutrient(saturatedFat).build(),
                DiseaseNutrient.builder().disease(fattyLiver).nutrient(sugars).build()
        ));

    }
}
