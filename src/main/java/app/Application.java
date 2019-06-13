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

    @Autowired private PersonRepository personRepository;

    @Value("${ui.title:JavaFX приложение}")//
    private String windowTitle;

//    @Qualifier("loginScreen")
    @Qualifier("loginScreen")
//    @Qualifier("shoppingTest")
    @Autowired
    private ControllersConfiguration.ViewHolder view;

    private double x, y;
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle(windowTitle);
        Scene scene = new Scene(view.getView());

        //todo разобраться с мерцанием при драге
//        Parent root = scene.getRoot();
//        root.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//             x = event.getSceneX();
//             y = event.getSceneY();
//            }
//        });
//        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                stage.setX(event.getSceneX() - x);
//                stage.setY(event.getSceneY() - y);
//            }
//        });
//        scene.setFill(Color.TRANSPARENT);
//        stage.initStyle(StageStyle.TRANSPARENT);


        stage.setScene(scene);
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();

        Person person = personRepository.findByName("admin");
        System.out.println("###PERSON: " + person);
        System.out.println("####################################");
    }

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

}
