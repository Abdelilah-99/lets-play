package lets.play.demo.DTOs;

import jakarta.validation.constraints.NotBlank;

public record LoginReqDto(
    @NotBlank
    String email,
    @NotBlank
    String password) {
}