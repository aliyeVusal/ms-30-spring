package az.ingress.service.concrate;

import az.ingress.database.entity.ProductEntity;
import az.ingress.database.repository.ProductRepository;
import az.ingress.dto.request.ProductRequestDto;
import az.ingress.dto.response.ProductResponseDto;
import az.ingress.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.enums.ProductStatus.DELETED;
import static az.ingress.mapper.ProductMapper.PRODUCT_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductServiceHandler implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto getProduct(Long id) {
        ProductEntity productEntity = findExistingProduct(id);
        return PRODUCT_MAPPER.mapEntityToDto(productEntity);
    }

    @Override
    public List<ProductResponseDto> getProductAll() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        return PRODUCT_MAPPER.mapEntityListToDtoList(productEntityList);
    }

    @Override
    public void saveProduct(ProductRequestDto productRequestDto) {
        ProductEntity productEntity = PRODUCT_MAPPER.mapDtoToEntity(productRequestDto);
        productRepository.save(productEntity);
    }

    @Override
    public void updateProduct(ProductRequestDto productRequestDto, Long id) {
        ProductEntity productEntity = findExistingProduct(id);
        PRODUCT_MAPPER.setProductEntity(productEntity, productRequestDto);
        productRepository.save(productEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity productEntity = findExistingProduct(id);
        productEntity.setStatus(DELETED);
        productRepository.save(productEntity);
    }

    private ProductEntity findExistingProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

}
