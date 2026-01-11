package lets.play.demo.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lets.play.demo.Entity.User;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    List<User> findAllByIdNot(String id);
}
