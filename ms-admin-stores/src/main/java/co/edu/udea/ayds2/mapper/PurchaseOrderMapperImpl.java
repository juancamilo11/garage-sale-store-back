package co.edu.udea.ayds2.mapper;

import co.edu.udea.ayds2.collection.store.PurchaseOrder;
import co.edu.udea.ayds2.dto.store.PurchaseOrderDto;
import co.edu.udea.ayds2.mapper.interfaces.PurchaseOrderMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.Function;

@Component
public class PurchaseOrderMapperImpl implements PurchaseOrderMapper {

    @Override
    public Function<PurchaseOrder, PurchaseOrderDto> mapFromEntityToDto() {
        return purchaseOrder -> PurchaseOrderDto.builder()
                .orderId(purchaseOrder.getOrderId())
                .storeId(purchaseOrder.getStoreId())
                .productId(purchaseOrder.getProductId())
                .sellerId(purchaseOrder.getSellerId())
                .quantity(purchaseOrder.getQuantity())
                .customerId(purchaseOrder.getCustomerId())
                .dateCreated(purchaseOrder.getDateCreated())
                .build();
    }

    @Override
    public Function<PurchaseOrderDto, PurchaseOrder> mapFromDtoToEntity() {
        return purchaseOrderDto -> PurchaseOrder.builder()
                .orderId(purchaseOrderDto.getOrderId())
                .storeId(purchaseOrderDto.getStoreId())
                .productId(purchaseOrderDto.getProductId())
                .sellerId(purchaseOrderDto.getSellerId())
                .quantity(purchaseOrderDto.getQuantity())
                .customerId(purchaseOrderDto.getCustomerId())
                .dateCreated(purchaseOrderDto.getDateCreated())
                .build();
    }
}
