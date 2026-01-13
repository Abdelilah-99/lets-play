package lets.play.demo.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lets.play.demo.Config.JwtUtils;
import lets.play.demo.DTOs.LoginReqDto;
import lets.play.demo.DTOs.LoginResDto;
import lets.play.demo.Entity.User;
import lets.play.demo.Exceptions.EmailNotFoundException;
import lets.play.demo.Exceptions.InvalidePasswordException;
import lets.play.demo.Repository.LoginRepo;
import lets.play.demo.Utils.DataSanitizer;

@Service
public class LoginService {
    private final LoginRepo loginRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public LoginService(LoginRepo loginRepo, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.loginRepo = loginRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public LoginResDto login(LoginReqDto req) {
        String email = DataSanitizer.sanitizeEmail(req.email());
        if (email == null) {
            throw new IllegalArgumentException("Invalid email format");
        }

        String password = DataSanitizer.sanitizePassword(req.password());
        
        User user = loginRepo.findByEmail(email).orElseThrow(()->{
            throw new EmailNotFoundException("Email Not Found");
        });
        if (!passwordEncoder.matches(password, user.password)) {
            throw new InvalidePasswordException("Incorrect Password");
        }
        String token = jwtUtils.generateToken(user.id, user.role);
        return new LoginResDto("user has logged in", token);
    }
}
