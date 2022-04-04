package co.edu.udea.ayds2.proxy;

import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.helpers.response.EnumResponseStatus;
import co.edu.udea.ayds2.dto.store.PurchaseOrderDto;
import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.monitoring.TraceabilityEmitter;
import co.edu.udea.ayds2.services.email.EmailSenderService;
import co.edu.udea.ayds2.services.order.interfaces.PurchaseOrderService;
import co.edu.udea.ayds2.services.web.interfaces.WebRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseOrderServiceProxy implements PurchaseOrderService {

    private final PurchaseOrderService purchaseOrderService;
    private final TraceabilityEmitter traceabilityEmitter;
    private final AppServerResponse appServerResponse;
    private final EmailSenderService emailSenderService;
    private final WebRequest webRequest;

    public PurchaseOrderServiceProxy(PurchaseOrderService purchaseOrderService, TraceabilityEmitter traceabilityEmitter, EmailSenderService emailSenderService, WebRequest webRequest) {
        this.purchaseOrderService = purchaseOrderService;
        this.traceabilityEmitter = traceabilityEmitter;
        this.appServerResponse = new AppServerResponse();
        this.emailSenderService = emailSenderService;
        this.webRequest = webRequest;
    }

    @Override
    public PurchaseOrderDto postPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrderDto purchaseOrder = this.purchaseOrderService.postPurchaseOrder(purchaseOrderDto);
        boolean result = purchaseOrder != null;
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se ha creado una orden de compra");
        sendEmailToUsersForNewPurchaseOrder(purchaseOrderDto.getOrderId(), purchaseOrderDto.getSellerId(), purchaseOrderDto.getCustomerId());
        traceabilityEmitter.emitTraceability(appServerResponse);
        return purchaseOrder;
    }

    @Override
    public boolean approveOrderById(String orderId) throws IllegalArgumentException {
        PurchaseOrderDto purchaseOrderDto = this.purchaseOrderService.getPurchaseOrderById(orderId);
        boolean result = this.purchaseOrderService.approveOrderById(orderId);
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se ha aprobado una orden de compra");
        sendEmailToCustomerForPurchaseOrderApproved(orderId, purchaseOrderDto.getCustomerId());
        traceabilityEmitter.emitTraceability(appServerResponse);
        return result;
    }

    @Override
    public boolean declineOrderById(String orderId) {
        PurchaseOrderDto purchaseOrderDto = this.purchaseOrderService.getPurchaseOrderById(orderId);
        boolean result = this.purchaseOrderService.declineOrderById(orderId);
        getAppServerResponseOfCurrentProcess(result, "[Garage Sale Store Service] - Se ha rechazado una orden de compra");
        sendEmailToCustomerForPurchaseOrderDeclined(orderId, purchaseOrderDto.getCustomerId());
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

    @Override
    public PurchaseOrderDto getPurchaseOrderById(String orderId) {
        return null;
    }

    private void getAppServerResponseOfCurrentProcess(boolean result, String operationDescription) {
        appServerResponse.setCurrentDate(LocalDateTime.now());
        appServerResponse.setStatus(result ? EnumResponseStatus.OK : EnumResponseStatus.ERROR);
        appServerResponse.setDetailInfo(operationDescription);
    }

    private void sendEmailToUsersForNewPurchaseOrder(String orderId, String sellerId, String customerId ) {
        UserDto seller = this.webRequest.requestUserInformationById(sellerId);
        UserDto customer = this.webRequest.requestUserInformationById(customerId);
        this.emailSenderService.getMailSender().sendEmailForOrderCreated(orderId, seller, customer);
    }

    private void sendEmailToCustomerForPurchaseOrderApproved(String orderId, String customerId) {
        UserDto customer = this.webRequest.requestUserInformationById(customerId);
        this.emailSenderService.getMailSender().sendEmailForOrderApproved(orderId, customer);
    }

    private void sendEmailToCustomerForPurchaseOrderDeclined(String orderId, String customerId) {
        UserDto customer = this.webRequest.requestUserInformationById(customerId);
        this.emailSenderService.getMailSender().sendEmailForOrderDeclined(orderId, customer);
    }
}
