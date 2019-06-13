package app.models;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ProductModel extends Pane {

     @FXML private ImageView minusProduct;
     @FXML private ImageView plusProduct;
     @FXML private Label productNameLbl;
     @FXML private Label productQntyLbl;

     public ProductModel(){}

     public ProductModel(ImageView minusProduct, ImageView plusProduct, Label productNameLbl, Label productQntyLbl) {
         this.minusProduct = minusProduct;
         this.plusProduct = plusProduct;
         this.productNameLbl = productNameLbl;
         this.productQntyLbl = productQntyLbl;
     }
}
