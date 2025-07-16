package az.ingress.service.concrate;

import az.ingress.database.entity.ProductEntity;
import az.ingress.database.repository.ProductRepository;
import az.ingress.dto.request.ProductRequestDto;
import az.ingress.service.abstraction.TransactionalService;
import az.ingress.service.abstraction.PropagationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static az.ingress.mapper.ProductMapper.PRODUCT_MAPPER;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionalServiceHandler implements TransactionalService {

    private final ProductRepository productRepository;
    private final PropagationService propagationService;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void createWithRollback(ProductRequestDto productRequestDto) {
        ProductEntity productEntity = PRODUCT_MAPPER.mapDtoToEntity(productRequestDto);
        productRepository.save(productEntity);
        throw new RuntimeException("rollback example");
    }

    @Transactional(noRollbackFor = RuntimeException.class, isolation = REPEATABLE_READ)
    @Override
    public void createWithNoRollback(ProductRequestDto productRequestDto, String productName) {
        ProductEntity productEntity = PRODUCT_MAPPER.mapDtoToEntity(productRequestDto);
        productRepository.save(productEntity);

        List<ProductEntity> productRepositoryAllFirst = findAll();
        log.info("Product list BEFORE insert: {}", productRepositoryAllFirst);

        propagationService.insertWithRequired(productRequestDto, productName);

        List<ProductEntity> productRepositoryAllLast = findAll();
        log.info("Product list AFTER insert: {}", productRepositoryAllLast);

        throw new RuntimeException("no rollback example");
    }

    @Transactional
    @Override
    public void callRequiresNew(ProductRequestDto productRequestDto) {
        propagationService.saveWithRequiresNew(productRequestDto);
        throw new RuntimeException("RequiresNew commit example");
    }

    @Transactional
    @Override
    public void callNested(ProductRequestDto productRequestDto) {
        propagationService.saveWithNested(productRequestDto);
        productRepository.save(PRODUCT_MAPPER.mapDtoToEntity(productRequestDto));
        throw new RuntimeException("Nested rollback example");
    }

    private List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

}
