package util;

import java.security.SecureRandom;

public final class OtpUtil {

    private static final SecureRandom RANDOM =
            new SecureRandom();

    private OtpUtil() {
    }

    public static String generate() {
        int value =
                100000 + RANDOM.nextInt(900000);

        return String.valueOf(value);
    }
}
