package lets.play.demo.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import lets.play.demo.Entity.User;

public interface LoginRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
