package az.ingress.service.abstraction;

import az.ingress.dto.request.ProductRequestDto;

public interface PropagationService {

    void insertWithRequired(ProductRequestDto productRequestDto, String productName);

    void saveWithRequiresNew(ProductRequestDto productRequestDto);

    void saveWithSupport(ProductRequestDto productRequestDto);

}
