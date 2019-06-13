package app.entity.local.data.shoppingCard.domain;

import javax.persistence.*;

@Entity
@Table(name = "SHOPPING_CARD")
public class ShoppingCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private ProductBucket productBucket;
    @OneToOne
    private Discount discount;

    public ShoppingCard() { }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductBucket getProductBucket() {
        return productBucket;
    }

    public void setProductBucket(ProductBucket productBucket) {
        this.productBucket = productBucket;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
