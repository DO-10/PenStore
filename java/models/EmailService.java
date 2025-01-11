package models;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    private static final String SMTP_HOST = "smtp.qq.com";  // SMTP 服务器地址
    private static final String SMTP_PORT = "587";  // 端口（通常是 587 或 465）
    private static final String USERNAME = "2959209045@qq.com";  // 发送方邮箱
    private static final String PASSWORD = "qceczwhnnoohdfhd";  // 发送方邮箱密码

    public static void sendVerificationCode(String recipientEmail, String verificationCode) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("注册验证码");
            message.setText("您的验证码是: " + verificationCode);

            Transport.send(message);
            System.out.println("验证码邮件已发送至 " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
