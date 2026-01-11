package lets.play.demo.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterReqDto(
    @NotBlank(message = "Name is required")
    String name,
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password,
    
    @NotBlank(message = "Email is required")
    @Email(message = "Valid email is required")
    String email
) {}
