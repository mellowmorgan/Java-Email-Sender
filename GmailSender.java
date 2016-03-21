import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class GmailSender {
    private String username;
    private String password;
    
    public GmailSender(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public boolean sendMail(String recipient, String subject, String body) {
        String[] r = new String[1];
        r[0] = recipient;
        return sendMail(r, subject, body);
    }
    
    public boolean sendMail(String[] recipients, String subject, String body) {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtps.auth", "true");
        Session mailSession = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(mailSession);
        try {
            message.setSubject(subject);
            message.setContent(body, "text/plain");
            for(String s : recipients) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(s));
            }
            Transport transport = mailSession.getTransport("smtps");
            transport.connect("smtp.gmail.com", username, password);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
            return true;
        }
        catch(NoSuchProviderException e) { System.out.println(e); return false; }
        catch(MessagingException e) { System.out.println(e); return false; }
    }

    public static void main(String[] args) {
        GmailSender gmail = new GmailSender("your.gmail.username", "your.gmail.password");
        boolean sent = gmail.sendMail("recipient@wherever.com", "some subject", "The body goes here.");
        System.out.println(sent);
    }
}