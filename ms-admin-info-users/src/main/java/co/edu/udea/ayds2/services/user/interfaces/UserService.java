package co.edu.udea.ayds2.services.user.interfaces;

import co.edu.udea.ayds2.dto.user.BasicUserInfo;
import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.dto.user.UserVisualizationDto;

import java.util.List;

public interface UserService {
    UserDto updateUserInfo(UserDto userDto);
    List<UserDto> getUsersByIds(List<String> idList);
    UserDto getUserById(String id);
    UserDto createUserBasicInfoIfNotExists(BasicUserInfo basicUserInfo);
    boolean getUserExistenceById(String id);
    List<UserVisualizationDto> getViewsByStoreId(String storeId);
}
