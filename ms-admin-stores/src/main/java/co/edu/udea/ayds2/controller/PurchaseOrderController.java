package co.edu.udea.ayds2.controller;

import co.edu.udea.ayds2.controller.interfaces.ControllerOperation;
import co.edu.udea.ayds2.dto.helpers.enums.EnumServerResponse;
import co.edu.udea.ayds2.dto.store.PurchaseOrderDto;
import co.edu.udea.ayds2.proxy.PurchaseOrderServiceProxy;
import co.edu.udea.ayds2.services.email.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class PurchaseOrderController implements ControllerOperation {

    private final PurchaseOrderServiceProxy purchaseOrderServiceProxy;
    private EmailSenderService emailSenderService;


    @PostMapping("/post/purchase-order")
    public ResponseEntity<String> registerPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrderDto result = this.purchaseOrderServiceProxy.postPurchaseOrder(purchaseOrderDto);
        return getResponseEntity(result.getOrderId() != null,
                EnumServerResponse.SUCCESS_RESPONSE.getDescription(),
                EnumServerResponse.ERROR_RESPONSE.getDescription());
    }

    @PostMapping("/post/accept/order/{orderId}")
    public ResponseEntity<String> approveOrderById(@PathVariable String orderId) {
        //Obtener la orden de compra y enviarla a una cola de Rabbit
        boolean result = this.purchaseOrderServiceProxy.approveOrderById(orderId);
        return getResponseEntity(result,
                EnumServerResponse.SUCCESS_RESPONSE.getDescription(),
                EnumServerResponse.ERROR_RESPONSE.getDescription());
    }

    @PostMapping("/post/decline/order/{orderId}")
    public ResponseEntity<String> declineOrderById(@PathVariable String orderId) {
        //Obtener la orden de compra y enviarla a una cola de Rabbit
        boolean result = this.purchaseOrderServiceProxy.declineOrderById(orderId);
        return getResponseEntity(result,
                EnumServerResponse.SUCCESS_RESPONSE.getDescription(),
                EnumServerResponse.ERROR_RESPONSE.getDescription());
    }

    @GetMapping("/get/purchase-order/{type}/{id}")
    public ResponseEntity<List<PurchaseOrderDto>> getAllPurchaseOrderByType(@PathVariable String type, @PathVariable String id) {
        List<PurchaseOrderDto> purchaseOrderList = this.purchaseOrderServiceProxy.getPurchaseOrdersByTypeAndId(type, id);
        return getResponseEntity(purchaseOrderList != null,purchaseOrderList, null);
    }
}
