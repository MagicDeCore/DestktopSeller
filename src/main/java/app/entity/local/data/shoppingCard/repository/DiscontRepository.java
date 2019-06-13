package app.entity.local.data.shoppingCard.repository;

import app.entity.local.data.shoppingCard.domain.Discount;
import org.springframework.data.repository.CrudRepository;

public interface DiscontRepository extends CrudRepository<Discount, Integer> {

}
