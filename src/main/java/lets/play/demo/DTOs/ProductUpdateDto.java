package lets.play.demo.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductUpdateDto(
        @NotBlank(message = "Name is required") String name,
        @Size(min = 6, message = "des must be at least 6 characters") @NotBlank(message = "des is required") String des,
        Double price) {
}
