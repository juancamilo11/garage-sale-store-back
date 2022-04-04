package co.edu.udea.ayds2.controller;

import co.edu.udea.ayds2.dto.user.BasicUserInfo;
import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.dto.user.UserVisualizationDto;
import co.edu.udea.ayds2.proxy.UserServiceProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    private final UserServiceProxy userServiceProxy;

    @PutMapping("/put/user")
    public ResponseEntity<UserDto> updateUserInfo(@RequestBody UserDto userDto) {
        UserDto savedUser = this.userServiceProxy.updateUserInfo(userDto);
        return getResponseEntity(userDto.getId() != null, savedUser, null);
    }

    @PostMapping("/post/user/{id}")
    public ResponseEntity<UserDto> createUserBasicInfoIfNotExists(@PathVariable String id, @RequestBody BasicUserInfo basicUserInfo) {
        UserDto savedUser = this.userServiceProxy.createUserBasicInfoIfNotExists(basicUserInfo);
        return getResponseEntity(savedUser.getId() != null, savedUser, null);
    }

    @GetMapping("/get/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        UserDto userDto = this.userServiceProxy.getUserById(id);
        return getResponseEntity(userDto.getId() != null, userDto, null);
    }

    @PostMapping("/get/users/id")
    public ResponseEntity<List<UserDto>> getUsersByIds(@RequestBody List<String> userIdList) {
        List<UserDto> userDtoList = this.userServiceProxy.getUsersByIds(userIdList);
        return getResponseEntity(!userDtoList.isEmpty(), userDtoList, Collections.emptyList());
    }

    @GetMapping("/get/store/views/users/{storeId}")
    public ResponseEntity<List<UserVisualizationDto>> getViewersInfo(@PathVariable String storeId) {
        List<UserVisualizationDto> userVisualizationList = this.userServiceProxy.getViewsByStoreId(storeId);
        return getResponseEntity(!userVisualizationList.isEmpty(), userVisualizationList, Collections.emptyList());
    }

    private <T> ResponseEntity<T> getResponseEntity(Boolean response, T value, T errorValue ) {
        return response.equals(Boolean.TRUE) ?
                new ResponseEntity<>(value, HttpStatus.OK) :
                new ResponseEntity<>(errorValue, HttpStatus.BAD_REQUEST);
    }
}
