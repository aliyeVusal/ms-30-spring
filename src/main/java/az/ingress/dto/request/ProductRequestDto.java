package az.ingress.dto.request;

import az.ingress.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private Long id;
    private String productName;
    private String serialNumber;
    private Integer quantity;
    private LocalDate firstUseDate;
    private LocalDate lastUseDate;
    private ProductType type;
    private boolean isDeleted;

}
