package az.ingress.service;

import az.ingress.dto.request.ProductRequestDto;
import az.ingress.dto.response.ProductResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {

    ProductResponseDto getProduct(Long id);

    List<ProductResponseDto> getProductAll();

    void saveProduct(ProductRequestDto productRequestDto);

    void updateProduct(@RequestBody ProductRequestDto productRequestDto, @PathVariable Long id);

    void deleteProduct(Long id);

}
