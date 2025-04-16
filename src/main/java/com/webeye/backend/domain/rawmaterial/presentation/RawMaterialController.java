package com.webeye.backend.domain.rawmaterial.presentation;

import com.webeye.backend.domain.rawmaterial.application.service.RawMaterialService;
import com.webeye.backend.domain.rawmaterial.dto.RawMaterialResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[주의 영양 성분 표시]")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/raw-materials")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    @GetMapping()
    public ResponseEntity<RawMaterialResponseDTO.Body> getRawMaterial() {
        RawMaterialResponseDTO.Body response = rawMaterialService.getRawMaterial();
        return ResponseEntity.ok(response);
    }
}
