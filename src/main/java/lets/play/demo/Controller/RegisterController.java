package lets.play.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
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
    @PermitAll
    public ResponseEntity<RegisterResDto> register(@Valid @RequestBody RegisterReqDto req) {
        RegisterResDto msg = registerService.registerService(req);
        return ResponseEntity.ok(msg);
    }
}
