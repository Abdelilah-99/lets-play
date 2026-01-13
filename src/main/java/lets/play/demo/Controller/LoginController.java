package lets.play.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lets.play.demo.DTOs.LoginReqDto;
import lets.play.demo.DTOs.LoginResDto;
import lets.play.demo.Service.LoginService;
import lets.play.demo.Utils.DtoSanitizer;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @PermitAll
    private ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginReqDto req) {
        DtoSanitizer.validateLoginReqDto(req);
        LoginReqDto sanitizedReq = DtoSanitizer.sanitizeLoginReqDto(req);
        
        LoginResDto res = loginService.login(sanitizedReq);
        return ResponseEntity.ok(res);
    }
}
