package lets.play.demo.Utils;

import lets.play.demo.DTOs.*;

public class DtoSanitizer {

    public static RegisterReqDto sanitizeRegisterReqDto(RegisterReqDto dto) {
        String sanitizedEmail = DataSanitizer.sanitizeEmail(dto.email());
        String sanitizedName = DataSanitizer.sanitizeString(dto.name());
        String sanitizedPassword = DataSanitizer.sanitizePassword(dto.password());

        if (sanitizedEmail == null) {
            throw new IllegalArgumentException("Invalid email format");
        }

        return new RegisterReqDto(sanitizedName, sanitizedPassword, sanitizedEmail);
    }

    public static LoginReqDto sanitizeLoginReqDto(LoginReqDto dto) {
        String sanitizedEmail = DataSanitizer.sanitizeEmail(dto.email());
        String sanitizedPassword = DataSanitizer.sanitizePassword(dto.password());

        if (sanitizedEmail == null) {
            throw new IllegalArgumentException("Invalid email format");
        }

        return new LoginReqDto(sanitizedEmail, sanitizedPassword);
    }

    public static ProductCreationDto sanitizeProductCreationDto(ProductCreationDto dto) {
        String sanitizedName = DataSanitizer.sanitizeProductText(dto.name());
        String sanitizedDescription = DataSanitizer.sanitizeProductText(dto.des());
        Double sanitizedPrice = sanitizePrice(dto.price());

        return new ProductCreationDto(sanitizedName, sanitizedDescription, sanitizedPrice);
    }

    public static ProductUpdateDto sanitizeProductUpdateDto(ProductUpdateDto dto) {
        String sanitizedName = DataSanitizer.sanitizeProductText(dto.name());
        String sanitizedDescription = DataSanitizer.sanitizeProductText(dto.des());
        Double sanitizedPrice = sanitizePrice(dto.price());

        return new ProductUpdateDto(sanitizedName, sanitizedDescription, sanitizedPrice);
    }

    private static Double sanitizePrice(Double price) {
        if (price == null || price < 0) {
            return 0.0;
        }
        return Math.min(price, 999999.99);
    }

    public static void validateProductCreationDto(ProductCreationDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Product creation data cannot be null");
        }

        if (!DataSanitizer.isNotEmpty(dto.name())) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (!DataSanitizer.isNotEmpty(dto.des())) {
            throw new IllegalArgumentException("Product description cannot be empty");
        }

        if (dto.price() == null || dto.price() < 0) {
            throw new IllegalArgumentException("Product price must be a valid positive number");
        }
    }

    public static void validateRegisterReqDto(RegisterReqDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Registration data cannot be null");
        }

        if (!DataSanitizer.isValidEmail(dto.email())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (!DataSanitizer.isNotEmpty(dto.name())) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (!DataSanitizer.isValidLength(dto.password(), 6, 100)) {
            throw new IllegalArgumentException("Password must be between 6 and 100 characters");
        }
    }

    public static void validateLoginReqDto(LoginReqDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Login data cannot be null");
        }

        if (!DataSanitizer.isValidEmail(dto.email())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (!DataSanitizer.isNotEmpty(dto.password())) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }
}
