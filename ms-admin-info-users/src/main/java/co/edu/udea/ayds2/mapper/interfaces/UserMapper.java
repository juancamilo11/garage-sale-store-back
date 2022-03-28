package co.edu.udea.ayds2.mapper.interfaces;

import co.edu.udea.ayds2.collection.user.User;
import co.edu.udea.ayds2.dto.user.UserDto;

import java.util.function.Function;

public interface UserMapper {

    Function<UserDto, User> mapFromDtoToEntity();
    Function<User, UserDto> mapFromEntityToDto();

}
