package com.webeye.backend.rawmaterial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RawMaterialResponseDTO {

    private Response response;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Body body;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Body {
        private List<Item> items;
        private String totalCount;
        private String numOfRows;
        private String pageNo;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private Long rawMaterialId;
        private String foodCd;
        private String foodNm;
        private String nutConSrtrQua;
        private double enerc;
        private double water;
        private double prot;
        private double fatce;
        private double ash;
        private double chocdf;
        private double sugar;
        private double fibtg;
        private double ca;
        private double fe;
        private double p;
        private double k;
        private double nat;
        private double vitaRae;
        private double retol;
        private double cartb;
        private double thia;
        private double ribf;
        private double nia;
        private double vitc;
        private double vitd;
        private double chole;
        private double fasat;
        private double fartn;
    }
}
