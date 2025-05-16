package com.webeye.backend.product.domain.type;

import lombok.Getter;

@Getter
public enum OutlineType {
    MAIN("내가 보낸 상품 설명 이미지를 분석하여 상품의 주된 성분, 소재, 기능, 구성 요소 등에 관한 정보를 알려줘. 특히, 식품이라면 영양성분은 필수로 알려줘. (ex. (영양)성분, 재질, 기능성, 구성품, 원산지, 제조사)"),
    USAGE("내가 보낸 상품 설명 이미지를 분석하여 상품의 사용(섭취/장착/조립/활용) 방법 및 사용 대상을 알려줘. (ex. 섭취법, 사용 순서, 권장 대상, 연령대, 설치 방식)"),
    WARNING("내가 보낸 상품 설명 이미지를 분석하여 상품의 보관법, 유통기한, 안전 주의, 알러지, 세척 등에 관한 정보를 알려줘. 특히, 식품이라면 알러지에 대한 정보는 필수로 알려줘. (ex. 보관법, 직사광선 피함, 유통기한, 세탁, 금지사항)"),
    SPECS("내가 보낸 상품 설명 이미지를 분석하여 상품의 색상, 크기, 무게, 용량, 호환성, 구매 선택지 등에 대한 정보를 알려줘. (ex. 크기, 무게, 용량, 호수, 색상, 옵션 구성, 커버력)"),
    CERTIFICATION("내가 보낸 상품 설명 이미지를 분석하여 상품의 상품의 인증, A/S, 포장, 브랜드, 배송, 마크 등에 대한 정보를 알려줘. (ex. KC인증, GMP, 비건, 식약처, 무상 A/S, 패키징)")
    ;

    private final String prompt;

    OutlineType(String prompt) {
        this.prompt = prompt;
    }
}
