package co.edu.udea.ayds2.controller;

import co.edu.udea.ayds2.collection.store.GarageSaleStore;
import co.edu.udea.ayds2.controller.interfaces.ControllerOperation;
import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.helpers.response.EnumResponseStatus;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.dto.store.PurchaseOrderDto;
import co.edu.udea.ayds2.dto.store.product.ProductQuestionDto;
import co.edu.udea.ayds2.monitoring.TraceabilityEmitter;
import co.edu.udea.ayds2.proxy.GarageSaleStoreServiceProxy;
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
public class GarageSaleStoreController implements ControllerOperation {

    private final GarageSaleStoreServiceProxy garageSaleStoreServiceProxy;

    @PostMapping("/post/store")
    public ResponseEntity<String> createStore(@RequestBody GarageSaleStoreDto garageSaleStoreDto) {
        boolean response = this.garageSaleStoreServiceProxy.createStore(garageSaleStoreDto);
        return getResponseEntity(response, "Success", "Error");
    }

    @GetMapping("/get/stores")
    public ResponseEntity<List<GarageSaleStore>> getAllActiveStores() {
        List<GarageSaleStore> garageSaleStoreList = this.garageSaleStoreServiceProxy.getAllActiveStores();
        boolean response = !garageSaleStoreList.isEmpty();
        return getResponseEntity(response,garageSaleStoreList, Collections.emptyList());
    }

    @GetMapping("/get/stores/seller/{sellerId}")
    public ResponseEntity<List<GarageSaleStore>> getAllActiveStoresBySellerId(@PathVariable String sellerId) {
        List<GarageSaleStore> garageSaleStoreList = this.garageSaleStoreServiceProxy.getAllActiveStoresBySellerId(sellerId);
        boolean response = !garageSaleStoreList.isEmpty();
        return getResponseEntity(response,garageSaleStoreList, Collections.emptyList());
    }

    @PostMapping("/post/question/{storeId}/{categoryName}/{productId}")
    public ResponseEntity<String> postNewQuestionToProduct(@PathVariable String storeId,
                                                     @PathVariable String categoryName,
                                                     @PathVariable String productId,
                                                     @RequestBody ProductQuestionDto productQuestionDto) {
        boolean result = this.garageSaleStoreServiceProxy.postNewQuestion(storeId, categoryName, productId, productQuestionDto);
        return getResponseEntity(result,"Success", "Error");
    }

    @PostMapping("/post/answer/{storeId}/{categoryName}/{productId}")
    public ResponseEntity<String> postAnswerToProductQuestion(@PathVariable String storeId,
                                                           @PathVariable String categoryName,
                                                           @PathVariable String productId,
                                                           @RequestBody ProductQuestionDto productQuestionDto) {
        boolean result = this.garageSaleStoreServiceProxy.postAnswerToProductQuestion(storeId, categoryName, productId, productQuestionDto);
        return getResponseEntity(result,"Success", "Error");
    }

    @PostMapping("/post/view/{storeId}/{userId}")
    public ResponseEntity<String> registerStoreView(@PathVariable String storeId,
                                                    @PathVariable String userId) {
        boolean result = this.garageSaleStoreServiceProxy.postStoreView(storeId, userId);
        return getResponseEntity(result,"Success", "Error");
    }

    @GetMapping("/get/store/{id}")
    public ResponseEntity<GarageSaleStoreDto> getStoreById(@PathVariable String id) {
        GarageSaleStoreDto garageSaleStoreDto = this.garageSaleStoreServiceProxy.getStoreById(id);
        return getResponseEntity(garageSaleStoreDto.getId() != null, garageSaleStoreDto, null);
    }

}
