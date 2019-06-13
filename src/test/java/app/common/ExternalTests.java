package app.common;

import app.entity.external.ExternalConfig;
import app.entity.external.data.product.domain.ProductExt;
import app.entity.external.data.product.domain.ProductImageExt;
import app.entity.external.data.product.repository.ProductExtRepository;
import app.entity.external.data.product.repository.ProductImageExtRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ExternalConfig.class })
@TransactionConfiguration
public class ExternalTests {
    @Autowired
    private ProductExtRepository productExtRepository;
    @Autowired
    private ProductImageExtRepository productImageExtRepository;
    public void whenCreatingProduct() {
        ProductExt product = new ProductExt("TestProduct", 1111, new Date().getTime(), 750, 8, "categoryA", "umBBB");
        product = productExtRepository.save(product);

        assertNotNull(productExtRepository.findOne(product.getId()));
    }

    @Test
    @Transactional("localTransactionManager")
    public void whenCreatingImage() {
        File image = new File("/img/samsung.jpg");
        ProductImageExt productImage = new ProductImageExt();
        productImage.setName(image.getName());
        productImage.setType("jpg");
        try {
            productImage.setImage(Files.readAllBytes(image.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        productImage = productImageExtRepository.save(productImage);
        assertNotNull(productExtRepository.findOne(productImage.getId()));
    }

    @Test
    @Transactional("localTransactionManager")
    public void whenCreatingProductWithImageAsID() {
        Long version =  new Date().getTime();
        File image = new File("P:\\Documents\\desktopseller\\sample-spring-boot-javafx-master\\src\\main\\resources\\img\\samsung.jpg");

        ProductImageExt productImage = new ProductImageExt();
        productImage.setName(image.getName());
        productImage.setType("jpg");
        productImage.setVersion(version);
        byte[] imageBytes = null;
        try {
            imageBytes = Files.readAllBytes(image.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        productImage.setImage(imageBytes);
        productImage = productImageExtRepository.save(productImage);

        ProductExt product = new ProductExt("TestProduct", 1111, version, 750, 8, "categoryA", "umBBB",
                productImage.getId());
        product = productExtRepository.save(product);

        assertNotNull(productExtRepository.findOne(product.getId()));
    }

}
