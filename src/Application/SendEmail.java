package Application;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
	public static void SendEmail(String email,String password,String name){
      // Recipient's email ID needs to be mentioned.
      String to = email;
      // Sender's email ID needs to be mentioned
      String from = "Assignment_Frederic@gmail.com";
      // Assuming you are sending email from localhost
      String host = "smtp.theoptimum.net";
      // Get system properties
      Properties properties = System.getProperties();
      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      // Get the default Session object.
      properties.put("mail.smtp.port", 587);
      Session session = Session.getDefaultInstance(properties);
      try {
         // Create a default Message object.
         MimeMessage message = new MimeMessage(session);
         // Set From header field of the header.
         message.setFrom(new InternetAddress(from));
         // Set To header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
         // Set Subject header field
         message.setSubject("Account Registered at Assignment4_Frederic");
         // Actual message
         StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append("Dear "+name+",\n");
         stringBuilder.append("You Have Been registered to Assignment4.\n");
         stringBuilder.append("Your Login Id would be "+email+" and the tempory password: "+password+"\n");
         stringBuilder.append("\n\n");
         stringBuilder.append("Best Regards,\n");
         stringBuilder.append("Admin");
         message.setText(stringBuilder.toString());
         // Send message
         System.out.println("Sending Email now, Please hold.");
         Transport.send(message);
         System.out.println("Sent Email to user is successful....");
         System.out.println();
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}