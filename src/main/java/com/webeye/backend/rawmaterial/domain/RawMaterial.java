package com.webeye.backend.rawmaterial.domain;

import com.webeye.backend.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RawMaterial extends BaseEntity {

    @Id
    @Column(name = "raw_material_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_cd")
    private String foodCd; // 식품 코드

    @Column(name = "food_nm")
    private String foodNm; // 식품명

    @Column(name = "nut_con_srtr_qua")
    private String nutConSrtrQua; // 영양성분함량기준량

    @Column(name = "enerc")
    private Double enerc; // 에너지

    @Column(name = "water")
    private Double water; // 수분

    @Column(name = "prot")
    private Double prot; // 단백질

    @Column(name = "fatce")
    private Double fatce; // 지방

    @Column(name = "ash")
    private Double ash; // 회분

    @Column(name = "chocdf")
    private Double chocdf; // 탄수화물

    @Column(name = "sugar")
    private Double sugar; // 당류

    @Column(name = "fibtg")
    private Double fibtg; // 식이섬유

    @Column(name = "ca")
    private Double ca; // 칼슘

    @Column(name = "fe")
    private Double fe; // 철

    @Column(name = "p")
    private Double p; // 인

    @Column(name = "k")
    private Double k; // 칼륨

    @Column(name = "nat")
    private Double nat; // 나트륨

    @Column(name = "vita_rae")
    private Double vitaRae; // 비타민 A

    @Column(name = "retol")
    private Double retol; // 레티놀

    @Column(name = "cartb")
    private Double cartb; // 베타카르틴

    @Column(name = "thia")
    private Double thia; // 티아민

    @Column(name = "ribf")
    private Double ribf; // 리보플라빈

    @Column(name = "nia")
    private Double nia; // 니아신

    @Column(name = "vitc")
    private Double vitc; // 비타민 C

    @Column(name = "vitd")
    private Double vitd; // 비타민 D

    @Column(name = "chole")
    private Double chole; // 콜레스테롤

    @Column(name = "fasat")
    private Double fasat; // 포화지방산

    @Column(name = "fartn")
    private Double fartn; // 트랜스지방산

    @Builder
    private RawMaterial(Long id, String foodCd, String foodNm, String nutConSrtrQua, Double enerc, Double water, Double prot, Double fatce, Double ash,
                        Double chocdf, Double sugar, Double fibtg, Double ca, Double fe, Double p, Double k, Double nat, Double vitaRae, Double retol,
                        Double cartb, Double thia, Double ribf, Double nia, Double vitc, Double vitd, Double chole, Double fasat, Double fartn) {
        this.id = id;
        this.foodCd = foodCd;
        this.foodNm = foodNm;
        this.nutConSrtrQua = nutConSrtrQua;
        this.enerc = enerc;
        this.water = water;
        this.prot = prot;
        this.fatce = fatce;
        this.ash = ash;
        this.chocdf = chocdf;
        this.sugar = sugar;
        this.fibtg = fibtg;
        this.ca = ca;
        this.fe = fe;
        this.p = p;
        this.k = k;
        this.nat = nat;
        this.vitaRae = vitaRae;
        this.retol = retol;
        this.cartb = cartb;
        this.thia = thia;
        this.ribf = ribf;
        this.nia = nia;
        this.vitc = vitc;
        this.vitd = vitd;
        this.chole = chole;
        this.fasat = fasat;
        this.fartn = fartn;
    }
}