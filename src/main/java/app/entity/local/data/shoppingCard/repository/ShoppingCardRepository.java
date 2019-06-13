package app.entity.local.data.shoppingCard.repository;

import app.entity.local.data.shoppingCard.domain.ShoppingCard;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCardRepository extends CrudRepository<ShoppingCard, Integer> {
}
