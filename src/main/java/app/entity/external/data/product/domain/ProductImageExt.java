package app.entity.external.data.product.domain;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_IMAGE")
public class ProductImageExt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TYPE")
    private String type;
    @Lob
    @Column(name = "IMAGE", columnDefinition = "BLOB")
    private byte[] image;

    public ProductImageExt() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
