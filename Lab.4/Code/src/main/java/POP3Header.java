import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.Enumeration;

public class POP3Header {
    public static void ReadHeaders(Message messages){
        System.out.println("Headers:");
        try {
            Enumeration e = messages.getAllHeaders();
            while (e.hasMoreElements()) {
                Header header = (Header) e.nextElement();
                System.out.println(header.getName() + ": " + header.getValue());
            }
        }
        catch (MessagingException e){
            e.printStackTrace();
        }
    }
}