package co.edu.udea.ayds2.controller;

import co.edu.udea.ayds2.collection.store.GarageSaleStore;
import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.helpers.response.EnumResponseStatus;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.dto.store.PurchaseOrderDto;
import co.edu.udea.ayds2.dto.store.product.ProductQuestionDto;
import co.edu.udea.ayds2.monitoring.TraceabilityEmitter;
import co.edu.udea.ayds2.services.interfaces.GarageSaleStoreService;
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
public class GarageSaleStoreController {

    private final TraceabilityEmitter traceabilityEmitter;
    private final GarageSaleStoreService garageSaleStoreService;

    @PostMapping("/post/store")
    public ResponseEntity<String> createStore(@RequestBody GarageSaleStoreDto garageSaleStoreDto) {

        boolean response = this.garageSaleStoreService.createStore(garageSaleStoreDto);

        emitAppServiceResponse(response ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
                response ? "[POST - Success] GarageSaleStore, Id " + garageSaleStoreDto.getId() :
                "[POST - Error] GarageSaleStore, Id " + garageSaleStoreDto.getId());

        return getResponseEntity(response, "Success", "Error");
    }

    @GetMapping("/get/stores")
    public ResponseEntity<List<GarageSaleStore>> getAllActiveStores() {

        List<GarageSaleStore> garageSaleStoreList = this.garageSaleStoreService.getAllActiveStores();
        boolean response = !garageSaleStoreList.isEmpty();

        emitAppServiceResponse(response ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
                response ? "[GET - Success] All GarageSaleStores, found " +  garageSaleStoreList.size():
                        "[GET - Error] All GarageSaleStores");

        return getResponseEntity(response,garageSaleStoreList, Collections.emptyList());

    }

    @GetMapping("/get/stores/seller/{sellerId}")
    public ResponseEntity<List<GarageSaleStore>> getAllActiveStoresBySellerId(@PathVariable String sellerId) {

        List<GarageSaleStore> garageSaleStoreList = this.garageSaleStoreService.getAllActiveStoresBySellerId(sellerId);
        boolean response = !garageSaleStoreList.isEmpty();

        emitAppServiceResponse(response ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
                response ? "[GET - Success] All getAllActiveStoresBySellerId, found " +  garageSaleStoreList.size():
                        "[GET - Error] All getAllActiveStoresBySellerId");

        return getResponseEntity(response,garageSaleStoreList, Collections.emptyList());

    }

    @PostMapping("/post/question/{storeId}/{categoryName}/{productId}")
    public ResponseEntity<String> postNewQuestionToProduct(@PathVariable String storeId,
                                                     @PathVariable String categoryName,
                                                     @PathVariable String productId,
                                                     @RequestBody ProductQuestionDto productQuestionDto) {

        boolean result = this.garageSaleStoreService.postNewQuestion(storeId, categoryName, productId, productQuestionDto);

//        emitAppServiceResponse(result ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
//                result ? "[GET - Success] Post new Question, value: " + productQuestionDto.getQuestion():
//                        "[GET - Error] All GarageSaleStores");

        return getResponseEntity(result,"Success", "Error");
    }

    @PostMapping("/post/answer/{storeId}/{categoryName}/{productId}")
    public ResponseEntity<String> postAnswerToProductQuestion(@PathVariable String storeId,
                                                           @PathVariable String categoryName,
                                                           @PathVariable String productId,
                                                           @RequestBody ProductQuestionDto productQuestionDto) {

        boolean result = this.garageSaleStoreService.postAnswerToProductQuestion(storeId, categoryName, productId, productQuestionDto);

//        emitAppServiceResponse(result ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
//                result ? "[GET - Success] Post new Question, value: " + productQuestionDto.getQuestion():
//                        "[GET - Error] All GarageSaleStores");

        return getResponseEntity(result,"Success", "Error");
    }

    @PostMapping("/post/view/{storeId}/{userId}")
    public ResponseEntity<String> registerStoreView(@PathVariable String storeId,
                                                    @PathVariable String userId) {

        boolean result = this.garageSaleStoreService.postStoreView(storeId, userId);

//        emitAppServiceResponse(result ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
//                result ? "[GET - Success] Post new Question, value: " + productQuestionDto.getQuestion():
//                        "[GET - Error] All GarageSaleStores");

        return getResponseEntity(result,"Success", "Error");
    }

    @PostMapping("/post/purchase-order")
    public ResponseEntity<String> registerPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {

        PurchaseOrderDto result = this.garageSaleStoreService.postPurchaseOrder(purchaseOrderDto);

//        emitAppServiceResponse(result ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
//                result ? "[GET - Success] Post new Question, value: " + productQuestionDto.getQuestion():
//                        "[GET - Error] All GarageSaleStores");

        return getResponseEntity(result.getOrderId() != null,"Success", "Error");
    }

    @PostMapping("/post/accept/order/{orderId}")
    public ResponseEntity<String> approveOrderById(@PathVariable String orderId) {
        //Obtener la orden de compra y enviarla a una cola de Rabbit
        boolean result = this.garageSaleStoreService.approveOrderById(orderId);

//        emitAppServiceResponse(result ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
//                result ? "[GET - Success] Post new Question, value: " + productQuestionDto.getQuestion():
//                        "[GET - Error] All GarageSaleStores");

        return getResponseEntity(result, "Success", "Error");
    }

    @PostMapping("/post/decline/order/{orderId}")
    public ResponseEntity<String> declineOrderById(@PathVariable String orderId) {
        //Obtener la orden de compra y enviarla a una cola de Rabbit
        boolean result = this.garageSaleStoreService.declineOrderById(orderId);

//        emitAppServiceResponse(result ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
//                result ? "[GET - Success] Post new Question, value: " + productQuestionDto.getQuestion():
//                        "[GET - Error] All GarageSaleStores");

        return getResponseEntity(result, "Success", "Error");
    }




    @GetMapping("/get/purchase-order/{type}/{id}")
    public ResponseEntity<List<PurchaseOrderDto>> getAllPurchaseOrderByType(@PathVariable String type, @PathVariable String id) {

        List<PurchaseOrderDto> purchaseOrderList = this.garageSaleStoreService.getPurchaseOrder(type, id);

//        emitAppServiceResponse(result ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
//                result ? "[GET - Success] Post new Question, value: " + productQuestionDto.getQuestion():
//                        "[GET - Error] All GarageSaleStores");

        return getResponseEntity(purchaseOrderList != null,purchaseOrderList, null);
    }

    @GetMapping("/get/store/{id}")
    public ResponseEntity<GarageSaleStoreDto> getStoreById(@PathVariable String id) {

        GarageSaleStoreDto garageSaleStoreDto = this.garageSaleStoreService.getStoreById(id);

//        emitAppServiceResponse(result ? EnumResponseStatus.OK : EnumResponseStatus.ERROR,
//                result ? "[GET - Success] Post new Question, value: " + productQuestionDto.getQuestion():
//                        "[GET - Error] All GarageSaleStores");

        return getResponseEntity(garageSaleStoreDto.getId() != null, garageSaleStoreDto, null);
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
