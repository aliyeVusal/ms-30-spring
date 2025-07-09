package az.ingress.service.impl;

import az.ingress.database.entity.ProductEntity;
import az.ingress.database.repository.ProductRepository;
import az.ingress.dto.request.ProductRequestDto;
import az.ingress.dto.response.ProductResponseDto;
import az.ingress.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.mapper.ProductMapper.PRODUCT_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto getProduct(Long id) {
        ProductEntity productEntity = findExistingProduct(id);
        return PRODUCT_MAPPER.mapEntityToDto(productEntity);
    }

    @Override
    public List<ProductResponseDto> getProductAll() {
        return productRepository.findAll().stream().map(PRODUCT_MAPPER::mapEntityToDto).toList();
    }

    @Override
    public void saveProduct(ProductRequestDto productRequestDto) {
        productRepository.save(PRODUCT_MAPPER.mapDtoToEntity(productRequestDto));
    }

    @Override
    public void updateProduct(ProductRequestDto productRequestDto, Long id) {
        ProductEntity productEntity = findExistingProduct(id);
        productEntity.setProductName(productRequestDto.getProductName());
        productRepository.save(productEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity productEntity = findExistingProduct(id);
        productEntity.setDeleted(true);
        productRepository.save(productEntity);
    }

    private ProductEntity findExistingProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

}
