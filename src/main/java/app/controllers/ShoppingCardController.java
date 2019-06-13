package app.controllers;


import app.models.TestModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;



@SuppressWarnings("SpringJavaAutowiringInspection")
public class ShoppingCardController {

    @FXML private GridPane productGrid;
    @FXML private Pane product;
    @FXML private Label name;
    @FXML private Label price;
    @FXML private Button add;


    public void init() {

    }


    @FXML
    public void initialize() {

    }

    int count = 0;
    @FXML
    private void addAction(ActionEvent event) {

        TestModel testModel = new TestModel();
//        product.setVisible(false);
//        productGrid.setVisible(false);
        GridPane gridPane = new GridPane();
        testModel.setName(new Label("Product" + count));
        testModel.setPrice(new Label("5$"));
        System.out.println("ADD: " + testModel.getName() );

        gridPane.addRow(count, testModel);

        productGrid.getChildren().clear();
        productGrid.addRow(count, testModel);
        productGrid.getChildren().setAll(gridPane);
        Scene scene = productGrid.getScene();

        scene.setRoot(productGrid);
        count++;

    }

}
