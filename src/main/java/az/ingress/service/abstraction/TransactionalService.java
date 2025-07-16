package az.ingress.service.abstraction;

import az.ingress.dto.request.ProductRequestDto;

public interface TransactionalService {

    void createWithRollback(ProductRequestDto productRequestDto);

    void createWithNoRollback(ProductRequestDto productRequestDto, String productName);

    void callRequiresNew(ProductRequestDto productRequestDto);

    void callNested(ProductRequestDto productRequestDto);

}
