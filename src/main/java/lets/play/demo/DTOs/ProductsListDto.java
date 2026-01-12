package lets.play.demo.DTOs;

import java.util.List;

import lets.play.demo.Entity.Product;

public record ProductsListDto(List<Product> products) {
}
