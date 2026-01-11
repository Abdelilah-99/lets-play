package lets.play.demo.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.data.annotation.Id;

@Document(collection = "products")
public class Product {
    @Id
    public String id;
    @NotBlank(message = "product required")
    @Size(min = 3, max = 15)
    public String name;
    @NotBlank(message = "description required")
    @Size(min = 5, max = 1000)
    public String description;
    @NotBlank(message = "price required")
    public Double price;
    public String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public Product() {
    }

    public Product(String id, String name, String description, Double price, String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.userId = userId;
    }
}
