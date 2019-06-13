package app.controllers;

import app.entity.local.data.product.domain.Product;
import app.entity.local.data.product.repository.ProductRepository;
import app.entity.local.data.shoppingCard.domain.ShoppingCard;
import app.entity.local.data.shoppingCard.repository.DiscontRepository;
import app.entity.local.data.shoppingCard.repository.ProductBucketRepository;
import app.entity.local.data.shoppingCard.repository.ShoppingCardRepository;
import app.services.ProductService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;


@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class NumericSearchController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DiscontRepository discontRepository;
    @Autowired
    private ProductBucketRepository productBucketRepository;
    @Autowired
    private ShoppingCardRepository shoppingCardRepository;


    public ShoppingCard getShoppingCard() {
        return shoppingCard;
    }

    public void setShoppingCard(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
    }

    private ShoppingCard shoppingCard;

    private int counter = 0;

    @FXML private Button num1;
    @FXML private Button num2;
    @FXML private Button num3;
    @FXML private Button num4;
    @FXML private Button num5;
    @FXML private Button num6;
    @FXML private Button num7;
    @FXML private Button num8;
    @FXML private Button num9;
    @FXML private Button num0;
    @FXML private Button numPoint;
    @FXML private Button numBack;

    @FXML private Button enterButton;

    @FXML private TextField searchProd;
    @FXML private TextField quanty;
    private TextField activeTextField;
    @FXML private Button updateProducts;

    @FXML private ImageView background;
    @FXML private GridPane productGrid;

    public GridPane getProductGrid() { return productGrid; } public void setProductGrid(GridPane productGrid) { this.productGrid = productGrid; }

    @FXML private Button client;
    @FXML private Button save;
    @FXML private Label totalLabel;
    @FXML private Label discountLabel;
    @FXML private Label subTotalLabel;
    @FXML private Label ron1;
    @FXML private Label ron2;
    @FXML private Label ron3;
    @FXML private Pane headerss;

    @FXML private Pane productPane1;
    @FXML private ImageView minusProduct1;
    @FXML private ImageView plusProduct1;
    @FXML private Label productName1;
    @FXML private Label productQnty1;
    @FXML private Label um1;
    @FXML private Label barCode1;
    @FXML private Label singlePrice1;
    @FXML private Label totalPrice1;
    private double singlePriceValue1 = 0 ;
    private double totalPriceValue1 = 0;

    @FXML private Pane productPane2;
    @FXML private ImageView minusProduct2;
    @FXML private ImageView plusProduct2;
    @FXML private Label productName2;
    @FXML private Label productQnty2;
    @FXML private Label um2;
    @FXML private Label barCode2;
    @FXML private Label singlePrice2;
    @FXML private Label totalPrice2;
    private double singlePriceValue2 = 0 ;
    private double totalPriceValue2 = 0;

    @FXML private Pane productPane3;
    @FXML private ImageView minusProduct3;
    @FXML private ImageView plusProduct3;
    @FXML private Label productName3;
    @FXML private Label productQnty3;
    @FXML private Label um3;
    @FXML private Label barCode3;
    @FXML private Label singlePrice3;
    @FXML private Label totalPrice3;
    private double singlePriceValue3 = 0 ;
    private double totalPriceValue3 = 0;

    @FXML private Pane productPane4;
    @FXML private ImageView minusProduct4;
    @FXML private ImageView plusProduct4;
    @FXML private Label productName4;
    @FXML private Label productQnty4;
    @FXML private Label um4;
    @FXML private Label barCode4;
    @FXML private Label singlePrice4;
    @FXML private Label totalPrice4;
    private double singlePriceValue4 = 0 ;
    private double totalPriceValue4 = 0;

    @FXML private Pane productPane5;
    @FXML private ImageView minusProduct5;
    @FXML private ImageView plusProduct5;
    @FXML private Label productName5;
    @FXML private Label productQnty5;
    @FXML private Label um5;
    @FXML private Label barCode5;
    @FXML private Label singlePrice5;
    @FXML private Label totalPrice5;
    private double singlePriceValue5 = 0;
    private double totalPriceValue5 = 0;

    @FXML private Pane productPane6;
    @FXML private ImageView minusProduct6;
    @FXML private ImageView plusProduct6;
    @FXML private Label productName6;
    @FXML private Label productQnty6;
    @FXML private Label um6;
    @FXML private Label barCode6;
    @FXML private Label singlePrice6;
    @FXML private Label totalPrice6;
    private double singlePriceValue6 = 0;
    private double totalPriceValue6 = 0;

    @FXML private Pane productPane7;
    @FXML private ImageView minusProduct7;
    @FXML private ImageView plusProduct7;
    @FXML private Label productName7;
    @FXML private Label productQnty7;
    @FXML private Label um7;
    @FXML private Label barCode7;
    @FXML private Label singlePrice7;
    @FXML private Label totalPrice7;
    private double singlePriceValue7 = 0;
    private double totalPriceValue7 = 0;

    @FXML private Pane discountPain;
    @FXML private Label discount;
    @FXML private Label discountAdded;
    @FXML private Label discountCount;

    @FXML private Label subtotalCountVal;
    @FXML private Label discountCountVal;
    @FXML private Label totalTotalPrice;
    private double totalTotalPriceValue = 0;







    ArrayList list;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {

    }


    @FXML
    public void initialize() {
        // Этап инициализации JavaFX
        background.setImage(new Image("/fxml/numericSearch/res/BackgroundMain.png"));


        minusProduct1.setImage(new Image("/fxml/numericSearch/res/ShopingCart/-.png"));
        plusProduct1.setImage(new Image("/fxml/numericSearch/res/ShopingCart/+.png"));
        plusProduct1.setCursor(Cursor.HAND);
        minusProduct1.setCursor(Cursor.HAND);
        productQnty1.setText("");
        productName1.setText("");
        singlePrice1.setText("");
        totalPrice1.setText("");

        minusProduct2.setImage(new Image("/fxml/numericSearch/res/ShopingCart/-.png"));
        plusProduct2.setImage(new Image("/fxml/numericSearch/res/ShopingCart/+.png"));
        plusProduct2.setCursor(Cursor.HAND);
        minusProduct2.setCursor(Cursor.HAND);

        minusProduct3.setImage(new Image("/fxml/numericSearch/res/ShopingCart/-.png"));
        plusProduct3.setImage(new Image("/fxml/numericSearch/res/ShopingCart/+.png"));
        plusProduct3.setCursor(Cursor.HAND);
        minusProduct3.setCursor(Cursor.HAND);

        minusProduct4.setImage(new Image("/fxml/numericSearch/res/ShopingCart/-.png"));
        plusProduct4.setImage(new Image("/fxml/numericSearch/res/ShopingCart/+.png"));
        plusProduct4.setCursor(Cursor.HAND);
        minusProduct4.setCursor(Cursor.HAND);

        minusProduct5.setImage(new Image("/fxml/numericSearch/res/ShopingCart/-.png"));
        plusProduct5.setImage(new Image("/fxml/numericSearch/res/ShopingCart/+.png"));
        plusProduct5.setCursor(Cursor.HAND);
        minusProduct5.setCursor(Cursor.HAND);

        minusProduct6.setImage(new Image("/fxml/numericSearch/res/ShopingCart/-.png"));
        plusProduct6.setImage(new Image("/fxml/numericSearch/res/ShopingCart/+.png"));
        plusProduct6.setCursor(Cursor.HAND);
        minusProduct6.setCursor(Cursor.HAND);

        minusProduct7.setImage(new Image("/fxml/numericSearch/res/ShopingCart/-.png"));
        plusProduct7.setImage(new Image("/fxml/numericSearch/res/ShopingCart/+.png"));
        plusProduct7.setCursor(Cursor.HAND);
        minusProduct7.setCursor(Cursor.HAND);

        activeTextField = searchProd;

        discountAdded.setVisible(false);
        discount.setVisible(false);
        discountCountVal.setVisible(false);
        totalTotalPrice.setVisible(false);
        totalLabel.setVisible(false);
        discountLabel.setVisible(false);
        ron1.setVisible(false);
        ron2.setVisible(false);
        ron3.setVisible(false);
        subTotalLabel.setVisible(false);
        subtotalCountVal.setVisible(false);
        headerss.setVisible(false);

        myInit();
        System.out.println("START TIME FXML INIT: " + new Date().getTime());

        System.out.println("GRID SIZE: " + productGrid.getChildren().size());

    }

    private void myInit() {
        initProductGrid();
    }

    private void initProductGrid() {
        for (int i = 0; i < 8; i++) {
            productGrid.getChildren().get(i).setVisible(false);
        }
    }

    @FXML
    private void openShoppingCard() {
        setShoppingCard();
    }

    @FXML
    private void setActiveSearch(MouseEvent mouseEvent) {
        String tmp = searchProd.getText();
        activeTextField = searchProd;
        activeTextField.setText(tmp);
    }


    @FXML
    private void setActiveQuanty(MouseEvent mouseEvent) {
        activeTextField = quanty;
        activeTextField.setText("");

    }

    @FXML
    private void numAction1(ActionEvent event) {
        final String val = "1";
        if (activeTextField.getText() != null) {
            activeTextField.setText(searchProd.getText() + val);
        } else {
            activeTextField.setText(val);
        }
    }

    @FXML
    private void numAction2(ActionEvent event) {
        final String val = "2";
        if (activeTextField.getText() != null) {
            activeTextField.setText(activeTextField.getText() + val);
        } else {
            activeTextField.setText(val);
        }
        System.out.println("2");
    }

    @FXML
    private void numAction3(ActionEvent event) {
        final String val = "3";
        if (activeTextField.getText() != null) {
            activeTextField.setText(activeTextField.getText() + val);
        } else {
            activeTextField.setText(val);
        }
        System.out.println("3");
    }

    @FXML
    private void numAction4(ActionEvent event) {
        final String val = "4";
        if (activeTextField.getText() != null) {
            activeTextField.setText(activeTextField.getText() + val);
        } else {
            activeTextField.setText(val);
        }
        System.out.println("4");
    }

    @FXML
    private void numAction5(ActionEvent event) {
        final String val = "5";
        if (activeTextField.getText() != null) {
            activeTextField.setText(activeTextField.getText() + val);
        } else {
            activeTextField.setText(val);
        }
        System.out.println("5");
    }

    @FXML
    private void numAction6(ActionEvent event) {
        final String val = "6";
        if (activeTextField.getText() != null) {
            activeTextField.setText(activeTextField.getText() + val);
        } else {
            activeTextField.setText(val);
        }
        System.out.println("6");
    }

    @FXML
    private void numAction7(ActionEvent event) {
        final String val = "7";
        if (activeTextField.getText() != null) {
            activeTextField.setText(activeTextField.getText() + val);
        } else {
            activeTextField.setText(val);
        }
        System.out.println("7");
    }

    @FXML
    private void numAction8(ActionEvent event) {
        final String val = "8";
        if (activeTextField.getText() != null) {
            activeTextField.setText(activeTextField.getText() + val);
        } else {
            activeTextField.setText(val);
        }
        System.out.println("8");
    }

    @FXML
    private void numAction9(ActionEvent event) {
        final String val = "9";
        if (activeTextField.getText() != null) {
            activeTextField.setText(activeTextField.getText() + val);
        } else {
            activeTextField.setText(val);
        }
        System.out.println("9");
    }

    @FXML
    private void numAction0(ActionEvent event) {
        final String val = "0";
        if (activeTextField.getText() != null) {
            activeTextField.setText(activeTextField.getText() + val);
        } else {
            activeTextField.setText(val);
        }
        System.out.println("0");
    }
    @FXML
    private void numActionPoint(ActionEvent event) {
        final String val = ".";
        if (activeTextField.getText() != null) {
            activeTextField.setText(activeTextField.getText() + val);
        } else {
            activeTextField.setText(val);
        }
        System.out.println("POINT");
    }
    @FXML
    private void numActionBack(ActionEvent event) {
        activeTextField.setText(activeTextField.getText().substring(0, activeTextField.getText().length() - 1));
        System.out.println("BACK");
    }

    @FXML
    private void enterButtonAction(ActionEvent event) {

        setShoppingCard();
        addProductToCardByBarCode(searchProd.getText(), quanty.getText());
        drowShoppingCard();
    }

    @FXML
    private void updateProductsAction(ActionEvent event) {
        System.out.println("OOP");
        productService.updateLocalProducts();
    }

    private void setShoppingCard() {
        initShoppingCard();
    }

    @FXML
    private void initShoppingCard() {
        shoppingCard = productService.initTestShoppingCard();
        System.out.println("SIZE: " + shoppingCard.getProductBucket().getProductList().size());
        drowShoppingCard();
    }

    private void drowShoppingCard() {
        discountAdded.setVisible(true);
        discount.setVisible(true);
        discountCountVal.setVisible(true);
        totalTotalPrice.setVisible(true);
        totalLabel.setVisible(true);
        discountLabel.setVisible(true);
        ron1.setVisible(true);
        ron2.setVisible(true);
        ron3.setVisible(true);
        subTotalLabel.setVisible(true);
        subtotalCountVal.setVisible(true);
        headerss.setVisible(true);

        ArrayList<Product> products = (ArrayList<Product>) shoppingCard.getProductBucket().getProductList();
        ArrayList<Product> uniqeProductList = new ArrayList<Product>();
        for (int i = 0; i < products.size(); i++) {
            if (!uniqeProductList.contains(products.get(i))) {
                uniqeProductList.add(products.get(i));
            }
        }

        uniqeProductList.forEach(pr->{
            System.out.println("PROD: " + pr);
        });
        int productCount = uniqeProductList.size();
        for (int i = 0; i < productCount; i++) {
            productGrid.getChildren().get(i).setVisible(true);
            Product product = uniqeProductList.get(i);
            int count = calculateProductCount(products, product);
            if(i == 0) {
                productName1.setText(product.getName());
                productQnty1.setText("" + count);
                singlePrice1.setText("" + product.getPrice() + ".0");
                totalPrice1.setText("" + (product.getPrice() * count) + ".0");
                singlePriceValue1 = Double.parseDouble(singlePrice1.getText());//.substring(0, singlePrice1.getText().length() -3));
                totalPriceValue1 = Double.parseDouble(totalPrice1.getText());//.substring(0, totalPrice1.getText().length() -3));
                um1.setText(product.getUm());
                barCode1.setText("" + product.getBarcode());

                if (shoppingCard.getDiscount() != null && i == productCount -1) {
                    System.out.println("discount: " + i);
                    setDiscount(i);
                }
            } else if (i == 1) {
                productName2.setText(product.getName());
                productQnty2.setText("" + count);
                singlePrice2.setText("" + product.getPrice() + ".0");
                totalPrice2.setText("" + (product.getPrice() * count) + ".0");
                singlePriceValue2 = Double.parseDouble(singlePrice2.getText());//.substring(0, singlePrice2.getText().length() -3));
                totalPriceValue2 = Double.parseDouble(totalPrice2.getText());//.substring(0, totalPrice2.getText().length() -3));
                um2.setText(product.getUm());
                barCode2.setText("" + product.getBarcode());
                if (shoppingCard.getDiscount() != null && i == productCount -1) {
                    System.out.println("discount: " + i);
                    setDiscount(i);
                }
            } else if (i == 2) {
                productName3.setText(product.getName());
                productQnty3.setText("" + count);
                singlePrice3.setText("" + product.getPrice() + ".0");
                totalPrice3.setText("" + product.getPrice() * count + ".0");
                singlePriceValue3 = Double.parseDouble(singlePrice3.getText());//.substring(0, singlePrice3.getText().length() -3));
                totalPriceValue3 = Double.parseDouble(totalPrice3.getText());//.substring(0, totalPrice3.getText().length() -3));
                um3.setText(product.getUm());
                barCode3.setText("" + product.getBarcode());
                if (shoppingCard.getDiscount() != null && i == productCount -1) {
                    System.out.println("discount: " + i);
                    setDiscount(i);
                }
            } else if (i == 3) {
                productName4.setText(product.getName());
                productQnty4.setText("" + count);
                singlePrice4.setText("" + product.getPrice() + ".0");
                totalPrice4.setText("" + product.getPrice() * count + ".0");
                singlePriceValue4 = Double.parseDouble(singlePrice4.getText());//.substring(0, singlePrice4.getText().length() -3));
                totalPriceValue4 = Double.parseDouble(totalPrice4.getText());//.substring(0, totalPrice4.getText().length() -3));
                um4.setText(product.getUm());
                barCode4.setText("" + product.getBarcode());
                if (shoppingCard.getDiscount() != null && i == (productCount -1)) {
                    System.out.println("discount: " + i);
                    setDiscount(i);
                }
            } else if (i == 4) {
                productName5.setText(product.getName());
                productQnty5.setText("" + count);
                singlePrice5.setText("" + product.getPrice() + ".0");
                totalPrice5.setText("" + product.getPrice() * count + ".0");
                singlePriceValue5 = Double.parseDouble(singlePrice5.getText());//.substring(0, singlePrice5.getText().length() -3));
                totalPriceValue5 = Double.parseDouble(totalPrice5.getText());//.substring(0, totalPrice5.getText().length() -3));
                um5.setText(product.getUm());
                barCode5.setText("" + product.getBarcode());
                if (shoppingCard.getDiscount() != null && i == productCount -1) {
                    System.out.println("discount: " + i);
                    setDiscount(i);
                }
            } else if (i == 5) {
                productName6.setText(product.getName());
                productQnty6.setText("" + count);
                singlePrice6.setText("" + product.getPrice() + ".0");
                totalPrice6.setText("" + product.getPrice() * count + ".0");
                singlePriceValue6 = Double.parseDouble(singlePrice6.getText());//.substring(0, singlePrice6.getText().length() -3));
                totalPriceValue6 = Double.parseDouble(totalPrice6.getText());//.substring(0, totalPrice6.getText().length() -3));
                um6.setText(product.getUm());
                barCode6.setText("" + product.getBarcode());

                if (shoppingCard.getDiscount() != null && i == productCount -1) {
                    System.out.println("discount: " + i);
                    setDiscount(i);
                }
            } else if (i == 6) {
                productName7.setText(product.getName());
                productQnty7.setText("" + count);
                singlePrice7.setText("" + product.getPrice() + ".0");
                totalPrice7.setText("" + product.getPrice() * count + ".0");
                singlePriceValue7 = Double.parseDouble(singlePrice7.getText());//.substring(0, singlePrice7.getText().length() -2));
                totalPriceValue7 = Double.parseDouble(totalPrice7.getText());//.substring(0, totalPrice7.getText().length() -3));
                um7.setText(product.getUm());
                barCode7.setText("" + product.getBarcode());

                if (shoppingCard.getDiscount() != null && i == productCount -1) {
                    System.out.println("discount: " + i);
                    setDiscount(i);
                }
            }
            System.out.println(productGrid.getChildren().get(i).getId());
        }
    }

    private void setDiscount(int index) {
            discountCountVal.setText("" + shoppingCard.getDiscount().getValue() + ",00");
            discount.setText("!");
            discountAdded.setText("Discount");

//            Node child = productGrid.getChildren().get(7);
//            productGrid.getChildren().remove(7);
//            productGrid.getChildren().set(index + 1, child);
            productGrid.getChildren().get(7).setVisible(true);

    }

    private int calculateProductCount(ArrayList<Product> products, Product product) {
        int count = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(product.getName())) {
                count++;
            }
        }
        return count;
    }

    @FXML
    private void minusProductAction1() {
        if (totalPriceValue1 > 0) {
            Product product = productRepository.findByBarcode(Integer.valueOf(barCode1.getText()));
            shoppingCard.getProductBucket().getProductList().remove(product);
            productQnty1.setText("" + (Integer.parseInt(productQnty1.getText()) - 1));
            totalPriceValue1 = totalPriceValue1 - singlePriceValue1;
            totalPrice1.setText("" + totalPriceValue1);
        }
        calculateTotals();
    }

    @FXML
    private void minusProductAction2() {
        if (totalPriceValue2 > 0) {
            Product product = productRepository.findByBarcode(Integer.valueOf(barCode2.getText()));
            shoppingCard.getProductBucket().getProductList().remove(product);
            productQnty2.setText("" + (Integer.parseInt(productQnty2.getText()) - 1));
            totalPriceValue2 = totalPriceValue2 - singlePriceValue2;
            totalPrice2.setText("" + totalPriceValue2);
        }
        calculateTotals();
    }

    @FXML
    private void minusProductAction3() {
        if (totalPriceValue3 > 0) {
            Product product = productRepository.findByBarcode(Integer.valueOf(barCode3.getText()));
            shoppingCard.getProductBucket().getProductList().remove(product);
            productQnty3.setText("" + (Integer.parseInt(productQnty3.getText()) - 1));
            totalPriceValue3 = totalPriceValue3 - singlePriceValue3;
            totalPrice3.setText("" + totalPriceValue3);
        }
        calculateTotals();
    }
    @FXML
    private void minusProductAction4() {
        if (totalPriceValue4 > 0) {
            Product product = productRepository.findByBarcode(Integer.valueOf(barCode4.getText()));
            shoppingCard.getProductBucket().getProductList().remove(product);
            productQnty4.setText("" + (Integer.parseInt(productQnty4.getText()) - 1));
            totalPriceValue4 = totalPriceValue4 - singlePriceValue4;
            totalPrice4.setText("" + totalPriceValue4);
        }

        calculateTotals();
    }

    @FXML
    private void minusProductAction5() {
        if (totalPriceValue5 > 0) {
            Product product = productRepository.findByBarcode(Integer.valueOf(barCode5.getText()));
            shoppingCard.getProductBucket().getProductList().remove(product);
            productQnty5.setText("" + (Integer.parseInt(productQnty5.getText()) - 1));
            totalPriceValue5 = totalPriceValue5 - singlePriceValue5;
            totalPrice5.setText("" + totalPriceValue5);
        }

        calculateTotals();
    }
    @FXML
    private void minusProductAction6() {
        if (totalPriceValue6 > 0) {
            Product product = productRepository.findByBarcode(Integer.valueOf(barCode6.getText()));
            shoppingCard.getProductBucket().getProductList().remove(product);
            productQnty6.setText("" + (Integer.parseInt(productQnty6.getText()) - 1));
            totalPriceValue6 = totalPriceValue6 - singlePriceValue6;
            totalPrice6.setText("" + totalPriceValue6);
        }

        calculateTotals();
    }
    @FXML
    private void minusProductAction7() {
        if (totalPriceValue7 > 0) {
            Product product = productRepository.findByBarcode(Integer.valueOf(barCode7.getText()));
            shoppingCard.getProductBucket().getProductList().remove(product);
            productQnty7.setText("" + (Integer.parseInt(productQnty7.getText()) - 1));
            totalPriceValue7 = totalPriceValue7 - singlePriceValue7;
            totalPrice7.setText("" + totalPriceValue7);
        }

        calculateTotals();
    }


    @FXML
    private void plusProductAction1() {
        Product product = productRepository.findByBarcode(Integer.valueOf(barCode1.getText()));
        shoppingCard.getProductBucket().getProductList().add(product);
        productQnty1.setText("" + (Integer.parseInt(productQnty1.getText()) + 1));
        totalPriceValue1 = totalPriceValue1 + singlePriceValue1;
        totalPrice1.setText("" + totalPriceValue1);

        calculateTotals();
    }
    @FXML
    private void plusProductAction2() {
        Product product = productRepository.findByBarcode(Integer.valueOf(barCode2.getText()));
        shoppingCard.getProductBucket().getProductList().add(product);
        productQnty2.setText("" + (Integer.parseInt(productQnty2.getText()) + 1));
        totalPriceValue2 = totalPriceValue2 + singlePriceValue2;
        totalPrice2.setText("" + totalPriceValue2);

        calculateTotals();
    }

    @FXML
    private void plusProductAction3() {
        Product product = productRepository.findByBarcode(Integer.valueOf(barCode3.getText()));
        shoppingCard.getProductBucket().getProductList().add(product);
        productQnty3.setText("" + (Integer.parseInt(productQnty3.getText()) + 1));
        totalPriceValue3 = totalPriceValue3 + singlePriceValue3;
        totalPrice3.setText("" + totalPriceValue3);

        calculateTotals();
    }
    @FXML
    private void plusProductAction4() {
        Product product = productRepository.findByBarcode(Integer.valueOf(barCode4.getText()));
        shoppingCard.getProductBucket().getProductList().add(product);
        productQnty4.setText("" + (Integer.parseInt(productQnty4.getText()) + 1));
        totalPriceValue4 = totalPriceValue4 + singlePriceValue4;
        totalPrice4.setText("" + totalPriceValue4);


        calculateTotals();
    }

    @FXML
    private void plusProductAction5() {
        Product product = productRepository.findByBarcode(Integer.valueOf(barCode5.getText()));
        shoppingCard.getProductBucket().getProductList().add(product);
        productQnty5.setText("" + (Integer.parseInt(productQnty5.getText()) + 1));
        totalPriceValue5 = totalPriceValue5 + singlePriceValue5;
        totalPrice5.setText("" + totalPriceValue5);

        calculateTotals();
    }
    @FXML
    private void plusProductAction6() {
        Product product = productRepository.findByBarcode(Integer.valueOf(barCode6.getText()));
        shoppingCard.getProductBucket().getProductList().add(product);
        productQnty6.setText("" + (Integer.parseInt(productQnty6.getText()) + 1));
        totalPriceValue6 = totalPriceValue6 + singlePriceValue6;
        totalPrice6.setText("" + totalPriceValue6);

        calculateTotals();
    }
    @FXML
    private void plusProductAction7() {
        Product product = productRepository.findByBarcode(Integer.valueOf(barCode7.getText()));
        shoppingCard.getProductBucket().getProductList().add(product);
        productQnty7.setText("" + (Integer.parseInt(productQnty7.getText()) + 1));
        totalPriceValue7 = totalPriceValue7 + singlePriceValue7;
        totalPrice7.setText("" + totalPriceValue7);

        calculateTotals();
    }

    @FXML
    private void saveAction() {
        shoppingCard.setDiscount(discontRepository.save(shoppingCard.getDiscount()));
        shoppingCard.setProductBucket(productBucketRepository.save(shoppingCard.getProductBucket()));
        shoppingCard = shoppingCardRepository.save(shoppingCard);
    }

    private void calculateTotals() {
        totalTotalPriceValue = (long) (totalPriceValue1 + totalPriceValue2 + totalPriceValue3 + totalPriceValue4 + totalPriceValue5 + totalPriceValue6 + totalPriceValue7);
        totalTotalPrice.setText("" + totalTotalPriceValue);
    }

    private void addProductToCardByBarCode(String barCode, String qnty){
        if (barCode.equalsIgnoreCase("") || barCode.isEmpty())
            if (qnty.equalsIgnoreCase("") || qnty.isEmpty()) {
                qnty = "1";
            }

        Product product = productRepository.findByBarcode(Integer.parseInt(barCode));
        if (product != null) {
            for (int i = 0; i < Integer.parseInt(qnty); i++) {
                shoppingCard.getProductBucket().getProductList().add(product);
            }
        }
    }

}
