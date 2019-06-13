package app.entity.local.data.shoppingCard.repository;

import app.entity.local.data.shoppingCard.domain.ProductBucket;
import org.springframework.data.repository.CrudRepository;

public interface ProductBucketRepository extends CrudRepository<ProductBucket, Integer> {
}
