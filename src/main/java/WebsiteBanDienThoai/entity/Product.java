package WebsiteBanDienThoai.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="product_name")
    private String name;
    @Column(name="product_price")
    private double price;
    @Column(name="product_quantity")
    private int quantity;

    @Column(name="product_image")
    private int image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
