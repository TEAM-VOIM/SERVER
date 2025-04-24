package com.webeye.backend.rawmaterial.application.service;

import com.webeye.backend.rawmaterial.dto.RawMaterialResponse;

public interface RawMaterialService {
    RawMaterialResponse.Body callRawMaterialAPI(Integer pageNo, Integer numOfRows);
}
