package app.controllers;

import app.ControllersConfiguration;
import app.entity.external.data.auth.domain.Company;
import app.entity.external.data.auth.domain.User;
import app.entity.external.data.auth.repository.CompanyRepository;
import app.entity.external.data.auth.repository.UserRepository;
import app.entity.external.data.product.repository.ProductExtRepository;
import app.entity.local.data.auth.domain.Person;
import app.entity.local.data.auth.repository.PersonRepository;
import app.entity.local.data.product.repository.ProductRepository;
import app.jobs.checkConnectionJob.CheckInternetConnetctionJob;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class LoginController {

    @Qualifier("numSearch")
    @Autowired
    private ControllersConfiguration.ViewHolder view;
    boolean isLogget = false;
    public static Person currenUser = null;

    // Инъекции Spring
    @Autowired private PersonRepository personRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CompanyRepository companyRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductExtRepository productExtRepository;


    // Инъекции JavaFX
    @FXML private AnchorPane mainPane;

    @FXML private ImageView background;
    @FXML private ImageView logo;
    @FXML private ImageView editCompany;
    @FXML private ImageView clearName;
    @FXML private ImageView clearPass;
    @FXML private ImageView closeIcon;
    @FXML private Button updateProducts;

    @FXML private Label message;
    @FXML private Label compL;
    @FXML private Label loc1;
    @FXML private TextField company;
    @FXML private TextField name;
    @FXML private TextField pass;

    @SuppressWarnings("unchecked")
    @PostConstruct


    public void init() {

        initPersonDatabase();
        company.setText("company: " + getLastLoggetCompanyName());
        startTestJobActionPerformed();
//        updateLocalProducts();

    }


    @FXML
    public void initialize() {
        // Этап инициализации JavaFX
        background.setImage(new Image("/fxml/login/res/Background.png"));
        logo.setImage(new Image("/fxml/login/res/logo.png"));
        editCompany.setImage(new Image("/fxml/login/res/edit-icon.png"));
        clearName.setImage(new Image("/fxml/login/res/x.png"));
        clearPass.setImage(new Image("/fxml/login/res/x.png"));
        closeIcon.setImage(new Image("/fxml/login/res/close.png"));
    }


    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = (Stage) closeIcon.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void editCompanyAction(MouseEvent event) {
        company.setEditable(true);
        company.setText("");
    }
    @FXML
    private void clearNameAction(MouseEvent event) {
        name.setText("");
    }
    @FXML
    private void clearPassAction(MouseEvent event) {
        pass.setText("");
    }

    @FXML
    private void loginAction(ActionEvent event) throws SQLException, ClassNotFoundException {
    //todo enable Login Action. now just for fast screen redirecton
//        login();
//        currenUser = person;
        view.getView();
        mainPane.getScene().setRoot(view.getView());
    }

//    @FXML
//    private void loadScreen(String fxmlPath) {
//        Parent root = null;
//        try {
//            URL url = Paths.get("/fxml/numericSearch/numSearch.fxml").toUri().toURL();
//            root = FXMLLoader.load(getClass().getResource("/fxml/numericSearch/numSearch.fxml"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("ssssssssssssssss");
//        System.out.println(root);
//        mainPane.getScene().setRoot(root);
//    }

    private void login() {
        String companyName = company.getText();
        if (companyName.length() > 8 && companyName.substring(0, 7).equals("company")) {
            companyName = companyName.substring(8, companyName.length()).trim();
        }
        System.out.println("Company: " + companyName);
        System.out.println("User: " + name.getText());
        System.out.println("Password: " + pass.getText());

        Person person = personRepository.findByName(name.getText());
        System.out.println("Looking for person: " + name.getText());
        System.out.println("FOUNTD: " + person.getName());
        if (person != null) {
            if (verifyLocalUser(person)) {
                isLogget = true;
                currenUser = person;
            }
        } else {
            User user = userRepository.findByName(name.getText());
            if (user.getId() != null) {
                System.out.println("External USER FOUND: " + user.getName());
                Company company = companyRepository.findByUserId(user.getId());
                if (company != null) {
                    System.out.println("External USER COMPANY FOUND: " + company.getName());
                    if (verifyExternalUser(user, company)) {
                        Person personToSave = adoptExternalUser(company, user);
                        if (personToSave != null) {
                            isLogget = true;
                            currenUser = personToSave;
                        }
                    }
                }
            }
        }

        if (isLogget) {
            currenUser = person;
            view.getView();
            mainPane.getScene().setRoot(view.getView());
        } else {
            message.setText("Please, check input data...");
            compL.setText("You are NOT Logged IN");
        }
    }

    private boolean verifyLocalUser(Person person) {
        boolean isOK = false;
        if (person.getCompany().equals(company.getText())){
            System.out.println("company OK");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            if (encoder.matches(pass.getText(), person.getPassword())) {
                System.out.println("PASS OK");
                isOK = true;
            }
        }
        return isOK;
    }

    private boolean verifyExternalUser(User user, Company comp) {
        System.out.println("Starting external user verification");
        boolean isOK = false;
            if (comp.getName().equals(company.getText())) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
                if (encoder.matches(pass.getText(), user.getPassword())) {
                    isOK = true;
                }
            }
        return isOK;
    }

    private Person adoptExternalUser(Company company, User user) {
        Person person = new Person();
        person.setCompany(company.getName());
        person.setName(user.getName());
        person.setPassword(user.getPassword());
        personRepository.save(person);
        return person;
    }


    private void initPersonDatabase() {
        Person person = personRepository.findByName("magicdecore");
        if (person != null) {
            System.out.println("you are OK, continue");
            return;
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            String token = encoder.encode("admin");

            Person admin = new Person();
            admin.setCompany("admin");
            admin.setName("admin");
            admin.setPassword(token);
            admin.setLast(true);
            personRepository.save(admin);

            Person me = new Person();

            String token2 = encoder.encode("1q2w3e!Q@W#Er");
            me.setName("magicdecore");
            me.setCompany("uoo");
            me.setPassword(token2);
            me.setLast(false);
            personRepository.save(me);

        }
    }

    private String getLastLoggetCompanyName() {
        String comName = "";
        Person person = personRepository.findByLast(true);
        if (person != null) {
            comName = person.getCompany();
        }
        return comName;
    }

    public void startTestJobActionPerformed() {
        // Grab the Scheduler instance from the Factory
        boolean isSynced = false;
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // and start it off
            scheduler.start();
            // TODO add your handling code here:

            JobDetail job = newJob(CheckInternetConnetctionJob.class)
                    .withIdentity("job1", "group1")
                    .build();


            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            //todo Set time accodring to needed, now 5 for skowing example
                            .withIntervalInSeconds(5)
                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(CheckInternetConnetctionJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


//    private void updateLocalProducts() {
//        Long version = new Date().getTime();
//        System.out.println("1");
//        try {
//            List<Product> productList = productRepository.findAllByNameNotNull();
//            System.out.println(2);
//            if (productList.size() > 0) {
//                System.out.println(3);
//                productList.forEach(product -> {
//                            product.setActive(false);
//                            productRepository.save(product);
//                        }
//                );
//
//                System.out.println("local products unactivated");
//            }
//
//            List<ProductExt> productExtList = productExtRepository.findAllByNameNotNull();
//            productExtList.forEach(productExt -> {
//                        Product product = productRepository.findByBarcode(productExt.getBarcode());
//                        if (product == null) {
//                            product = new Product();
//                        }
//                        product.setName(productExt.getName());
//                        product.setCategory(productExt.getCategory());
//                        product.setPrice(productExt.getPrice());
//                        product.setStock(productExt.getStock());
//                        product.setUm(productExt.getUm());
//                        product.setVersion(version);
//                        product = productRepository.save(product);
//                        if (product.getId() != null) {
//                            System.out.println("Product " + product.getId() + " are updated");
//                        }
//                    }
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
