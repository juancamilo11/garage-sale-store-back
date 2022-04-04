package co.edu.udea.ayds2.services.email.implementations;

import co.edu.udea.ayds2.dto.user.BasicUserInfo;
import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.services.email.interfaces.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Primary
public class SMTPSender implements MailSender {

    private final JavaMailSender javaMailSender;
    private static final String EMAIL_FOOTER = "Ingresa a https://garage-sale-store.web.app/ para conocer más al respecto.";
    private static final String EMAIL_EMITTER = "Att. Garage Sale Store.";

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendEmailToUserForInformationUpdated(UserDto userDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(userDto.getEmail());
        simpleMailMessage.setSubject("¡Saludos, " + userDto.getName() + ", se ha actualizado tu información personal el " + LocalDateTime.now());
        simpleMailMessage.setText("Se ha actualizado tu información personal." + "\n" +
                EMAIL_FOOTER + "\n\n" +
                EMAIL_EMITTER);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailToUserForWelcome(BasicUserInfo basicUserInfo) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(basicUserInfo.getEmail());
        simpleMailMessage.setSubject("¡Saludos, " + basicUserInfo.getName() + ", Bienvenido a Garage Sale Store!");
        simpleMailMessage.setText("\n¡Esperamos que disfrutes de los grandes beneficios de esta aplicación!\n\n" +
                EMAIL_FOOTER + "\n\n" +
                EMAIL_EMITTER);
        javaMailSender.send(simpleMailMessage);
    }

}
