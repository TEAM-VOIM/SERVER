package com.webeye.backend.product.domain;


import com.webeye.backend.global.domain.BaseEntity;
import com.webeye.backend.product.domain.type.OutlineType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "product_detail",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"product_id", "type"})
        }
)
public class ProductDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private OutlineType outline;

    private String content;

    @Builder
    public ProductDetail(Product product, OutlineType outline, String content) {
        this.product = product;
        this.outline = outline;
        this.content = content;
    }

    public void associateWithProduct(Product product) {
        this.product = product;
        product.getDetails().add(this);
    }
}
