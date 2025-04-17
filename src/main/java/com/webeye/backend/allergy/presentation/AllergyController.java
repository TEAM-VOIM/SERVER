package com.webeye.backend.allergy.presentation;

import com.webeye.backend.allergy.application.AllergyService;
import com.webeye.backend.allergy.dto.response.AllergyResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/allergy")
public class AllergyController {
    private final AllergyService allergyService;

    @PostMapping(value = "")
    public AllergyResponse allergyAnalysis(@RequestBody ImageAnalysisRequest request) {
        return allergyService.analyzeAllergy(request);
    }
}
