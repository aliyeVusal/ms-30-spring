package az.ingress.service.concrate;

import az.ingress.database.entity.ProductEntity;
import az.ingress.database.repository.ProductRepository;
import az.ingress.dto.request.ProductRequestDto;
import az.ingress.service.abstraction.PropagationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static az.ingress.mapper.ProductMapper.PRODUCT_MAPPER;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Service
@RequiredArgsConstructor
public class PropagationServiceHandler implements PropagationService {

    private final ProductRepository productRepository;

    @Transactional(propagation = REQUIRED)
    @Override
    public void insertWithRequired(ProductRequestDto productRequestDto, String productName) {
        productRequestDto.setProductName(productName);
        productRequestDto.setSerialNumber(UUID.randomUUID().toString());

        ProductEntity productEntity = PRODUCT_MAPPER.mapDtoToEntity(productRequestDto);
        productRepository.save(productEntity);
    }

    @Transactional(propagation = REQUIRES_NEW, isolation = SERIALIZABLE)
    @Override
    public void saveWithRequiresNew(ProductRequestDto productRequestDto) {
        ProductEntity productEntity = PRODUCT_MAPPER.mapDtoToEntity(productRequestDto);
        productRepository.save(productEntity);
    }

    @Transactional(propagation = SUPPORTS)
    @Override
    public void saveWithSupport(ProductRequestDto productRequestDto) {
        ProductEntity productEntity = PRODUCT_MAPPER.mapDtoToEntity(productRequestDto);
        productEntity.setSerialNumber(UUID.randomUUID().toString());
        productRepository.save(productEntity);
    }

}
