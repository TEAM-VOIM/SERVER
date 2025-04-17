package com.webeye.backend.imageanalysis.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record ImageAnalysisRequest (
        List<String> urls
) {
}

