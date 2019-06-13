package app.entity.local.data.shoppingCard.domain;

import app.entity.local.data.auth.domain.Person;
import app.entity.local.data.product.domain.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PUBLIC.PRODUCT_BUCKET")
public class ProductBucket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Person person;
    @OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
    private List<Product> productList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }


}
