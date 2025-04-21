package com.webeye.backend.rawmaterial.dto;

import java.util.List;

public record RawMaterialResponse(
        Response response
) {
    public record Response(
            Body body
    ) {}

    public record Body(
            List<Item> items,
            String totalCount,
            String numOfRows,
            String pageNo
    ) {}

    public record Item(
            Long rawMaterialId,
            String foodCd,
            String foodNm,
            String nutConSrtrQua,
            double enerc,
            double water,
            double prot,
            double fatce,
            double ash,
            double chocdf,
            double sugar,
            double fibtg,
            double ca,
            double fe,
            double p,
            double k,
            double nat,
            double vitaRae,
            double retol,
            double cartb,
            double thia,
            double ribf,
            double nia,
            double vitc,
            double vitd,
            double chole,
            double fasat,
            double fartn
    ) {}
}

