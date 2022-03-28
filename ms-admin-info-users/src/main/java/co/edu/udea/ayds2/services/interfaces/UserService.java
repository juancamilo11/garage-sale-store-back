package co.edu.udea.ayds2.services.interfaces;

import co.edu.udea.ayds2.dto.user.BasicUserInfo;
import co.edu.udea.ayds2.dto.user.UserDto;

import java.util.List;

public interface UserService {
    UserDto updateUserInfo(UserDto userDto);
    List<UserDto> getUsersByIds(List<String> idList);
    UserDto getUserById(String id);

    UserDto createUserBasicInfoIfNotExists(BasicUserInfo basicUserInfo);
}
