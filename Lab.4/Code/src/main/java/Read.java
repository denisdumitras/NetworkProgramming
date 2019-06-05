import javax.mail.*;
import java.util.Properties;

public class Read {
    public static void ReadEmail() throws Exception {
        Properties properties = System.getProperties();
//        properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.put("mail.pop3.socketFactory.port", "995");
        properties.put("mail.pop3.host", "pop.gmail.com");
        properties.put("mail.pop3.port", "995");
//        properties.put("mail.pop3.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties);
        Store store = session.getStore("pop3s");

//        wuttdgzlnddrjqip

        try{
            store.connect("pop.gmail.com","lukaklose467@gmail.com", "wuttdgzlnddrjqip");
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message[] messages = inbox.getMessages();

            if (messages.length == 0) {
                System.out.println("No new messages were found!");
            }
            else{
                System.out.println("There are: " + messages.length + " messages.");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println();
            }

            Address[] recipients;

            for (int i = 0; i < messages.length; i++) {
                System.out.println("Message " + (i + 1));
                System.out.println();
                System.out.println("From: " + messages[i].getFrom()[0]);

                recipients = messages[i].getRecipients(Message.RecipientType.TO);
                if(recipients != null) {
                    for (int j = 0; j < recipients.length; j++) {
                        System.out.println("To: " + recipients[j].toString());
                    }
                }
                System.out.println("Subject: " + messages[i].getSubject());
                System.out.println("Sent Date: " + messages[i].getSentDate());
                System.out.println("Text: " + messages[i].getContent().toString());
                POP3Header.ReadHeaders(messages[i]);
                System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - -");
            }
            inbox.close(true);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}