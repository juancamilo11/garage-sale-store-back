package co.edu.udea.ayds2.services;

import co.edu.udea.ayds2.collection.store.GarageSaleStore;
import co.edu.udea.ayds2.collection.store.PurchaseOrder;
import co.edu.udea.ayds2.collection.store.product.Product;
import co.edu.udea.ayds2.collection.store.product.ProductCategory;
import co.edu.udea.ayds2.dto.store.PurchaseOrderDto;
import co.edu.udea.ayds2.mapper.PurchaseOrderMapperImpl;
import co.edu.udea.ayds2.mapper.interfaces.PurchaseOrderMapper;
import co.edu.udea.ayds2.repository.GarageSaleStoreRepository;
import co.edu.udea.ayds2.repository.PurchaseOrderRepository;
import co.edu.udea.ayds2.services.interfaces.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("realPurchaseOrderServiceImpl")
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final GarageSaleStoreRepository garageSaleStoreRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    public PurchaseOrderServiceImpl(GarageSaleStoreRepository garageSaleStoreRepository,
                                    PurchaseOrderRepository purchaseOrderRepository) {
        this.garageSaleStoreRepository = garageSaleStoreRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this. purchaseOrderMapper = PurchaseOrderMapperImpl.getInstance();
    }

    @Override
    public PurchaseOrderDto postPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        return this.purchaseOrderMapper.mapFromEntityToDto()
                .apply(this.purchaseOrderRepository
                        .save(this.purchaseOrderMapper.mapFromDtoToEntity()
                                .apply(purchaseOrderDto)));
    }

    @Override
    public List<PurchaseOrderDto> getPurchaseOrdersByTypeAndId(String type, String id) {
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        if(type.equalsIgnoreCase("BUY")) {
            purchaseOrderList = this.purchaseOrderRepository.findAllByCustomerId(id);
        }
        if(type.equalsIgnoreCase("SELL")) {
            purchaseOrderList = this.purchaseOrderRepository.findAllBySellerId(id);
        }
        return purchaseOrderList.stream()
                .map(purchaseOrder -> this.purchaseOrderMapper
                        .mapFromEntityToDto()
                        .apply(purchaseOrder))
                .collect(Collectors.toList());
    }

    private boolean deductProductQuantityFromStore(PurchaseOrder purchaseOrder) {
        Optional<GarageSaleStore> garageSaleStoreOptional = this.garageSaleStoreRepository.findById(purchaseOrder.getStoreId());
        return garageSaleStoreOptional.map(garageSaleStore -> {
            List<ProductCategory> productCategoryList = garageSaleStore.getProductCategoryList()
                    .stream()
                    .map(getProductCategoryToUpdate(purchaseOrder.getProductId(), purchaseOrder.getQuantity()))
                    .collect(Collectors.toList());

            garageSaleStore.setProductCategoryList(productCategoryList);
            this.garageSaleStoreRepository.save(garageSaleStore);
            return Boolean.TRUE;
        }).orElse(Boolean.FALSE);
    }
    @Override
    public boolean approveOrderById(String orderId) {
        //Obtener la orden de compra y enviarla a una cola de Rabbit
        Optional<PurchaseOrder> purchaseOrderOptional = this.purchaseOrderRepository.findById(orderId);
        return purchaseOrderOptional.map(purchaseOrder -> {
            boolean result = this.deductProductQuantityFromStore(purchaseOrder);
            if(result) {
                this.purchaseOrderRepository.delete(purchaseOrder);
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }).orElse(Boolean.FALSE);
    }

    @Override
    public boolean declineOrderById(String orderId) {
        Optional<PurchaseOrder> purchaseOrderOptional = this.purchaseOrderRepository.findById(orderId);
        return purchaseOrderOptional.map(purchaseOrder -> {
            this.purchaseOrderRepository.delete(purchaseOrder);
            return Boolean.TRUE;
        }).orElse(Boolean.FALSE);
    }

    private Function<ProductCategory, ProductCategory> getProductCategoryToUpdate(String productId, Integer quantity) {
        return productCategory -> {
            if (productCategory.getProductList().stream().anyMatch(product -> product.getId().equalsIgnoreCase(productId))) {
                return ProductCategory.builder()
                        .name(productCategory.getName())
                        .imageUrl(productCategory.getImageUrl())
                        .productList(productCategory.getProductList()
                                .stream()
                                .map(deductUnitsPurchasedFromProduct(productId, quantity))
                                .collect(Collectors.toList()))
                        .build();
            }
            return productCategory;
        };
    }

    private Function<Product, Product> deductUnitsPurchasedFromProduct(String productId, Integer quantity) {
        return product -> {
            if (product.getId().equalsIgnoreCase(productId)) {
                return Product.builder()
                        .id(product.getId())
                        .productName(product.getProductName())
                        .additionalDescription(product.getAdditionalDescription())
                        .price(product.getPrice())
                        .quantity(Math.abs(product.getQuantity() - quantity)) //Deduct the purchased quantity
                        .productStatus(product.getProductStatus())
                        .productImageUrlList(product.getProductImageUrlList())
                        .productTagList(product.getProductTagList())
                        .productQuestionList(product.getProductQuestionList())
                        .build();
            }
            return product;
        };
    }
}
