package co.edu.udea.ayds2.services.email.interfaces;

import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.dto.store.product.ProductQuestionDto;
import co.edu.udea.ayds2.dto.user.UserDto;

public interface MailSender {
    void sendEmailForOrderCreated(String orderId, UserDto sellerInfo, UserDto customerInfo);
    void sendEmailForOrderApproved(String orderId, UserDto customerInfo);
    void sendEmailForOrderDeclined(String orderId, UserDto customerInfo);
    void sendEmailToUserForNewStoreCreated(GarageSaleStoreDto garageSaleStoreDto, UserDto seller);
    void sendEmailToSellerForNewQuestionPosted(String storeName, String categoryName, UserDto seller, String question);
    void sendEmailToCustomerForAnswerPosted(String storeName, String categoryName, UserDto customer, ProductQuestionDto productQuestionDto);
}
