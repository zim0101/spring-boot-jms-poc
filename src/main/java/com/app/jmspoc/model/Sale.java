package com.app.jmspoc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Sale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_generator")
    @SequenceGenerator(name = "sale_generator", sequenceName = "sale_seq")
    private Integer id;

    @ManyToOne
    private Product product;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

    private Date createdAt;

    public Sale(Integer id, Product product, Integer quantity, Double price, Date createdAt) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = createdAt;
    }

    public Sale() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}