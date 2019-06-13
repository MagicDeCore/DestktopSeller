package app;

import app.controllers.LoginController;
import app.controllers.NumericSearchController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ControllersConfiguration {

    @Bean(name = "loginScreen")
    public ViewHolder getMainView() throws IOException {
        return loadView("fxml/login/loginScreen.fxml");
    }

    @Bean(name = "numSearch")
    public ViewHolder getNumSearchView() throws IOException {
        return loadView("fxml/numericSearch/numSearch.fxml");
    }

    @Bean(name = "shoppingTest")
    public ViewHolder getShoppingCardView() throws IOException {
        return loadView("fxml/shoppingCard/shoppingTest.fxml");
    }

    @Bean
    public LoginController getLoginController() throws IOException {
        return (LoginController) getMainView().getController();
    }

    @Bean
    public NumericSearchController getNumericSearchController() throws IOException {
        return (NumericSearchController) getNumSearchView().getController();
    }

    @Bean
    public ShoppingCardController getShoppingCardController() throws IOException {
        return (ShoppingCardController) getShoppingCardView().getController();
    }

    public ViewHolder loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new ViewHolder(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    public class ViewHolder {
        private Parent view;
        private Object controller;

        public ViewHolder(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }
}