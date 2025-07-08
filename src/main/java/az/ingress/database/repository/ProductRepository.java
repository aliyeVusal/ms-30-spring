package az.ingress.database.repository;

import az.ingress.database.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    @Override
    List<ProductEntity> findAll();

}
