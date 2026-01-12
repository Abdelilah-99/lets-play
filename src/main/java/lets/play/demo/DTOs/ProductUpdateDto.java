package lets.play.demo.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductUpdateDto(
        @NotBlank(message = "Name is required") String name,

        @NotBlank(message = "des is required") @Size(min = 6, message = "des must be at least 6 characters") String des,

        @NotNull(message = "price is required") @Positive(message = "price must be positive") Double price) {
}
