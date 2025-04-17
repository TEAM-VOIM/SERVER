package com.webeye.backend.explanation.presentation;

import com.webeye.backend.explanation.application.ExplanationService;
import com.webeye.backend.explanation.dto.response.DetailExplanationResponse;
import com.webeye.backend.explanation.dto.response.PointExplanationResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/explanation")
public class ExplanationController {
    private final ExplanationService explanationService;

    @PostMapping(value = "")
    public PointExplanationResponse productAnalysis(@RequestBody ImageAnalysisRequest request) {
        return explanationService.analyzeProductPoint(request);
    }

    @PostMapping(value = "detail")
    public DetailExplanationResponse productDetailAnalysis(@RequestBody ImageAnalysisRequest request) {
        return explanationService.analyzeProductDetail(request);
    }
}
