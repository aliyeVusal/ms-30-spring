package az.ingress.service.abstraction;

import az.ingress.dto.request.ProductRequestDto;
import az.ingress.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto getProduct(Long id);

    List<ProductResponseDto> getProductAll();

    void saveProduct(ProductRequestDto productRequestDto);

    void updateProduct(ProductRequestDto productRequestDto, Long id);

    void deleteProduct(Long id);

}
