package lets.play.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lets.play.demo.DTOs.MeResDto;
import lets.play.demo.Service.MeService;

@RestController
public class MeController {
    private final MeService meService;

    public MeController(MeService meService) {
        this.meService = meService;
    }

    @GetMapping("/api/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<MeResDto> myInfo() {
        MeResDto res = meService.getMyInfo();
        return ResponseEntity.ok(res);
    }
}
