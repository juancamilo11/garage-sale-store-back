package co.edu.udea.ayds2.collection.store;

import co.edu.udea.ayds2.collection.store.product.Product;
import co.edu.udea.ayds2.collection.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class GarageSaleStore {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("storeName")
    private String storeName;

    @JsonProperty("storeExistencePeriod")
    private StoreExistencePeriod storeExistencePeriod;

    @JsonProperty("storeDescription")
    private StoreDescription storeDescription;

    @JsonProperty("storeVisualDescription")
    private StoreVisualDescription storeVisualDescription;

    @JsonProperty("user")
    private User user;

    @JsonProperty("purchaseTestimonialList")
    private List<PurchaseTestimonial> purchaseTestimonialList;

    @JsonProperty("purchaseOrderList")
    private List<PurchaseOrder> purchaseOrderList;

    @JsonProperty("productLis")
    private List<Product> productList;

}
