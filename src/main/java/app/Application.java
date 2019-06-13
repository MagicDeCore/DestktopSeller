package app;

import app.entity.local.data.auth.domain.Person;
import app.entity.local.data.auth.repository.PersonRepository;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

    @Qualifier("loginScreen")
    @Autowired private ControllersConfiguration.ViewHolder view;

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(view.getView());
        stage.setScene(scene);
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

}
