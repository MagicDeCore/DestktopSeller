package app.entity.local.data.shoppingCard.domain;

import javax.persistence.*;

@Entity
@Table(name = "PUBLIC.DISCOUNT")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "VALUE")
    private int value;

    public Discount() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
