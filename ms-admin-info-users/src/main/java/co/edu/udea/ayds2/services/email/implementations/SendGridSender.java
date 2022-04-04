package co.edu.udea.ayds2.services.email.implementations;


import co.edu.udea.ayds2.dto.user.BasicUserInfo;
import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.services.email.interfaces.MailSender;
import org.springframework.stereotype.Service;

@Service
public class SendGridSender implements MailSender {

    // Docs at https://medium.com/javarevisited/sending-emails-with-sendgrid-and-spring-boot-81e9637a1f05

    @Override
    public void sendEmailToUserForInformationUpdated(UserDto userDto) {
        //Another implementation
    }

    @Override
    public void sendEmailToUserForWelcome(BasicUserInfo basicUserInfo) {
        //Another implementation
    }

}
