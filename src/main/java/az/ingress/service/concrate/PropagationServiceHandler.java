package az.ingress.service.concrate;

import az.ingress.database.entity.ProductEntity;
import az.ingress.database.repository.ProductRepository;
import az.ingress.dto.request.ProductRequestDto;
import az.ingress.service.abstraction.PropagationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static az.ingress.mapper.ProductMapper.PRODUCT_MAPPER;

@Service
@RequiredArgsConstructor
public class PropagationServiceHandler implements PropagationService {

    private final ProductRepository productRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public void insertWithRequired(ProductRequestDto productRequestDto, String productName) {
        productRequestDto.setProductName(productName);
        productRequestDto.setSerialNumber(UUID.randomUUID().toString());

        ProductEntity productEntity = PRODUCT_MAPPER.mapDtoToEntity(productRequestDto);
        productRepository.save(productEntity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void saveWithRequiresNew(ProductRequestDto productRequestDto) {
        ProductEntity productEntity = PRODUCT_MAPPER.mapDtoToEntity(productRequestDto);
        productRepository.save(productEntity);
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void saveWithNested(ProductRequestDto productRequestDto) {
        ProductEntity productEntity = PRODUCT_MAPPER.mapDtoToEntity(productRequestDto);
        productRepository.save(productEntity);
    }

}
