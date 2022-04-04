package co.edu.udea.ayds2.mapper;

import co.edu.udea.ayds2.collection.user.User;
import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.mapper.interfaces.UserMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserMapperImpl implements UserMapper {

    private static UserMapper instance;

    private UserMapperImpl(){}

    public static synchronized UserMapper getInstance(){
        if(instance == null){
            instance = new UserMapperImpl();
        }
        return instance;
    }

    @Override
    public Function<UserDto, User> mapFromDtoToEntity() {
        return (userDto) -> User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .photoUrl(userDto.getPhotoUrl())
                .occupation(userDto.getOccupation())
                .cellphone(userDto.getCellphone())
                .email(userDto.getEmail())
                .postalCode(userDto.getPostalCode())
                .colombianState(userDto.getColombianState())
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .dateOfBirth(userDto.getDateOfBirth())
                .registerDate(userDto.getRegisterDate())
                .build();
    }

    @Override
    public Function<User, UserDto> mapFromEntityToDto() {
        return (user) -> UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .photoUrl(user.getPhotoUrl())
                .occupation(user.getOccupation())
                .cellphone(user.getCellphone())
                .email(user.getEmail())
                .postalCode(user.getPostalCode())
                .colombianState(user.getColombianState())
                .phone(user.getPhone())
                .address(user.getAddress())
                .dateOfBirth(user.getDateOfBirth())
                .registerDate(user.getRegisterDate())
                .build();
    }

}
