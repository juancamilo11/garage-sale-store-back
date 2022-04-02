package co.edu.udea.ayds2.services.email.implementations;

import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.services.email.interfaces.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Stream;

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
    public void sendEmailForOrderCreated(String orderId, UserDto sellerInfo, UserDto customerInfo) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        Stream.of(sellerInfo, customerInfo).forEach(userDto -> {
            simpleMailMessage.setTo(userDto.getEmail());
            simpleMailMessage.setSubject("¡Saludos, " + userDto.getName() + ", se ha creado una orden de compra con identificador " + orderId);
            simpleMailMessage.setText("Se ha creado la orden con identificador " + orderId + " el " + LocalDateTime.now() + "." + "\n" +
                    EMAIL_FOOTER + "\n\n" +
                    EMAIL_EMITTER);
            javaMailSender.send(simpleMailMessage);
        });
    }

    @Override
    public void sendEmailForOrderApproved(String orderId, UserDto customerInfo) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(customerInfo.getEmail());
        simpleMailMessage.setSubject("¡Saludos, " + customerInfo.getName() + " te han aprobado la órden de compra con identificador " + orderId);
        simpleMailMessage.setText("El vendedor correspondiente de la orden con identificador " + orderId + " te ha aprobado la órden de compra el " + LocalDateTime.now() + "." + "\n" +
                "¡Gracias por tu compra!" + "\n" +
                EMAIL_FOOTER + "\n\n" +
                EMAIL_EMITTER);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailForOrderDeclined(String orderId, UserDto customerInfo) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(customerInfo.getEmail());
        simpleMailMessage.setSubject("¡Saludos, " + customerInfo.getName() + " se ha cancelado o rechazado la órden de compra con identificador " + orderId);
        simpleMailMessage.setText("Se ha cancelado o rechazado la orden con identificador " + orderId + "\n" +
                "¡Esperamos puedas encontrar otros productos similares y que sean de tu gusto!" + "\n" +
                EMAIL_FOOTER + "\n\n" +
                EMAIL_EMITTER);
        javaMailSender.send(simpleMailMessage);
    }
}
