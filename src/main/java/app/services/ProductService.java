package app.services;

import app.entity.local.data.shoppingCard.domain.ShoppingCard;
import org.springframework.stereotype.Component;

@Component
public interface ProductService {
    public void updateLocalProducts();
    public ShoppingCard initTestShoppingCard();

}
