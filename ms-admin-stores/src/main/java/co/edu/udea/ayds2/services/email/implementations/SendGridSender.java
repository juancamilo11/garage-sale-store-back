package co.edu.udea.ayds2.services.email.implementations;


import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.services.email.interfaces.MailSender;
import org.springframework.stereotype.Service;

@Service
public class SendGridSender implements MailSender {
    @Override
    public void sendEmailForOrderCreated(String orderId, UserDto sellerInfo, UserDto customerInfo) {
       //Another implemententation
    }

    @Override
    public void sendEmailForOrderApproved(String orderId, UserDto customerInfo) {
        //Another implemententation

    }

    @Override
    public void sendEmailForOrderDeclined(String orderId, UserDto customerInfo) {
        //Another implemententation

    }
    // Docs at https://medium.com/javarevisited/sending-emails-with-sendgrid-and-spring-boot-81e9637a1f05
}
