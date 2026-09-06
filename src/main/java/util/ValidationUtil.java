package util;

import java.math.BigDecimal;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean hasLength(String value, int min, int max) {
        if (isBlank(value)) {
            return false;
        }

        int length =
                value.trim().length();

        return length >= min && length <= max;
    }

    public static boolean isEmail(String value) {
        return value != null
                && value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean isPhone(String value) {
        return isBlank(value)
                || value.matches("^0[0-9]{9}$");
    }

    public static boolean isOtp(String value) {
        return value != null
                && value.matches("^[0-9]{6}$");
    }

    public static BigDecimal positiveMoney(String value) {
        try {
            BigDecimal money =
                    new BigDecimal(value);

            if (money.compareTo(BigDecimal.valueOf(1000)) < 0) {
                return null;
            }

            return money;
        } catch (Exception e) {
            return null;
        }
    }
}
