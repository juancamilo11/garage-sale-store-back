package co.edu.udea.ayds2.services.email.interfaces;

import co.edu.udea.ayds2.dto.user.UserDto;

public interface MailSender {
    void sendEmailForOrderCreated(String orderId, UserDto sellerInfo, UserDto customerInfo);
    void sendEmailForOrderApproved(String orderId, UserDto customerInfo);
    void sendEmailForOrderDeclined(String orderId, UserDto customerInfo);
}
