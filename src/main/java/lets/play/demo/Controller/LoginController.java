package lets.play.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lets.play.demo.DTOs.LoginReqDto;
import lets.play.demo.DTOs.LoginResDto;
import lets.play.demo.Service.LoginService;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    private ResponseEntity<LoginResDto> login(@RequestBody LoginReqDto req) {
        LoginResDto res = loginService.login(req);
        return ResponseEntity.ok(res);
    }
}
