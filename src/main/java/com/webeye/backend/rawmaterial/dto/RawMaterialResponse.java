package com.webeye.backend.rawmaterial.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "원재료 영양 성분")
public record RawMaterialResponse(
        Response response
) {

    public record Response(
            Body body
    ) {}

    public record Body(
            @Schema(description = "원재료 목록")
            List<Item> items,
            @Schema(description = "페이지 번호", example = "1")
            String pageNo,
            @Schema(description = "페이지 내 데이터 개수", example = "10")
            String numOfRows,
            @Schema(description = "총 데이터 개수", example = "3500")
            String totalCount
    ) {}

    public record Item(
            @Schema(description = "원재료 영양성분 ID", example = "1")
            Long rawMaterialId,
            @Schema(description = "식품 코드", example = "R211-009111601-0001")
            String foodCd,
            @Schema(description = "식품명", example = "청어_암컷,육_생것_포항_8월")
            String foodNm,
            @Schema(description = "영양성분함량기준량", example = "100g")
            String nutConSrtrQua,
            @Schema(description = "에너지", example = "90")
            Double enerc,
            @Schema(description = "수분", example = "40.7")
            Double water,
            @Schema(description = "단백질", example = "18.21")
            Double prot,
            @Schema(description = "지방", example = "3.14")
            Double fatce,
            @Schema(description = "회분", example = "1.2")
            Double ash,
            @Schema(description = "탄수화물", example = "0.61")
            Double chocdf,
            @Schema(description = "당류", example = "2.64")
            Double sugar,
            @Schema(description = "식이섬유", example = "21.7")
            Double fibtg,
            @Schema(description = "칼슘", example = "156")
            Double ca,
            @Schema(description = "철", example = "10.4")
            Double fe,
            @Schema(description = "인", example = "244")
            Double p,
            @Schema(description = "칼륨", example = "250")
            Double k,
            @Schema(description = "나트륨", example = "1300")
            Double nat,
            @Schema(description = "비타민 A", example = "1007")
            Double vitaRae,
            @Schema(description = "레티놀", example = "12")
            Double retol,
            @Schema(description = "베타카르틴", example = "2698")
            Double cartb,
            @Schema(description = "티아민", example = "0.968")
            Double thia,
            @Schema(description = "리보플라빈", example = "0.11")
            Double ribf,
            @Schema(description = "니아신", example = "6.3")
            Double nia,
            @Schema(description = "비타민 C", example = "1.75")
            Double vitc,
            @Schema(description = "비타민 D", example = "48")
            Double vitd,
            @Schema(description = "콜레스테롤", example = "22.57")
            Double chole,
            @Schema(description = "포화지방산", example = "0.83")
            Double fasat,
            @Schema(description = "트랜스지방산", example = "0")
            Double fartn
    ) {}
}