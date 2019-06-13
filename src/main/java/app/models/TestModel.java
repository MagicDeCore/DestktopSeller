package app.models;


import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class TestModel extends Pane {
    private Label name;
    private Label price;

    public TestModel() {   }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public Label getPrice() {
        return price;
    }

    public void setPrice(Label price) {
        this.price = price;
    }

}
