package com.webeye.backend.domain.rawmaterial.domain;

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

    @Column
    private String foodCd;

    @Column
    private String foodNm;

    @Column
    private String nutConSrtrQua;

    @Column
    private double enerc;

    @Column
    private double water;

    @Column
    private double prot;

    @Column
    private double fatce;

    @Column
    private double ash;

    @Column
    private double chocdf;

    @Column
    private double sugar;

    @Column
    private double fibtg;

    @Column
    private double ca;

    @Column
    private double fe;

    @Column
    private double p;

    @Column
    private double k;

    @Column
    private double nat;

    @Column
    private double vitaRae;

    @Column
    private double retol;

    @Column
    private double cartb;

    @Column
    private double thia;

    @Column
    private double ribf;

    @Column
    private double nia;

    @Column
    private double vitc;

    @Column
    private double vitd;

    @Column
    private double chole;

    @Column
    private double fasat;

    @Column
    private double fartn;

    @Builder
    private RawMaterial(Long id, String foodCd, String foodNm, String nutConSrtrQua, double enerc, double water, double prot, double fatce, double ash,
                        double chocdf, double sugar, double fibtg, double ca, double fe, double p, double k, double nat, double vitaRae, double retol,
                        double cartb, double thia, double ribf, double nia, double vitc, double vitd, double chole, double fasat, double fartn) {
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