package co.edu.udea.ayds2.services.email.implementations;

import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.dto.store.product.ProductQuestionDto;
import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.services.email.interfaces.MailSender;
import org.springframework.stereotype.Service;

@Service
public class SendGridSender implements MailSender {

    // Docs at https://medium.com/javarevisited/sending-emails-with-sendgrid-and-spring-boot-81e9637a1f05

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

    @Override
    public void sendEmailToUserForNewStoreCreated(GarageSaleStoreDto garageSaleStoreDto, UserDto seller) {
        //Another implemententation
    }

    @Override
    public void sendEmailToSellerForNewQuestionPosted(String storeName, String categoryName, UserDto seller, String question) {
        //Another implemententation
    }

    @Override
    public void sendEmailToCustomerForAnswerPosted(String storeName, String categoryName, UserDto customer, ProductQuestionDto productQuestionDto) {
        //Another implemententation
    }
}
