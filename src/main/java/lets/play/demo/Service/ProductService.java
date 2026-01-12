package lets.play.demo.Service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lets.play.demo.DTOs.ProductCreationDto;
import lets.play.demo.DTOs.ProductsListDto;
import lets.play.demo.Entity.Product;
import lets.play.demo.Repository.ProductRepo;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ProductsListDto getAllProducts() {
        List<Product> products = productRepo.findAll();
        return new ProductsListDto(products);
    }

    public void createProducts(ProductCreationDto req) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        Product product = new Product();
        product.setName(req.name());
        product.setPrice(req.price());
        product.setDescription(req.des());
        product.setUserId(id);
        productRepo.save(product);
    }
}
