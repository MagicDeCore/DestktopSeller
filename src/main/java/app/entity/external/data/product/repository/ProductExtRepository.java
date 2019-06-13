package app.entity.external.data.product.repository;

import app.entity.external.data.product.domain.ProductExt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductExtRepository extends CrudRepository<ProductExt, Integer> {
    List<ProductExt> findAll();
    List<ProductExt> findAllByNameNotNull();
}
