package app.entity.external.data.product.domain;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class ProductExt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "BARCODE")
    private Integer barcode;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "PRICE")
    private Integer price;
    @Column(name = "STOCK")
    private Integer stock;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "UM")
    private String um;
    @Column(name = "PRODUCT_IMAGE_ID")
    private Integer productImageId;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "PRODUCT")
    private  ProductImageExt productImage;

    public ProductExt() { }

    public ProductExt(String name, Integer barcode, Long version, Integer price, Integer stock, String category, String um) {
        this.name = name;
        this.barcode = barcode;
        this.version = version;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.um = um;
    }

    public ProductExt(String name, Integer barcode, Long version, Integer price, Integer stock, String category, String um, Integer productImageId) {
        this.name = name;
        this.barcode = barcode;
        this.version = version;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.um = um;
        this.productImageId = productImageId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBarcode() {
        return barcode;
    }

    public void setBarcode(Integer barcode) {
        this.barcode = barcode;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public Integer getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(Integer productImageId) {
        this.productImageId = productImageId;
    }

    public ProductImageExt getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImageExt productImage) {
        this.productImage = productImage;
    }
    @Override
    public String toString() {
        return "ProductExt{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", barcode=" + barcode +
                ", version=" + version +
                ", price=" + price +
                ", stock=" + stock +
                ", category='" + category + '\'' +
                ", um='" + um + '\'' +
                '}';
    }
}
