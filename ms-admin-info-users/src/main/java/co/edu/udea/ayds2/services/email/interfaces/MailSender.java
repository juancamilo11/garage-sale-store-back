package co.edu.udea.ayds2.services.email.interfaces;

import co.edu.udea.ayds2.dto.user.BasicUserInfo;
import co.edu.udea.ayds2.dto.user.UserDto;

public interface MailSender {
    void sendEmailToUserForInformationUpdated(UserDto userDto);
    void sendEmailToUserForWelcome(BasicUserInfo basicUserInfo);
}
