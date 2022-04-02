package co.edu.udea.ayds2.services.email;

import co.edu.udea.ayds2.services.email.interfaces.MailSender;

public class EmailSenderService {

    private final MailSender mailSender;

    public EmailSenderService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public MailSender getMailSender() {
        return this.mailSender;
    }
}
