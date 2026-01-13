package lets.play.demo.Utils;

import java.util.regex.Pattern;

public class DataSanitizer {

    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    
    private static final Pattern ALPHANUMERIC_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9]+$");
    
    private static final Pattern SAFE_STRING_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9\\s.,!?'-()&]+$");

    private static final Pattern HTML_TAG_PATTERN = 
        Pattern.compile("<[^>]*>");
    
    private static final Pattern SCRIPT_PATTERN = 
        Pattern.compile("(?i)<script.*?</script>|on\\w+\\s*=|javascript:", Pattern.DOTALL);

    public static String sanitizeString(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }

        String sanitized = input.trim();

        sanitized = removeHtmlTags(sanitized);

        sanitized = removeScriptPatterns(sanitized);

        return sanitized;
    }

    public static String sanitizeEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }

        String sanitized = email.trim().toLowerCase();

        if (!isValidEmail(sanitized)) {
            return null;
        }

        return sanitized;
    }

    public static String sanitizePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "";
        }

        return password.trim();
    }

    public static String sanitizeProductText(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "";
        }

        String sanitized = text.trim();

        sanitized = removeHtmlTags(sanitized);

        sanitized = removeScriptPatterns(sanitized);

        if (sanitized.length() > 1000) {
            sanitized = sanitized.substring(0, 1000);
        }

        return sanitized;
    }

    public static String sanitizeNumeric(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }

        String sanitized = input.trim();

        sanitized = sanitized.replaceAll("[^0-9.-]", "");

        return sanitized;
    }

    private static String removeHtmlTags(String input) {
        return HTML_TAG_PATTERN.matcher(input).replaceAll("");
    }

    private static String removeScriptPatterns(String input) {
        return SCRIPT_PATTERN.matcher(input).replaceAll("");
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public static boolean isAlphanumeric(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        return ALPHANUMERIC_PATTERN.matcher(input).matches();
    }

    public static boolean isSafeString(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        return SAFE_STRING_PATTERN.matcher(input).matches();
    }

    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static String escapeSqlString(String input) {
        if (input == null) {
            return "";
        }

        return input.replace("'", "''")
                   .replace("\\", "\\\\")
                   .replace("\"", "\\\"");
    }

    public static boolean isValidLength(String input, int minLength, int maxLength) {
        if (input == null) {
            return minLength == 0;
        }

        int length = input.trim().length();
        return length >= minLength && length <= maxLength;
    }

    public static String toSafeDisplayText(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }

        return sanitizeString(input);
    }
}
