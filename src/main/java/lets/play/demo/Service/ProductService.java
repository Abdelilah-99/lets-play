package lets.play.demo.Service;

import java.util.List;

import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lets.play.demo.DTOs.ProductCreationDto;
import lets.play.demo.DTOs.ProductUpdateDto;
import lets.play.demo.DTOs.ProductsListDto;
import lets.play.demo.Entity.Product;
import lets.play.demo.Exceptions.InvalidProductException;
import lets.play.demo.Repository.ProductRepo;
import lets.play.demo.Utils.DataSanitizer;

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
        
        String name = DataSanitizer.sanitizeProductText(req.name());
        String description = DataSanitizer.sanitizeProductText(req.des());
        
        if (name.isEmpty() || description.isEmpty()) {
            throw new InvalidProductException("Product name and description cannot be empty");
        }
        
        Product product = new Product();
        product.setName(name);
        product.setPrice(req.price());
        product.setDescription(description);
        product.setUserId(id);
        productRepo.save(product);
    }

    public void deletProduct(String id) {
        Authentication userId = SecurityContextHolder.getContext().getAuthentication();
        Product product = productRepo.findById(id).orElseThrow(() -> {
            throw new InvalidProductException("Product not exist");
        });
        if (userId.getName().equals(product.userId)
                || userId.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            productRepo.deleteById(id);
            return;
        }
        throw new AuthorizationDeniedException("Access denied");
    }

    public void updateProduct(String id, ProductUpdateDto req) {
        Authentication userId = SecurityContextHolder.getContext().getAuthentication();
        Product product = productRepo.findById(id).orElseThrow(() -> {
            throw new InvalidProductException("Product not exist");
        });
        if (userId.getName().equals(product.userId)) {
            String sanitizedDescription = DataSanitizer.sanitizeProductText(req.des());
            String sanitizedName = DataSanitizer.sanitizeProductText(req.name());
            
            product.setDescription(sanitizedDescription);
            product.setName(sanitizedName);
            product.setPrice(req.price());
            productRepo.save(product);
            return;
        }
        throw new AuthorizationDeniedException("Acces denied");
    }
}
