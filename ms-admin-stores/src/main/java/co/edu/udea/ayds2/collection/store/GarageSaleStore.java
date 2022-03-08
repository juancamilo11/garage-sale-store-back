package co.edu.udea.ayds2.collection.store;

import co.edu.udea.ayds2.collection.store.product.Product;
import co.edu.udea.ayds2.collection.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class GarageSaleStore {

    @Id
    private String id;
    private String storeName;
    private StoreExistencePeriod storeExistencePeriod;
    private StoreDescription storeDescription;
    private StoreVisualDescription storeVisualDescription;
    private User seller;
    private List<Product> productList;
    private List<PurchaseTestimonial> purchaseTestimonialList = new ArrayList<>();
    private List<PurchaseOrder> purchaseOrderList = new ArrayList<>();

}
