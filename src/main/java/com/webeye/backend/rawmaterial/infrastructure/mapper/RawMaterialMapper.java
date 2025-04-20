package com.webeye.backend.rawmaterial.infrastructure.mapper;

import com.webeye.backend.rawmaterial.domain.RawMaterial;
import com.webeye.backend.rawmaterial.dto.RawMaterialResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RawMaterialMapper {

    public static RawMaterial toEntity(RawMaterialResponseDTO.Item item) {
        return RawMaterial.builder()
                .foodCd(item.getFoodCd())
                .foodNm(item.getFoodNm())
                .nutConSrtrQua(item.getNutConSrtrQua())
                .enerc(item.getEnerc())
                .water(item.getWater())
                .prot(item.getProt())
                .fatce(item.getFatce())
                .ash(item.getAsh())
                .chocdf(item.getChocdf())
                .sugar(item.getSugar())
                .fibtg(item.getFibtg())
                .ca(item.getCa())
                .fe(item.getFe())
                .p(item.getP())
                .k(item.getK())
                .nat(item.getNat())
                .vitaRae(item.getVitaRae())
                .retol(item.getRetol())
                .cartb(item.getCartb())
                .thia(item.getThia())
                .ribf(item.getRibf())
                .nia(item.getNia())
                .vitc(item.getVitc())
                .vitd(item.getVitd())
                .chole(item.getChole())
                .fasat(item.getFasat())
                .fartn(item.getFartn())
                .build();
    }

    public static RawMaterialResponseDTO.Item of(RawMaterial rawMaterial) {
        return RawMaterialResponseDTO.Item.builder()
                .rawMaterialId(rawMaterial.getId())
                .foodCd(rawMaterial.getFoodCd())
                .foodNm(rawMaterial.getFoodNm())
                .nutConSrtrQua(rawMaterial.getNutConSrtrQua())
                .enerc(rawMaterial.getEnerc())
                .water(rawMaterial.getWater())
                .prot(rawMaterial.getProt())
                .fatce(rawMaterial.getFatce())
                .ash(rawMaterial.getAsh())
                .chocdf(rawMaterial.getChocdf())
                .sugar(rawMaterial.getSugar())
                .fibtg(rawMaterial.getFibtg())
                .ca(rawMaterial.getCa())
                .fe(rawMaterial.getFe())
                .p(rawMaterial.getP())
                .k(rawMaterial.getK())
                .nat(rawMaterial.getNat())
                .vitaRae(rawMaterial.getVitaRae())
                .retol(rawMaterial.getRetol())
                .cartb(rawMaterial.getCartb())
                .thia(rawMaterial.getThia())
                .ribf(rawMaterial.getRibf())
                .nia(rawMaterial.getNia())
                .vitc(rawMaterial.getVitc())
                .vitd(rawMaterial.getVitd())
                .chole(rawMaterial.getChole())
                .fasat(rawMaterial.getFasat())
                .fartn(rawMaterial.getFartn())
                .build();
    }

    public static RawMaterialResponseDTO.Body ofList(List<RawMaterial> rawMaterials) {
        List<RawMaterialResponseDTO.Item> rawMaterialResponseDTOList = rawMaterials.stream()
                .map(RawMaterialMapper::of).collect(Collectors.toList());

        return RawMaterialResponseDTO.Body.builder()
                .items(rawMaterialResponseDTOList)
                .build();
    }
}
