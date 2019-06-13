package app.habrahabr;

import app.entity.external.ExternalConfig;
import app.entity.external.data.auth.domain.User;
import app.entity.external.data.auth.repository.UserRepository;
import app.entity.local.LocalConfig;
import app.entity.local.data.auth.domain.Person;
import app.entity.local.data.auth.repository.PersonRepository;
import app.entity.local.data.product.domain.Product;
import app.entity.local.data.product.domain.ProductImage;
import app.entity.local.data.product.repository.ProductImageRepository;
import app.entity.local.data.product.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LocalConfig.class, ExternalConfig.class })
@TransactionConfiguration
public class JPAMultipleDBTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional("externalTransactionManager")
    public void whenCreatingUser_thenCreated() {
        User user = new User();
        user.setName("John");
        user.setCompanyId(1);
        user.setPassword("ASasAS");
        user.setVersion(new Date().getTime());
        user.setEmail("asd@asd.cxm");
        user.setEnabled(true);
        user = userRepository.save(user);

        assertNotNull(userRepository.findOne(user.getId()));
    }

    @Test
    @Transactional("localTransactionManager")
    public void testPersons() {
        Person person = new Person();
        person.setCompany("comp1");
        person.setName("FineMan");
        person.setPassword("sdasdasd");
        person.setLast(true);
        person = personRepository.save(person);
        assertNotNull(personRepository.findOne(person.getId()));
        assertNotNull(personRepository.findByName("FineMan"));
    }

    //    INSERT INTO PERSON VALUES( null,  'asdasd' ,'1', 'asdasd', 'asdasd')
    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUsersWithSameEmail_thenRollback() {

        User user2 = new User();
        user2.setName("Tom");
        user2.setCompanyId(1);
        user2.setPassword("ASasAS");
        try {
            user2 = userRepository.save(user2);
        } catch (DataIntegrityViolationException e) {
        }

        assertNull(userRepository.findOne(user2.getId()));
    }


    @Test
    public void whenCreatingProduct() {
        Product product = new Product("TestProduct", 1111, new Date().getTime(), 750, 8, "categoryA", "umBBB");
        product = productRepository.save(product);

        assertNotNull(productRepository.findOne(product.getId()));
    }

    @Test
//    @Transactional("localTransactionManager")
    public void whenCreatingImage() {
        File image = new File("/img/samsung.jpg");
        ProductImage productImage = new ProductImage();
        productImage.setName(image.getName());
        productImage.setType("jpg");
        try {
            productImage.setImage(Files.readAllBytes(image.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        productImage = productImageRepository.save(productImage);
        assertNotNull(productRepository.findOne(productImage.getId()));
    }

    @Test
//    @Transactional("localTransactionManager")
    public void whenCreatingProductWithImageAsID() {
        Long version =  new Date().getTime();
        File image = new File("P:\\Documents\\desktopseller\\sample-spring-boot-javafx-master\\src\\main\\resources\\img\\samsung.jpg");

        ProductImage productImage = new ProductImage();
        productImage.setName(image.getName());
        productImage.setType("jpg");
        productImage.setVersion(version);
        byte[] imageBytes = null;
        try {
            imageBytes = Files.readAllBytes(image.toPath());

            System.out.println("####################");
            System.out.println("IMAGE SIZE: " + imageBytes.length);
        } catch (IOException e) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!NO IMAGE DATA!!!");
            e.printStackTrace();
        }
        productImage.setImage(imageBytes);

        productImage = productImageRepository.save(productImage);
        System.out.println("IMAGE SAVEEED");


        Product product = new Product("TestProduct", 1111, version, 750, 8, "categoryA", "umBBB",
                productImage.getId());

        product = productRepository.save(product);

        assertNotNull(productRepository.findOne(product.getId()));
    }


}