package co.edu.udea.ayds2.services.web.interfaces;

import co.edu.udea.ayds2.dto.user.UserDto;

public interface WebRequest {
    UserDto requestUserInformationById(String userId);
}
