package Helpers;

import layer.domain.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailVerification {
    Session session;
    public EmailVerification() {
        // 你的電子郵件帳號和密碼
        final String username = "ck1040308@gmail.com";
        final String password = "hrxz kyro hdlq jmew";

        // SMTP 伺服器設定
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");

        // 創建一個 Session 對象
        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public String createEmail(Customer customer){
        try {
            // 創建一個 MimeMessage 對象
            Message message = new MimeMessage(session);
            // 設置發送者的郵件地址
            message.setFrom(new InternetAddress("ck1040308@gmail.com"));
            // 設置接收者的郵件地址
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getEmail()));
            // 設置郵件主題
            message.setSubject("帳號驗證");
            // 建立認證token
            String verificationToken = UUID.randomUUID().toString();
            // 建立時間戳


            // 生成帳號驗證連結
            String verificationLink = "localhost:8080/controller?action=verification&token=" + verificationToken +"&account="+customer.getAccount();
            // 郵件內容，可以使用 HTML 格式
            String emailContent = "<p>請點擊以下連結進行帳號驗證：</p><p><a '" + verificationLink + "'>" + verificationLink + "</a></p>";
            message.setContent(emailContent, "text/html; charset=utf-8");

            // 送出郵件
            Transport.send(message);

            System.out.println("已發送郵件成功");

            return verificationToken;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }


    }
}

