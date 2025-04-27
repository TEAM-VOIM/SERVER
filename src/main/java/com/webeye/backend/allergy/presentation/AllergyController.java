package com.webeye.backend.allergy.presentation;

import com.webeye.backend.allergy.application.AllergyService;
import com.webeye.backend.allergy.dto.response.AllergyResponse;
import com.webeye.backend.allergy.presentation.swagger.AllergySwagger;
import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.webeye.backend.global.dto.response.type.SuccessCode.ALLERGY_ANALYSIS_SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/allergy")
public class AllergyController implements AllergySwagger {
    private final AllergyService allergyService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "")
    public SuccessResponse<AllergyResponse> allergyAnalysis(@Valid @RequestBody ImageAnalysisRequest request) {
        return SuccessResponse.of(ALLERGY_ANALYSIS_SUCCESS ,allergyService.analyzeAllergy(request));
    }
}
