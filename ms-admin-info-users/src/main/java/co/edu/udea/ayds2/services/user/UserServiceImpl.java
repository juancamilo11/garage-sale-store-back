package co.edu.udea.ayds2.services.user;

import co.edu.udea.ayds2.collection.user.User;
import co.edu.udea.ayds2.dto.helpers.enums.EnumColombianStates;
import co.edu.udea.ayds2.dto.store.StoreVisualizationDto;
import co.edu.udea.ayds2.dto.user.BasicUserInfo;
import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.dto.user.UserVisualizationDto;
import co.edu.udea.ayds2.mapper.interfaces.StoreVisualizationMapper;
import co.edu.udea.ayds2.mapper.interfaces.UserMapper;
import co.edu.udea.ayds2.repository.StoreVisualizationRepository;
import co.edu.udea.ayds2.repository.UserRepository;
import co.edu.udea.ayds2.services.user.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
@Primary
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final StoreVisualizationRepository storeVisualizationRepository;
    private final StoreVisualizationMapper storeVisualizationMapper;

    @Override
    public UserDto updateUserInfo(UserDto userDto) {
        return this.userMapper.mapFromEntityToDto()
                .apply(this.userRepository
                        .save(this.userMapper.mapFromDtoToEntity().apply(userDto)));
    }

    @Override
    public List<UserDto> getUsersByIds(List<String> idList) {
        List<User> userList = new ArrayList<>();
        this.userRepository.findAllById(idList).forEach(userList::add);
        return userList.stream()
                .map(user -> this.userMapper.mapFromEntityToDto().apply(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(String id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional.map(user -> this.userMapper.mapFromEntityToDto().apply(user))
                .orElseThrow();
    }

    public boolean getUserExistenceById(String id) {
        return this.userRepository.existsById(id);
    }

    @Override
    public UserDto createUserBasicInfoIfNotExists(BasicUserInfo basicUserInfo) {
        Optional<User> userOptional = this.userRepository.findById(basicUserInfo.getId());
        if(userOptional.isEmpty()) {
            User userToSave = User.builder()
                    .id(basicUserInfo.getId())
                    .name(basicUserInfo.getName())
                    .photoUrl(basicUserInfo.getPhotoUrl())
                    .email(basicUserInfo.getEmail())
                    .colombianState(EnumColombianStates.NN)
                    .registerDate(basicUserInfo.getCreationTime())
                    .occupation("NN")
                    .cellphone("000000000")
                    .postalCode("000000")
                    .phone("000000000")
                    .address("NN")
                    .dateOfBirth(LocalDate.now())
                    .build();
            return this.userMapper
                    .mapFromEntityToDto()
                    .apply(this.userRepository.save(userToSave));
        } else {
            return this.userMapper.mapFromEntityToDto().apply(userOptional.get());
        }
    }

    @Override
    public List<UserVisualizationDto> getViewsByStoreId(String storeId) {
        return this.storeVisualizationRepository
                .findById(storeId)
                .map(storeVisualization -> this.storeVisualizationMapper
                        .mapFromEntityToDto()
                        .apply(storeVisualization))
                .map(StoreVisualizationDto::getUserVisualizationList)
                .orElse(Collections.emptyList());
    }

}
