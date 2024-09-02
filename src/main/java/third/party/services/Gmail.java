package third.party.services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class Gmail {

	public static boolean emailSender(String to, String userPassword) {
		String user = "r9038064@gmail.com";
		String password = "utbh iozi ifnt hbmx";
		String from = "r9038064@gmail.com";

		try {
			Properties prop = new Properties();
			prop.put("mail.smtp.auth", true);
			prop.put("mail.smtp.starttls.enable", true);
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.host", "smtp.gmail.com");

			Session session = Session.getInstance(prop, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});

			Message message = new MimeMessage(session);
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress(from));
			message.setSubject("Your Account Has Been Created!");
			message.setText("Hello " + to + ",\r\n" + "\r\n"
					+ "Welcome to Vishwas Libraries Management Systems PVT LTD \r\n" + "\r\n"
					+ "We're excited to let you know that your account has been successfully created. Below are your account details:\r\n"
					+ "\r\n" + "User ID: " + to + "\r\n" + "Password: " + userPassword + "\r\n"
					+ "For security reasons, we recommend changing your password after your first login. You can do this by visiting the “Account Settings” section once you’re logged in.\r\n"
					+ "\r\n"
					+ "If you have any questions or need assistance, please feel free to contact our support team at [Support Email/Phone Number].\r\n"
					+ "\r\n" + "Thank you for joining us!\r\n" + "\r\n" + "Best regards,\r\n"
					+ "Vishwas LMS PVT LTD\r\n" + "7489216777\r\n" + "www.vishwaslms.org.in");
			
			Transport.send(message);
			
			System.out.println("Mail Sent");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		emailSender("paridhiprj610@gmail.com", "Pari@321456");
	}
}
