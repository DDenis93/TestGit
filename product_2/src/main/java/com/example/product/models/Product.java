package com.example.product.models;

import com.example.product.enumm.Provider;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "product_site")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 20, message = "Имя должно содержать от 2 до 20 символов")
    @Column(name = "name", length = 20, //пустая строка - nullable, уникальность unique = false
            nullable = false, columnDefinition = "text") // тип данных "text"
    private String name;
    @Min(value = 0, message = "Минимальная цена 0")
    @Column(name = "price", length = 15, //пустая строка - nullable, уникальность unique = false
            nullable = false, columnDefinition = "int") // тип данных "text"
    private float price;
    @Min(value = 0, message = "Вес не может быть меньше 0")
    @Column(name = "weight", length = 15, //пустая строка - nullable, уникальность unique = false
            nullable = false, columnDefinition = "int") // тип данных "text"
    private int weight;
    @Column(name = "provider", length = 15, //пустая строка - nullable, уникальность unique = false
            nullable = false, columnDefinition = "text") // тип данных "text"
    private Provider provider;

    public Product(int id, String name, float price, int weight, Provider provider) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.provider = provider;
    }
    public Product(){ // пустой конструктор

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", provider=" + provider +
                '}';
    }
}
