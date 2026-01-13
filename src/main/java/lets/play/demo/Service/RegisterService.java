package lets.play.demo.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import lets.play.demo.DTOs.RegisterResDto;
import lets.play.demo.Entity.User;
import lets.play.demo.Exceptions.EmailAlreadyExistException;
import lets.play.demo.Repository.RegisterRepo;
import lets.play.demo.DTOs.RegisterReqDto;
import lets.play.demo.Utils.DataSanitizer;

@Service
public class RegisterService {
    private PasswordEncoder passwordEncoder;
    private RegisterRepo registerRepo;

    public RegisterService(PasswordEncoder passwordEncoder, RegisterRepo registerRepo) {
        this.passwordEncoder = passwordEncoder;
        this.registerRepo = registerRepo;
    }

    public RegisterResDto registerService(@Valid RegisterReqDto req) {
        String email = DataSanitizer.sanitizeEmail(req.email());
        if (email == null) {
            throw new IllegalArgumentException("Invalid email format");
        }

        String name = DataSanitizer.sanitizeString(req.name());
        String password = DataSanitizer.sanitizePassword(req.password());
        
        if (registerRepo.existsByEmail(email)) {
            throw new EmailAlreadyExistException("email already exist");
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        registerRepo.save(user);
        return new RegisterResDto("Registration Succeed");
    }
}
