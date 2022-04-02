package co.edu.udea.ayds2.services.web.implementations;

import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.services.web.interfaces.WebRequest;
import org.springframework.stereotype.Service;

@Service
public class WebClientRequest implements WebRequest {
    @Override
    public UserDto requestUserInformationById(String userId) {
        return UserDto.builder().build();
    }
}
