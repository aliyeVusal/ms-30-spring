package az.ingress.controller;

import az.ingress.dto.request.ProductRequestDto;
import az.ingress.service.abstraction.TransactionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("v1/transactions")
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionalService transactionalService;

    @PostMapping("/test-rollback")
    @ResponseStatus(HttpStatus.CREATED)
    public void createWithRollback(@RequestBody ProductRequestDto productRequestDto) {
        transactionalService.createWithRollback(productRequestDto);
    }

    @PostMapping("/test-no-rollback/{productName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createWithNoRollback(@RequestBody ProductRequestDto productRequestDto, @PathVariable String productName) {
        transactionalService.createWithNoRollback(productRequestDto, productName);
    }

    @PostMapping("/test-requires-new")
    @ResponseStatus(HttpStatus.CREATED)
    public void callRequiresNew(@RequestBody ProductRequestDto productRequestDto) {
        transactionalService.callRequiresNew(productRequestDto);
    }

    @PostMapping("/test-support")
    @ResponseStatus(HttpStatus.CREATED)
    public void callSupport(@RequestBody ProductRequestDto productRequestDto) {
        transactionalService.callSupport(productRequestDto);
    }

}
