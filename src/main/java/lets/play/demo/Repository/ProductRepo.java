package lets.play.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import lets.play.demo.Entity.Product;

public interface ProductRepo extends MongoRepository<Product, String> {
    
}
