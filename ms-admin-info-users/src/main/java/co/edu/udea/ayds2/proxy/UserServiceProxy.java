package co.edu.udea.ayds2.proxy;

import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.helpers.response.EnumResponseStatus;
import co.edu.udea.ayds2.dto.user.BasicUserInfo;
import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.dto.user.UserVisualizationDto;
import co.edu.udea.ayds2.monitoring.TraceabilityEmitter;
import co.edu.udea.ayds2.services.email.EmailSenderService;
import co.edu.udea.ayds2.services.user.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceProxy implements UserService {

    private final UserService userService;
    private final TraceabilityEmitter traceabilityEmitter;
    private final AppServerResponse appServerResponse;
    private final EmailSenderService emailSenderService;

    public UserServiceProxy(UserService userService, TraceabilityEmitter traceabilityEmitter, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.traceabilityEmitter = traceabilityEmitter;
        this.appServerResponse = new AppServerResponse();
        this.emailSenderService = emailSenderService;
    }

    @Override
    public UserDto updateUserInfo(UserDto userDto) {
        UserDto userDtoUpdated = this.userService.updateUserInfo(userDto);
        boolean result = userDtoUpdated != null;
        getAppServerResponseOfCurrentProcess(result, "[ms-admin-info-users] | {updateUserInfo} | Id: " + Objects.requireNonNull(userDtoUpdated).getId());
        sendEmailToUserForInformationUpdated(userDtoUpdated);
        traceabilityEmitter.emitTraceability(appServerResponse);
        return userDtoUpdated;
    }

    @Override
    public List<UserDto> getUsersByIds(List<String> idList) {
        List<UserDto> usersByIds = this.userService.getUsersByIds(idList);
        boolean result = !usersByIds.isEmpty();
        getAppServerResponseOfCurrentProcess(result, "[ms-admin-info-users] | {getUsersByIds} | Found: " + usersByIds.size());
        traceabilityEmitter.emitTraceability(appServerResponse);
        return usersByIds;
    }

    @Override
    public UserDto getUserById(String id) {
        UserDto userDto = this.userService.getUserById(id);
        boolean result = userDto != null;
        this.getAppServerResponseOfCurrentProcess(result, "[ms-admin-info-users] | {getUserById} | Id: " + Objects.requireNonNull(userDto).getId());
        traceabilityEmitter.emitTraceability(appServerResponse);
        return userDto;
    }

    @Override
    public UserDto createUserBasicInfoIfNotExists(BasicUserInfo basicUserInfo) {
        if(!this.getUserExistenceById(basicUserInfo.getId())) {
            this.sendEmailToUserForWelcomeToTheApp(basicUserInfo);
        }
        UserDto userDto = this.userService.createUserBasicInfoIfNotExists(basicUserInfo);
        boolean result = userDto != null;
        this.getAppServerResponseOfCurrentProcess(result, "[ms-admin-info-users] | {createUserBasicInfoIfNotExists} | Id: " + Objects.requireNonNull(userDto).getId());
        traceabilityEmitter.emitTraceability(appServerResponse);
        return userDto;
    }

    @Override
    public boolean getUserExistenceById(String id) {
        return this.userService.getUserExistenceById(id);
    }

    @Override
    public List<UserVisualizationDto> getViewsByStoreId(String storeId) {
        List<UserVisualizationDto> viewsByStoreId = this.userService.getViewsByStoreId(storeId);
        boolean result = !viewsByStoreId.isEmpty();
        getAppServerResponseOfCurrentProcess(result, "[ms-admin-info-users] | {getViewsByStoreId} | Store Id: " + storeId + " | Found: " + viewsByStoreId.size());
        traceabilityEmitter.emitTraceability(appServerResponse);
        return viewsByStoreId;
    }

    private void getAppServerResponseOfCurrentProcess(boolean result, String operationDescription) {
        appServerResponse.setCurrentDate(LocalDateTime.now());
        appServerResponse.setStatus(result ? EnumResponseStatus.OK : EnumResponseStatus.ERROR);
        appServerResponse.setDetailInfo(operationDescription);
    }

    private void sendEmailToUserForInformationUpdated(UserDto userDtoUpdated) {
        this.emailSenderService.getMailSender().sendEmailToUserForInformationUpdated(userDtoUpdated);
    }

    private void sendEmailToUserForWelcomeToTheApp(BasicUserInfo userDtoUpdated) {
        this.emailSenderService.getMailSender().sendEmailToUserForWelcome(userDtoUpdated);
    }
}
