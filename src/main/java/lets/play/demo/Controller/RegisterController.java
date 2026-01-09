package lets.play.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lets.play.demo.DTOs.*;
import lets.play.demo.Service.*;
@RestController
@RequestMapping("/api/auth")
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResDto> register(RegisterReqDto req) {
        RegisterResDto msg = registerService.registerService(req);
        return ResponseEntity.ok(msg);
    }
}
