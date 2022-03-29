package co.edu.udea.ayds2.controller;

import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.helpers.response.EnumResponseStatus;
import co.edu.udea.ayds2.dto.store.StoreVisualizationDto;
import co.edu.udea.ayds2.dto.user.BasicUserInfo;
import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.dto.user.UserVisualizationDto;
import co.edu.udea.ayds2.monitoring.TraceabilityEmitter;
import co.edu.udea.ayds2.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    private final TraceabilityEmitter traceabilityEmitter;
    private final UserService userService;

    @PutMapping("/put/user")
    public ResponseEntity<UserDto> updateUserInfo(@RequestBody UserDto userDto) {

        UserDto savedUser = this.userService.updateUserInfo(userDto);

//        emitAppServiceResponse(savedUser.getId() != null ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
//                savedUser.getId() != null  ? "[POST - Success] User, Id " + userDto.getId() :
//                "[POST - Error] User, Id " + savedUser.getId());

        return getResponseEntity(userDto.getId() != null, savedUser, null);
    }

    @PostMapping("/post/user/{id}")
    public ResponseEntity<UserDto> createUserBasicInfoIfNotExists(@PathVariable String id, @RequestBody BasicUserInfo basicUserInfo) {

        UserDto savedUser = this.userService.createUserBasicInfoIfNotExists(basicUserInfo);

//        emitAppServiceResponse(savedUser.getId() != null ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
//                savedUser.getId() != null  ? "[POST - Success] User, Id " + userDto.getId() :
//                "[POST - Error] User, Id " + savedUser.getId());

        return getResponseEntity(savedUser.getId() != null, savedUser, null);
    }

    @GetMapping("/get/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {

        UserDto userDto = this.userService.getUserById(id);

//        emitAppServiceResponse(userDto.getId() != null ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
//                userDto.getId() != null ? "[GET - Success] Get User By ID, found " +  userDto:
//                        "[GET - Error] Get User By ID");

        return getResponseEntity(userDto.getId() != null, userDto, null);
    }

    @PostMapping("/get/users/id")
    public ResponseEntity<List<UserDto>> getUsersByIds(@RequestBody List<String> userIdList) {

        List<UserDto> userDtoList = this.userService.getUsersByIds(userIdList);

//        emitAppServiceResponse(!userDtoList.isEmpty() ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
//                !userDtoList.isEmpty() ? "[GET - Success] Get Users By IDs, found " +  userDtoList.size():
//                        "[GET - Error] Get Users By IDs");

        return getResponseEntity(!userDtoList.isEmpty(), userDtoList, Collections.emptyList());
    }



    @GetMapping("/get/store/views/users/{storeId}")
    public ResponseEntity<List<UserVisualizationDto>> getViewersInfo(@PathVariable String storeId) {

        List<UserVisualizationDto> userVisualizationList = this.userService.getViewsByStoreId(storeId);

//        emitAppServiceResponse(!userDtoList.isEmpty() ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
//                !userDtoList.isEmpty() ? "[GET - Success] Get Users By IDs, found " +  userDtoList.size():
//                        "[GET - Error] Get Users By IDs");

        return getResponseEntity(!userVisualizationList.isEmpty(), userVisualizationList, Collections.emptyList());
    }

    private void emitAppServiceResponse(EnumResponseStatus responseStatus, String additionalInfo) {
        AppServerResponse appServerResponse = new AppServerResponse(responseStatus, LocalDate.now(), additionalInfo);
        this.traceabilityEmitter.emitTraceability(appServerResponse);
    }

    private <T> ResponseEntity<T> getResponseEntity(Boolean response, T value, T errorValue ) {
        return response.equals(Boolean.TRUE) ?
                new ResponseEntity<>(value, HttpStatus.OK) :
                new ResponseEntity<>(errorValue, HttpStatus.BAD_REQUEST);
    }

}
