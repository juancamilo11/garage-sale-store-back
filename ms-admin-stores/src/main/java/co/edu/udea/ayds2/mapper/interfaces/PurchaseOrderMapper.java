package co.edu.udea.ayds2.mapper.interfaces;

import co.edu.udea.ayds2.collection.store.PurchaseOrder;
import co.edu.udea.ayds2.dto.store.PurchaseOrderDto;

import java.util.function.Function;

public interface PurchaseOrderMapper {
    Function<PurchaseOrder, PurchaseOrderDto> mapFromEntityToDto();
    Function<PurchaseOrderDto, PurchaseOrder> mapFromDtoToEntity();
}
