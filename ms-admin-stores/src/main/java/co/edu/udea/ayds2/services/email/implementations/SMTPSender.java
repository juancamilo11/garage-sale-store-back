package co.edu.udea.ayds2.services.email.implementations;

import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.dto.store.product.ProductQuestionDto;
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
            simpleMailMessage.setText("Se ha creado la orden con identificador " + orderId + " el " + LocalDateTime.now() + ".\n" +
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
        simpleMailMessage.setText("El vendedor correspondiente de la orden con identificador " + orderId + " te ha aprobado la órden de compra el " + LocalDateTime.now() + ".\n" +
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
                "¡Esperamos puedas encontrar otros productos similares y que sean de tu gusto!\n" +
                EMAIL_FOOTER + "\n\n" +
                EMAIL_EMITTER);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailToUserForNewStoreCreated(GarageSaleStoreDto garageSaleStoreDto, UserDto seller) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(seller.getEmail());
        simpleMailMessage.setSubject("¡Saludos, " + seller.getName() + " se ha creado exitosamente tu nueva tienda llamada " + garageSaleStoreDto.getStoreName());
        simpleMailMessage.setText("Se ha creado tu nueva tienda " + garageSaleStoreDto.getStoreName() + ", la cual estará habilitada desde el "  +
                garageSaleStoreDto.getStoreExistencePeriod().getStartingDate() + " hasta el " + garageSaleStoreDto.getStoreExistencePeriod().getEndingDate() + ".\n" +
                "¡Esperamos puedas vender todos tus productos!" + "\n" +
                "La mejor de nuestras suertes." + "\n" +
                EMAIL_FOOTER + "\n\n" +
                EMAIL_EMITTER);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailToSellerForNewQuestionPosted(String storeName, String categoryName, UserDto seller, String question) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(seller.getEmail());
        simpleMailMessage.setSubject("¡Saludos, " + seller.getName() + ", te han realizado una pregunta en la tienda " + storeName);
        simpleMailMessage.setText("Alguien te ha realizado una pregunta en la tienda " + storeName + ", específicamente en la categoría de producto " + categoryName + ".\n" +
                "La pregunta realizada fue: \n" + question +  "\n\n" +
                EMAIL_FOOTER + "\n\n" +
                EMAIL_EMITTER);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailToCustomerForAnswerPosted(String storeName, String categoryName, UserDto customer, ProductQuestionDto productQuestionDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("¡Saludos, " + customer.getName() + ", te han respondido la pregunta que realizaste en la tienda " + storeName);
        simpleMailMessage.setText("El vendedor de la tienda " + storeName + " te ha respondido pregunta.\n\n\n" +
                "Tu pregunta fue: \n" + productQuestionDto.getQuestion() + ", realizada el " + productQuestionDto.getQuestionDate() + ".\n\n" +
                "La pregunta por parte del vendedor es: \n" + productQuestionDto.getResponse() +  "\n\n" +
                EMAIL_FOOTER + "\n\n" +
                EMAIL_EMITTER);
        javaMailSender.send(simpleMailMessage);
    }
}
