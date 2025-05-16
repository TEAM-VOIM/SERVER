package com.webeye.backend.imageanalysis.presentation;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.imageanalysis.application.ImageAnalysisService;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import com.webeye.backend.imageanalysis.dto.response.ImageAnalysisResponse;
import com.webeye.backend.imageanalysis.presentation.swagger.ImageAnalysisSwagger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.webeye.backend.global.dto.response.type.SuccessCode.IMAGE_ANALYSIS_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/image-analysis")
public class ImageAnalysisController implements ImageAnalysisSwagger {
    private final ImageAnalysisService imageAnalysisService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "")
    public SuccessResponse<ImageAnalysisResponse> imageAnalysis(@Valid @RequestBody ImageAnalysisRequest request) {
        return SuccessResponse.of(IMAGE_ANALYSIS_SUCCESS, imageAnalysisService.analyzeImage(request));
    }
}
