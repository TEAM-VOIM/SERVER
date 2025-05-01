package com.webeye.backend.disease.persistent.init;

import com.webeye.backend.disease.domain.Disease;
import com.webeye.backend.disease.domain.type.DiseaseType;
import com.webeye.backend.disease.persistent.DiseaseRepository;
import com.webeye.backend.global.util.DummyDataInit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

@Slf4j
@RequiredArgsConstructor
@Order(1)
@DummyDataInit
public class DiseaseInit implements ApplicationRunner {
    private final DiseaseRepository diseaseRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (diseaseRepository.count() >= DiseaseType.values().length) {
            log.info("[Disease] 기본 데이터 존재");
            return;
        }
        for (DiseaseType diseaseType : DiseaseType.values()) {
            Disease disease = Disease.builder()
                    .name(diseaseType)
                    .build();
            diseaseRepository.save(disease);
        }
        log.info("[Disease] 기본 데이터 저장 완료");
    }
}
