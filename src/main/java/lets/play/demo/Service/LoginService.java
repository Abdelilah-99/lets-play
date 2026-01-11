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
        User user = loginRepo.findByEmail(req.email()).orElseThrow(()->{
            throw new EmailNotFoundException("Email Not Found");
        });
        String pwd = req.password();
        if (!passwordEncoder.matches(pwd, user.password)) {
            throw new InvalidePasswordException("Incorrect Password");
        }
        // return new LoginResDto("authentication failed", null);
        String token = jwtUtils.generateToken(user.id, user.role);
        return new LoginResDto("user has logged in", token);
    }
}
