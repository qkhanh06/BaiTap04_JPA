package util;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public final class EmailUtil {

    private EmailUtil() {
    }

    public static void send(
            String to,
            String subject,
            String content) {

        String host =
                value("MAIL_HOST", "smtp.gmail.com");
        String port =
                value("MAIL_PORT", "587");
        String username =
                value("MAIL_USERNAME", "");
        String password =
                value("MAIL_PASSWORD", "");

        if (!isConfigured()) {
            System.out.println(
                    "[OTP MAIL] To: " + to
                            + " | Subject: " + subject
                            + " | Content: " + content);
            return;
        }

        Properties props =
                new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session =
                Session.getInstance(
                        props,
                        new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }
                        });

        try {
            Message message =
                    new MimeMessage(session);

            message.setFrom(
                    new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("Khong gui duoc email OTP", e);
        }
    }

    public static boolean isConfigured() {
        String username =
                value("MAIL_USERNAME", "");
        String password =
                value("MAIL_PASSWORD", "");

        return !username.isBlank() && !password.isBlank();
    }

    private static String value(String key, String defaultValue) {
        String systemValue =
                System.getProperty(key);

        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }

        String envValue =
                System.getenv(key);

        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }

        return defaultValue;
    }
}
