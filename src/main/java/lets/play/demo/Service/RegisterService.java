package lets.play.demo.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lets.play.demo.DTOs.RegisterResDto;
import lets.play.demo.Entity.User;
import lets.play.demo.Exceptions.EmailAlreadyExistException;
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

    public RegisterResDto registerService(@Valid RegisterReqDto req) {
        String email = req.email().toLowerCase();
        if (registerRepo.existsByEmail(email)) {
            throw new EmailAlreadyExistException("email already exist");
        }
        User user = new User();
        user.setEmail(email);
        user.setName(req.name());
        user.setPassword(passwordEncoder.encode(req.password()));
        registerRepo.save(user); // MongoDB will throw DuplicateKeyException if email exists
        return new RegisterResDto("Registration Succeed");
    }
}
