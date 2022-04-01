package co.edu.udea.ayds2.proxy;

import co.edu.udea.ayds2.collection.store.GarageSaleStore;
import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.helpers.response.EnumResponseStatus;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.dto.store.product.ProductQuestionDto;
import co.edu.udea.ayds2.monitoring.TraceabilityEmitter;
import co.edu.udea.ayds2.monitoring.TraceabilityEmitterImpl;
import co.edu.udea.ayds2.services.interfaces.GarageSaleStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GarageSaleStoreServiceProxy implements GarageSaleStoreService {

    private final GarageSaleStoreService garageSaleStoreService;
    private final TraceabilityEmitter traceabilityEmitter;
    private final AppServerResponse appServerResponse;

    @Autowired
    public GarageSaleStoreServiceProxy(@Qualifier("realGarageSaleStoreServiceImpl") GarageSaleStoreService garageSaleStoreService, TraceabilityEmitter traceabilityEmitter) {
        this.garageSaleStoreService = garageSaleStoreService;
        this.traceabilityEmitter = traceabilityEmitter;
        this.appServerResponse = new AppServerResponse();
    }

    @Override
    public boolean createStore(GarageSaleStoreDto garageSaleStoreDto) {
        boolean result = this.garageSaleStoreService.createStore(garageSaleStoreDto);
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se ha creado una tienda");
        traceabilityEmitter.emitTraceability(appServerResponse);
        return result;
    }

    @Override
    public List<GarageSaleStore> getAllActiveStores() {
        List<GarageSaleStore> garageSaleStoreList = this.garageSaleStoreService.getAllActiveStores();
        boolean result = !garageSaleStoreList.isEmpty();
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se ha solicitado todas las tiendas activas");
        traceabilityEmitter.emitTraceability(appServerResponse);
        return garageSaleStoreList;
    }

    @Override
    public boolean postNewQuestion(String storeId, String categoryName, String productId, ProductQuestionDto productQuestionDto) {
        boolean result = this.garageSaleStoreService.postNewQuestion(storeId,categoryName, productId, productQuestionDto);
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se ha creado una nueva pregunta");
        traceabilityEmitter.emitTraceability(appServerResponse);
        return result;
    }

    @Override
    public boolean postAnswerToProductQuestion(String storeId, String categoryName, String productId, ProductQuestionDto productQuestionDto) {
        boolean result = this.garageSaleStoreService.postAnswerToProductQuestion(storeId, categoryName, productId, productQuestionDto);
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se ha creado una nueva pregunta");
        traceabilityEmitter.emitTraceability(appServerResponse);
        return result;
    }

    @Override
    public List<GarageSaleStore> getAllActiveStoresBySellerId(String sellerId) {
        List<GarageSaleStore> garageSaleStoreList = this.garageSaleStoreService.getAllActiveStoresBySellerId(sellerId);
        boolean result = !garageSaleStoreList.isEmpty();
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se ha solicitado todas las tiendas del seller");
        traceabilityEmitter.emitTraceability(appServerResponse);
        return garageSaleStoreList;
    }

    @Override
    public boolean postStoreView(String storeId, String userId) {
        boolean result = this.garageSaleStoreService.postStoreView(storeId, userId);
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se ha creado una nueva tienda");
        traceabilityEmitter.emitTraceability(appServerResponse);
        return false;
    }

    @Override
    public GarageSaleStoreDto getStoreById(String id) {
        GarageSaleStoreDto garageSaleStoreDto = this.garageSaleStoreService.getStoreById(id);
        boolean result = garageSaleStoreDto != null;
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se ha solicitado todas las tiendas del seller");
        traceabilityEmitter.emitTraceability(appServerResponse);
        return garageSaleStoreDto;
    }

    private void getAppServerResponseOfCurrentProcess(boolean result, String operationDescription) {
        appServerResponse.setCurrentDate(LocalDate.now());
        appServerResponse.setStatus(result ? EnumResponseStatus.OK : EnumResponseStatus.ERROR);
        appServerResponse.setDetailInfo(operationDescription);
    }
}
