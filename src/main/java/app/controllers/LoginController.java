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
import app.services.JobService;
import app.services.LoginService;
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
    @Autowired private ControllersConfiguration.ViewHolder view;
    @Autowired private LoginService loginService;
    @Autowired private JobService jobService;

    @FXML private AnchorPane mainPane;

    @FXML private ImageView background;
    @FXML private ImageView logo;
    @FXML private ImageView editCompany;
    @FXML private ImageView clearName;
    @FXML private ImageView clearPass;
    @FXML private ImageView closeIcon;

    @FXML private TextField company;
    @FXML private TextField name;
    @FXML private TextField pass;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        loginService.initPersonDatabase();
        company.setText("company: " + loginService.getLastLoggetCompanyName());
    }


    @FXML
    public void initialize() {
        background.setImage(new Image("/fxml/login/res/Background.png"));
        logo.setImage(new Image("/fxml/login/res/logo.png"));
        editCompany.setImage(new Image("/fxml/login/res/edit-icon.png"));
        clearName.setImage(new Image("/fxml/login/res/x.png"));
        clearPass.setImage(new Image("/fxml/login/res/x.png"));
        closeIcon.setImage(new Image("/fxml/login/res/close.png"));
    }

    @FXML
    private void loginAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (loginService.loginUser(name.getText(), company.getText(), pass.getText())) {
            view.getView();
            mainPane.getScene().setRoot(view.getView());
            jobService.startTestJobActionPerformed();
        }
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

}
