package lets.play.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import lets.play.demo.Entity.User;

public interface LoginRepo extends MongoRepository<User, String> {
    User findByEmail(String email);
}
