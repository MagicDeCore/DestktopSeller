package app.common;

import app.entity.external.ExternalConfig;
import app.entity.external.data.product.domain.ProductExt;
import app.entity.external.data.product.repository.ProductExtRepository;
import app.entity.local.LocalConfig;
import app.entity.local.data.product.domain.Product;
import app.entity.local.data.product.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LocalConfig.class, ExternalConfig.class })
@TransactionConfiguration
public class DevTests {
    @Autowired private ProductExtRepository productExtRepository;
    @Autowired private ProductRepository productRepository;

    @Test
    public void testProductsMigration() {
        Long version = new Date().getTime();
        List<Product> productList = productRepository.findAllByNameNotNull();
        productList.forEach(product -> {
                product.setActive(false);
                productRepository.save(product);
            }
        );

        List<ProductExt> productExtList = productExtRepository.findAllByNameNotNull();
        productExtList.forEach(productExt -> {
                Product product = productRepository.findByBarcode(productExt.getBarcode());
                if (product == null) {
                    product = new Product();
                }
                product.setName(productExt.getName());
                product.setCategory(productExt.getCategory());
                product.setPrice(productExt.getPrice());
                product.setStock(productExt.getStock());
                product.setUm(productExt.getUm());
                product.setVersion(version);
                product = productRepository.save(product);
            }
        );
    }

    @Test
    public void testProductService() {

    }
}
