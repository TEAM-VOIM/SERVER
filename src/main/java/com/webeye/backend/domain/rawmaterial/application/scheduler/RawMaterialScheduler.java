package com.webeye.backend.domain.rawmaterial.application.scheduler;

import com.webeye.backend.domain.rawmaterial.application.service.RawMaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RawMaterialScheduler {

    private final RawMaterialService rawMaterialService;

    @Scheduled(cron = "0 0 0 1 3 *")
    public void updateRawMaterial() {

    }
}
