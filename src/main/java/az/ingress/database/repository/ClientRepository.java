package az.ingress.database.repository;

import az.ingress.database.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<CardEntity, Long> {
}
