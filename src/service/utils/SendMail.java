package service.utils;
import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;


/**
 * author:East(张栋芳) date:2008-7-17
 * 
 **/
public class SendMail {
	private static MailAuthenticator autherticator = null;

	public static void sendmail(String nr,String toUser){
//	public static void main(String args[]){
		String from = "KJCK_kejianwang@163.com";
		String to = toUser;
		String smtpServer = "smtp.163.com";
		String subject = "课件仓库注册激活";
		String content = nr;
		Properties props = System.getProperties();

		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.auth", "true");
		autherticator = new MailAuthenticator(from,
				"kjck1234");
		Session session = Session.getDefaultInstance(props, autherticator);
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from));
			msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
					to));
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(content);
			Transport.send(msg);
			System.out.println("成功发送给"+to);
		} catch (Exception se) {
			se.printStackTrace();
		}
	}
}

// 现在的大部分的邮件服务器都要求有身份验证，所以需要此类实现验证功能
class MailAuthenticator extends Authenticator {

	private String username = null;
	private String userpasswd = null;

	public MailAuthenticator() {
	}

	public MailAuthenticator(String username, String userpasswd) {
		this.username = username;
		this.userpasswd = userpasswd;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.userpasswd = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, userpasswd);
	}
}
