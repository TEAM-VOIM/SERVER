package com.webeye.backend.domain.rawmaterial.application.service;

import com.webeye.backend.domain.rawmaterial.infrastructure.mapper.RawMaterialMapper;
import com.webeye.backend.domain.rawmaterial.domain.RawMaterial;
import com.webeye.backend.domain.rawmaterial.dto.RawMaterialResponseDTO;
import com.webeye.backend.domain.rawmaterial.infrastructure.client.RawMaterialClient;
import com.webeye.backend.domain.rawmaterial.infrastructure.persistence.RawMaterialRepository;
import com.webeye.backend.global.error.BusinessException;
import com.webeye.backend.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RawMaterialServiceImpl implements RawMaterialService {

    @Value("${open-api.service-key}")
    private String serviceKey;

    private final RawMaterialClient rawMaterialClient;
    private final RawMaterialRepository rawMaterialRepository;

    @Override
    @Transactional
    public RawMaterialResponseDTO.Body callRawMaterialAPI() {
        RawMaterialResponseDTO response = rawMaterialClient.getRawMaterialInfo(serviceKey, 1, 100, "json");

        RawMaterialResponseDTO.Response responseDto = response.getResponse();

        List<RawMaterialResponseDTO.Item> items = responseDto.getBody().getItems();

        validateRawMaterial(responseDto, items);

        List<RawMaterial> rawMaterials = items.stream()
                .map(RawMaterialMapper::toEntity)
                .collect(Collectors.toList());

        List<RawMaterial> saved = rawMaterialRepository.saveAll(rawMaterials);

        return RawMaterialMapper.ofList(saved);
    }

    private void validateRawMaterial(RawMaterialResponseDTO.Response response, List<RawMaterialResponseDTO.Item> items) {
        // Open API 응답 실패
        if (response == null || response.getBody() == null) {
            throw new BusinessException(ErrorCode.OPEN_API_RESPONSE_NULL);
        }

        // items not found
        if (items == null || response.getBody().getItems().isEmpty()) {
            throw new BusinessException(ErrorCode.OPEN_API_DATA_MISSING);
        }
    }
}
