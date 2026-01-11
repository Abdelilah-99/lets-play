package lets.play.demo.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lets.play.demo.DTOs.RegisterResDto;
import lets.play.demo.Entity.User;
import lets.play.demo.Repository.RegisterRepo;
import lets.play.demo.DTOs.RegisterReqDto;

@Service
public class RegisterService {
    private PasswordEncoder passwordEncoder;
    private RegisterRepo registerRepo;

    public RegisterService(PasswordEncoder passwordEncoder, RegisterRepo registerRepo) {
        this.passwordEncoder = passwordEncoder;
        this.registerRepo = registerRepo;
    }

    public RegisterResDto registerService(RegisterReqDto req) {
        // User newUser = new User(req.userName(),
        // passwordEncoder.encode(req.password()));
        // this.registerRepo.save(newUser);
        // User newUser = new User(null, null, null, null, null)
        User user = new User();
        user.setEmail(req.email().toLowerCase());
        user.setName(req.name());
        user.setPassword(passwordEncoder.encode(req.password()));
        registerRepo.save(user);
        return new RegisterResDto("Registration Succeed");
    }
}
