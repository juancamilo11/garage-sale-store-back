package co.edu.udea.ayds2.services.order.interfaces;

import co.edu.udea.ayds2.dto.store.PurchaseOrderDto;

import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrderDto postPurchaseOrder(PurchaseOrderDto purchaseOrderDto);
    boolean approveOrderById(String orderId);

    boolean declineOrderById(String orderId);
    List<PurchaseOrderDto> getPurchaseOrdersByTypeAndId(String type, String id);

    PurchaseOrderDto getPurchaseOrderById(String orderId);
}
