package app.entity.local.data.product.repository;

import app.entity.local.data.product.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findByBarcode(Integer barcode);
    ArrayList<Product> findAllByNameNotNull();

}
