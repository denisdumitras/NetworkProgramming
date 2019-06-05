import com.sun.mail.smtp.SMTPTransport;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class Write {
    public static void WriteEmail() {
        Properties properties = System.getProperties();
        properties.put("mail.smtps.host","smtp.gmail.com");
        properties.put("mail.smtps.auth","true");
        properties.put("mail.smtp.EnableSSL.enable","true");

        Session session = Session.getInstance(properties, null);

        try {
            MimeMessage mimeMessage = new MimeMessage(session);

            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("metei1997vasile@gmail.com"));
            mimeMessage.setSubject("Test Subject");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Test - Hello World!");

            MimeBodyPart attachementBodyPart = new MimeBodyPart();
            DataSource source1 = new FileDataSource("D:\\EmailAttachment.txt");
            DataSource source2 = new FileDataSource("D:\\football.jpg");
            attachementBodyPart.setDataHandler(new DataHandler(source1));
            attachementBodyPart.setDataHandler(new DataHandler(source2));
            attachementBodyPart.setFileName("TestAttachement.txt");
            attachementBodyPart.setHeader("Content-ID", "<image>");


            MimeBodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent("<h1>Image</h1>" +
                    "<br>" +
                    "<img src=\"cid:image\">", "text/html");

            multipart.addBodyPart(textBodyPart);
            multipart.addBodyPart(attachementBodyPart);
            multipart.addBodyPart(htmlBodyPart);

            mimeMessage.setContent(multipart);

            SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
            t.connect("smtp.gmail.com", "lukaklose467@gmail.com" , "wuttdgzlnddrjqip");
            t.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

            System.out.println("Your email was sent successfully!");
            t.close();
        }
        catch (MessagingException e){
            e.printStackTrace();
        }
    }
}