package app.services;

import app.entity.external.data.product.domain.ProductExt;
import app.entity.external.data.product.repository.ProductExtRepository;
import app.entity.external.data.product.repository.ProductImageExtRepository;
import app.entity.local.data.product.domain.Product;
import app.entity.local.data.product.repository.ProductImageRepository;
import app.entity.local.data.product.repository.ProductRepository;
import app.entity.local.data.shoppingCard.domain.Discount;
import app.entity.local.data.shoppingCard.domain.ProductBucket;
import app.entity.local.data.shoppingCard.domain.ShoppingCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired private ProductRepository productRepository;
    @Autowired private ProductExtRepository productExtRepository;

    public void runService() {
        updateLocalProducts();
    }

    @Transactional
    public void updateLocalProducts() {
        Long version = new Date().getTime();
        try {
            List<Product> productList = productRepository.findAllByNameNotNull();
            if (productList.size() > 0) {
                productList.forEach(product -> {
                            product.setActive(false);
                            productRepository.save(product);
                        }
                );
            }

            List<ProductExt> productExtList = productExtRepository.findAllByNameNotNull();
            productExtList.forEach(productExt -> {
                        Product product = productRepository.findByBarcode(productExt.getBarcode());
                        if (product == null) {
                            product = new Product();
                        }
                        product.setBarcode(productExt.getBarcode());
                        product.setName(productExt.getName());
                        product.setCategory(productExt.getCategory());
                        product.setPrice(productExt.getPrice());
                        product.setStock(productExt.getStock());
                        product.setUm(productExt.getUm());
                        product.setVersion(version);
                        productRepository.save(product);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ShoppingCard initTestShoppingCard() {
        ProductBucket bucket = prepareTestBucket();
        Discount discount = prepareTestDiscount();
        return prepareTestShoppingCard(bucket, discount);
    }

    private ProductBucket prepareTestBucket() {
        ProductBucket bucket = new ProductBucket();
        List<Product> product = productRepository.findAllByNameNotNull();
        List<Product> productsBucket = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            productsBucket.add(product.get(0));
        }
        for (int i = 0; i < 16; i++) {
            productsBucket.add(product.get(5));
        }
        for (int i = 0; i < 151; i++) {
            productsBucket.add(product.get(4));
        }
        bucket.setProductList(productsBucket);
        return bucket;
    }

    private Discount prepareTestDiscount() {
        Discount discount = new Discount();
        discount.setId(1);
        discount.setValue(13312);
        return discount;
    }

    private ShoppingCard prepareTestShoppingCard(ProductBucket bucket, Discount discount) {
        ShoppingCard shoppingCard = new ShoppingCard();
        shoppingCard.setId(1);
        shoppingCard.setProductBucket(bucket);
        shoppingCard.setDiscount(discount);
        return shoppingCard;
    }

    private List<ProductExt> getAllExternalProducts() {
        return productExtRepository.findAllByNameNotNull();
    }
}
