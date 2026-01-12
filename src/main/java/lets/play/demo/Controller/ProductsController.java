package lets.play.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lets.play.demo.DTOs.ProductCreationDto;
import lets.play.demo.DTOs.ProductsListDto;
import lets.play.demo.Service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductsListDto> getAllProduct() {
        ProductsListDto res = productService.getAllProducts();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProducts(@RequestBody ProductCreationDto req) {
        productService.createProducts(req);
        return ResponseEntity.ok("product created succefully");
    }
}
