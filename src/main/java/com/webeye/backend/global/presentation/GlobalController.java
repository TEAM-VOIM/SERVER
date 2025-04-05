package com.webeye.backend.global.presentation;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.global.presentation.swagger.GlobalSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.webeye.backend.global.dto.response.type.SuccessCode.HEALTH_CHECK_SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/global")
public class GlobalController implements GlobalSwagger {
    @GetMapping("/health-check")
    @Override
    public ResponseEntity<SuccessResponse<String>> healthcheck() {
        return ResponseEntity.ok(SuccessResponse.of(HEALTH_CHECK_SUCCESS));
    }
}
