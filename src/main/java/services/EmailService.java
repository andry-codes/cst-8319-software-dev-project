package services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

    private static final String FROM_EMAIL = "capstone113@outlook.com"; // Your Outlook email
    private static final String EMAIL_PASSWORD = "ppgigwvrdgqkzmpw"; // Your email password
    private static final String SMTP_HOST = "smtp.office365.com"; // Outlook SMTP host
    private static final int SMTP_PORT = 587; // Outlook SMTP port

    private static Session getSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Ensure TLSv1.2 is used

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, EMAIL_PASSWORD);
            }
        });
    }

    public static void sendVerificationEmail(String toEmail, String verificationCode) {
        System.out.println("Sending verification email to: " + toEmail);
        System.out.println("Verification code: " + verificationCode);

        if (toEmail == null || toEmail.isEmpty()) {
            System.out.println("Error: toEmail is null or empty.");
            return;
        }

        try {
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Email Verification");
            message.setText("Your verification code is: " + verificationCode);

            Transport.send(message);
            System.out.println("Verification email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void sendResetPasswordEmail(String toEmail, String resetCode) {
        System.out.println("Sending reset password email to: " + toEmail);
        System.out.println("Reset code: " + resetCode);

        if (toEmail == null || toEmail.isEmpty()) {
            System.out.println("Error: toEmail is null or empty.");
            return;
        }

        try {
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Password Reset");
            message.setText("Your password reset code is: " + resetCode);

            Transport.send(message);
            System.out.println("Reset password email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
