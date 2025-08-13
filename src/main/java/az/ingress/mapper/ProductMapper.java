package az.ingress.mapper;

import az.ingress.database.entity.ProductEntity;
import az.ingress.dto.request.ProductRequestDto;
import az.ingress.dto.response.ProductResponseDto;

import java.util.List;
import java.util.Optional;

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
                .status(productEntity.getStatus())
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
                .build();
    }

    public List<ProductResponseDto> mapEntityListToDtoList(List<ProductEntity> productEntityList) {
        return productEntityList.stream().map(this::mapEntityToDto).toList();
    }

    public void setProductEntity(ProductEntity productEntity, ProductRequestDto productRequestDto) {
        Optional.ofNullable(productRequestDto.getProductName()).ifPresent(productEntity::setProductName);
        Optional.ofNullable(productRequestDto.getSerialNumber()).ifPresent(productEntity::setSerialNumber);
        Optional.ofNullable(productRequestDto.getQuantity()).ifPresent(productEntity::setQuantity);
        Optional.ofNullable(productRequestDto.getFirstUseDate()).ifPresent(productEntity::setFirstUseDate);
        Optional.ofNullable(productRequestDto.getLastUseDate()).ifPresent(productEntity::setLastUseDate);
        Optional.ofNullable(productRequestDto.getType()).ifPresent(productEntity::setType);
        Optional.ofNullable(productRequestDto.getStatus()).ifPresent(productEntity::setStatus);
    }

}
