package lets.play.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lets.play.demo.DTOs.ProductCreationDto;
import lets.play.demo.DTOs.ProductUpdateDto;
import lets.play.demo.DTOs.ProductsListDto;
import lets.play.demo.Service.ProductService;
import lets.play.demo.Utils.DtoSanitizer;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @PermitAll
    public ResponseEntity<ProductsListDto> getAllProduct() {
        ProductsListDto res = productService.getAllProducts();
        return ResponseEntity.ok(res);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<?> createProducts(@Valid @RequestBody ProductCreationDto req) {
        DtoSanitizer.validateProductCreationDto(req);
        ProductCreationDto sanitizedReq = DtoSanitizer.sanitizeProductCreationDto(req);
        
        productService.createProducts(sanitizedReq);
        return ResponseEntity.ok("product created succefully");
    }

    @PreAuthorize("isAuthenticated() || hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletProduct(@PathVariable String id) {
        productService.deletProduct(id);
        return ResponseEntity.ok("product deleted succefully");
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @Valid @RequestBody ProductUpdateDto req) {
        DtoSanitizer.validateProductCreationDto(new ProductCreationDto(req.name(), req.des(), req.price()));
        ProductUpdateDto sanitizedReq = DtoSanitizer.sanitizeProductUpdateDto(req);
        
        productService.updateProduct(id, sanitizedReq);
        return ResponseEntity.ok("product updated succefully");
    }
}
