package lets.play.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import lets.play.demo.DTOs.UsersListDto;
import lets.play.demo.Entity.User;
import lets.play.demo.Repository.UserRepo;

@Service
public class AdminService {
    private final UserRepo userRepo;

    public AdminService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UsersListDto getAllUsers() {
        List<User> users = userRepo.findAll();
        return new UsersListDto(users);
    }
}
