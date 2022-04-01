package co.edu.udea.ayds2.proxy;

import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.helpers.response.EnumResponseStatus;
import co.edu.udea.ayds2.dto.store.PurchaseOrderDto;
import co.edu.udea.ayds2.monitoring.TraceabilityEmitter;
import co.edu.udea.ayds2.services.interfaces.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseOrderServiceProxy implements PurchaseOrderService {

    private final PurchaseOrderService purchaseOrderService;
    private final TraceabilityEmitter traceabilityEmitter;
    private final AppServerResponse appServerResponse;

    public PurchaseOrderServiceProxy(@Qualifier("realPurchaseOrderServiceImpl") PurchaseOrderService purchaseOrderService, TraceabilityEmitter traceabilityEmitter) {
        this.purchaseOrderService = purchaseOrderService;
        this.traceabilityEmitter = traceabilityEmitter;
        this.appServerResponse = new AppServerResponse();
    }

    @Override
    public PurchaseOrderDto postPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrderDto purchaseOrder = this.purchaseOrderService.postPurchaseOrder(purchaseOrderDto);
        boolean result = purchaseOrder != null;
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se ha creado una orden de compra");
        traceabilityEmitter.emitTraceability(appServerResponse);
        return purchaseOrder;
    }

    @Override
    public boolean approveOrderById(String orderId) {
        boolean result = this.purchaseOrderService.approveOrderById(orderId);
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se ha aprobado una orden de compra");
        traceabilityEmitter.emitTraceability(appServerResponse);
        return result;
    }

    @Override
    public boolean declineOrderById(String orderId) {
        boolean result = this.purchaseOrderService.declineOrderById(orderId);
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se ha rechazado una orden de compra");
        traceabilityEmitter.emitTraceability(appServerResponse);
        return result;
    }

    @Override
    public List<PurchaseOrderDto> getPurchaseOrdersByTypeAndId(String type, String id) {
        List<PurchaseOrderDto> purchaseOrderDtoList = this.purchaseOrderService.getPurchaseOrdersByTypeAndId(type, id);
        boolean result = !purchaseOrderDtoList.isEmpty();
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se han solicitado las ordenes por tipo e Id");
        traceabilityEmitter.emitTraceability(appServerResponse);
        return purchaseOrderDtoList;
    }

    private void getAppServerResponseOfCurrentProcess(boolean result, String operationDescription) {
        appServerResponse.setCurrentDate(LocalDate.now());
        appServerResponse.setStatus(result ? EnumResponseStatus.OK : EnumResponseStatus.ERROR);
        appServerResponse.setDetailInfo(operationDescription);
    }
}
