package com.webeye.backend.rawmaterial.application.service;

import com.webeye.backend.rawmaterial.infrastructure.mapper.RawMaterialMapper;
import com.webeye.backend.rawmaterial.domain.RawMaterial;
import com.webeye.backend.rawmaterial.dto.RawMaterialResponse;
import com.webeye.backend.rawmaterial.infrastructure.client.RawMaterialClient;
import com.webeye.backend.rawmaterial.infrastructure.persistence.RawMaterialRepository;
import com.webeye.backend.global.error.BusinessException;
import com.webeye.backend.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public RawMaterialResponse.Body callRawMaterialAPI(Integer pageNo, Integer numOfRows) {
        RawMaterialResponse response = rawMaterialClient.getRawMaterialInfo(serviceKey, pageNo, numOfRows, "json");

        RawMaterialResponse.Response responseDto = response.response();

        List<RawMaterialResponse.Item> items = responseDto.body().items();

        validateRawMaterial(responseDto, items);

        List<RawMaterial> rawMaterials = RawMaterialMapper.toEntityList(items);

        // List<RawMaterial> savedRawMaterials = rawMaterialRepository.saveAll(rawMaterials);

        return RawMaterialMapper.of(rawMaterials, pageNo, numOfRows, items.size());
    }

    private void validateRawMaterial(RawMaterialResponse.Response response, List<RawMaterialResponse.Item> items) {
        // Open API 응답 실패
        if (response == null || response.body() == null) {
            throw new BusinessException(ErrorCode.OPEN_API_RESPONSE_NULL);
        }

        // items not found
        if (items == null || response.body().items().isEmpty()) {
            throw new BusinessException(ErrorCode.OPEN_API_DATA_MISSING);
        }
    }
}
