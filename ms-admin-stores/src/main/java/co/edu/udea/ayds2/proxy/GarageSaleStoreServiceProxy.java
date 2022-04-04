package co.edu.udea.ayds2.proxy;

import co.edu.udea.ayds2.collection.store.GarageSaleStore;
import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.helpers.response.EnumResponseStatus;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.dto.store.product.ProductQuestionDto;
import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.monitoring.TraceabilityEmitter;
import co.edu.udea.ayds2.services.email.EmailSenderService;
import co.edu.udea.ayds2.services.store.interfaces.GarageSaleStoreService;
import co.edu.udea.ayds2.services.web.interfaces.WebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GarageSaleStoreServiceProxy implements GarageSaleStoreService {

    private final GarageSaleStoreService garageSaleStoreService;
    private final TraceabilityEmitter traceabilityEmitter;
    private final AppServerResponse appServerResponse;
    private final EmailSenderService emailSenderService;
    private final WebRequest webRequest;

    @Autowired
    public GarageSaleStoreServiceProxy(@Qualifier("realGarageSaleStoreServiceImpl") GarageSaleStoreService garageSaleStoreService, TraceabilityEmitter traceabilityEmitter, EmailSenderService emailSenderService, WebRequest webRequest) {
        this.garageSaleStoreService = garageSaleStoreService;
        this.traceabilityEmitter = traceabilityEmitter;
        this.appServerResponse = new AppServerResponse();
        this.emailSenderService = emailSenderService;
        this.webRequest = webRequest;
    }

    @Override
    public boolean createStore(GarageSaleStoreDto garageSaleStoreDto) {
        boolean result = this.garageSaleStoreService.createStore(garageSaleStoreDto);
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] | {createStore} | store Id: " + garageSaleStoreDto.getId());
        sendEmailToUserForNewStoreCreated(garageSaleStoreDto);
        traceabilityEmitter.emitTraceability(appServerResponse);
        return result;
    }

    @Override
    public List<GarageSaleStore> getAllActiveStores() {
        List<GarageSaleStore> garageSaleStoreList = this.garageSaleStoreService.getAllActiveStores();
        boolean result = !garageSaleStoreList.isEmpty();
        getAppServerResponseOfCurrentProcess(result, "[ms-admin-stores] | {getAllActiveStores} | Found: " + garageSaleStoreList.size());
        traceabilityEmitter.emitTraceability(appServerResponse);
        return garageSaleStoreList;
    }

    @Override
    public boolean postNewQuestion(String storeId, String categoryName, String productId, ProductQuestionDto productQuestionDto) {
        boolean result = this.garageSaleStoreService.postNewQuestion(storeId,categoryName, productId, productQuestionDto);
        getAppServerResponseOfCurrentProcess(result, "[ms-admin-stores] | {postNewQuestion} | Question Id: " + productQuestionDto.getId());
        sendEmailToSellerForNewQuestionPosted(storeId, categoryName, productQuestionDto);
        traceabilityEmitter.emitTraceability(appServerResponse);
        return result;
    }

    @Override
    public boolean postAnswerToProductQuestion(String storeId, String categoryName, String productId, ProductQuestionDto productQuestionDto) {
        boolean result = this.garageSaleStoreService.postAnswerToProductQuestion(storeId, categoryName, productId, productQuestionDto);
        getAppServerResponseOfCurrentProcess(result, "[ms-admin-stores] | {postAnswerToProductQuestion} | Question Id: " + productQuestionDto.getId());
        sendEmailToCustomerForAnswerPosted(storeId, categoryName, productQuestionDto);
        traceabilityEmitter.emitTraceability(appServerResponse);
        return result;
    }

    @Override
    public List<GarageSaleStore> getAllActiveStoresBySellerId(String sellerId) {
        List<GarageSaleStore> garageSaleStoreList = this.garageSaleStoreService.getAllActiveStoresBySellerId(sellerId);
        boolean result = !garageSaleStoreList.isEmpty();
        getAppServerResponseOfCurrentProcess(result, "[ms-admin-stores] | {getAllActiveStoresBySellerId} | Found: " + garageSaleStoreList.size());
        traceabilityEmitter.emitTraceability(appServerResponse);
        return garageSaleStoreList;
    }

    @Override
    public boolean postStoreView(String storeId, String userId) {
        boolean result = this.garageSaleStoreService.postStoreView(storeId, userId);
        getAppServerResponseOfCurrentProcess(result, "[ms-admin-stores] | {postStoreView} | Store Id: " + storeId);
        traceabilityEmitter.emitTraceability(appServerResponse);
        return false;
    }

    @Override
    public GarageSaleStoreDto getStoreById(String id) {
        GarageSaleStoreDto garageSaleStoreDto = this.garageSaleStoreService.getStoreById(id);
        boolean result = garageSaleStoreDto != null;
        getAppServerResponseOfCurrentProcess(result, "[ms-admin-stores] | {getStoreById} | Store Id: " + id);
        traceabilityEmitter.emitTraceability(appServerResponse);
        return garageSaleStoreDto;
    }

    private void getAppServerResponseOfCurrentProcess(boolean result, String operationDescription) {
        appServerResponse.setCurrentDate(LocalDateTime.now());
        appServerResponse.setStatus(result ? EnumResponseStatus.OK : EnumResponseStatus.ERROR);
        appServerResponse.setDetailInfo(operationDescription);
    }

    private void sendEmailToUserForNewStoreCreated(GarageSaleStoreDto garageSaleStoreDto) {
        UserDto seller = this.webRequest.requestUserInformationById(garageSaleStoreDto.getSellerId());
        this.emailSenderService.getMailSender().sendEmailToUserForNewStoreCreated(garageSaleStoreDto, seller);
    }

    private void sendEmailToSellerForNewQuestionPosted(String storeId, String categoryName, ProductQuestionDto productQuestionDto) {
        GarageSaleStoreDto garageSaleStoreDto = this.garageSaleStoreService.getStoreById(storeId);
        UserDto seller = this.webRequest.requestUserInformationById(garageSaleStoreDto.getSellerId());
        this.emailSenderService.getMailSender().sendEmailToSellerForNewQuestionPosted(garageSaleStoreDto.getStoreName(), categoryName, seller, productQuestionDto.getQuestion());
    }

    private void sendEmailToCustomerForAnswerPosted(String storeId, String categoryName, ProductQuestionDto productQuestionDto) {
        GarageSaleStoreDto garageSaleStoreDto = this.garageSaleStoreService.getStoreById(storeId);
        UserDto customer = this.webRequest.requestUserInformationById(productQuestionDto.getCustomerId());
        this.emailSenderService.getMailSender().sendEmailToCustomerForAnswerPosted(garageSaleStoreDto.getStoreName(), categoryName, customer, productQuestionDto);
    }
}
