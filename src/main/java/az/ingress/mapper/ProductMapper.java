package az.ingress.mapper;

import az.ingress.database.entity.ProductEntity;
import az.ingress.dto.request.ProductRequestDto;
import az.ingress.dto.response.ProductResponseDto;

public enum ProductMapper {
    PRODUCT_MAPPER;

    public ProductResponseDto mapEntityToDto(ProductEntity productEntity) {
        return ProductResponseDto.builder()
                .id(productEntity.getId())
                .productName(productEntity.getProductName())
                .serialNumber(productEntity.getSerialNumber())
                .quantity(productEntity.getQuantity())
                .firstUseDate(productEntity.getFirstUseDate())
                .lastUseDate(productEntity.getLastUseDate())
                .type(productEntity.getType())
                .isDeleted(productEntity.isDeleted())
                .build();
    }

    public ProductEntity mapDtoToEntity(ProductRequestDto productRequestDto) {
        return ProductEntity.builder()
                .id(productRequestDto.getId())
                .productName(productRequestDto.getProductName())
                .serialNumber(productRequestDto.getSerialNumber())
                .quantity(productRequestDto.getQuantity())
                .firstUseDate(productRequestDto.getFirstUseDate())
                .lastUseDate(productRequestDto.getLastUseDate())
                .type(productRequestDto.getType())
                .isDeleted(productRequestDto.isDeleted())
                .build();
    }

}
