package az.ingress.database.entity;

import az.ingress.enums.ProductStatus;
import az.ingress.enums.ProductType;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@ToString
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String productName;

    private String serialNumber;

    private Integer quantity;

    private LocalDate firstUseDate;

    private LocalDate lastUseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType type;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ProductStatus status = ProductStatus.ACTIVE;

}
