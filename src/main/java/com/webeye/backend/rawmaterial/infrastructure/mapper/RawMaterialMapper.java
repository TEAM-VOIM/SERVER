package com.webeye.backend.rawmaterial.infrastructure.mapper;

import com.webeye.backend.rawmaterial.domain.RawMaterial;
import com.webeye.backend.rawmaterial.dto.RawMaterialResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class RawMaterialMapper {

    public static RawMaterial toEntity(RawMaterialResponse.Item item) {
        return RawMaterial.builder()
                .foodCd(item.foodCd())
                .foodNm(item.foodNm())
                .nutConSrtrQua(item.nutConSrtrQua())
                .enerc(item.enerc())
                .water(item.water())
                .prot(item.prot())
                .fatce(item.fatce())
                .ash(item.ash())
                .chocdf(item.chocdf())
                .sugar(item.sugar())
                .fibtg(item.fibtg())
                .ca(item.ca())
                .fe(item.fe())
                .p(item.p())
                .k(item.k())
                .nat(item.nat())
                .vitaRae(item.vitaRae())
                .retol(item.retol())
                .cartb(item.cartb())
                .thia(item.thia())
                .ribf(item.ribf())
                .nia(item.nia())
                .vitc(item.vitc())
                .vitd(item.vitd())
                .chole(item.chole())
                .fasat(item.fasat())
                .fartn(item.fartn())
                .build();
    }

    public static RawMaterialResponse.Item of(RawMaterial rawMaterial) {
        return new RawMaterialResponse.Item(
                rawMaterial.getId(),
                rawMaterial.getFoodCd(),
                rawMaterial.getFoodNm(),
                rawMaterial.getNutConSrtrQua(),
                rawMaterial.getEnerc(),
                rawMaterial.getWater(),
                rawMaterial.getProt(),
                rawMaterial.getFatce(),
                rawMaterial.getAsh(),
                rawMaterial.getChocdf(),
                rawMaterial.getSugar(),
                rawMaterial.getFibtg(),
                rawMaterial.getCa(),
                rawMaterial.getFe(),
                rawMaterial.getP(),
                rawMaterial.getK(),
                rawMaterial.getNat(),
                rawMaterial.getVitaRae(),
                rawMaterial.getRetol(),
                rawMaterial.getCartb(),
                rawMaterial.getThia(),
                rawMaterial.getRibf(),
                rawMaterial.getNia(),
                rawMaterial.getVitc(),
                rawMaterial.getVitd(),
                rawMaterial.getChole(),
                rawMaterial.getFasat(),
                rawMaterial.getFartn()
        );
    }

    public static List<RawMaterial> toEntityList(List<RawMaterialResponse.Item> items) {
        return items.stream()
                .map(RawMaterialMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static RawMaterialResponse.Body of(List<RawMaterial> list, int totalCount, int numOfRows, int pageNo) {
        List<RawMaterialResponse.Item> itemList = list.stream()
                .map(RawMaterialMapper::of)
                .collect(Collectors.toList());

        return new RawMaterialResponse.Body(
                itemList,
                String.valueOf(totalCount),
                String.valueOf(numOfRows),
                String.valueOf(pageNo)
        );
    }
}
