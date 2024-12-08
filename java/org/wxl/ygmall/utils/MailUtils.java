package org.wxl.ygmall.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
/**发送邮件的工具类
 */
public class MailUtils {
	public static boolean sendEmail(String to, String subject, String messageText) {
		if (to == null || to.isEmpty()) {
	        //throw new IllegalArgumentException("Recipient email address cannot be null or empty.");
			return false;
	    }
        // 邮件配置
        String host = "smtp.qq.com";  // QQ 邮箱的 SMTP 服务器地址
        String from = "3636681912@qq.com";  // 发送邮箱地址（你自己的 QQ 邮箱）
        String password = "lrdvflzaezexdaic";  // 发送邮箱的授权码（QQ邮箱设置中获取）

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");  // 使用587端口，支持TLS加密
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");  // 启用 TLS 加密

        // 创建会话
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
        	 // 确保 to 不是 null 或空
            //InternetAddress[] addresses = InternetAddress.parse(to);
            // 创建邮件对象
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));  // 收件人
            message.setSubject(subject);  // 设置邮件主题
            message.setText(messageText);  // 设置邮件内容

            // 发送邮件
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
