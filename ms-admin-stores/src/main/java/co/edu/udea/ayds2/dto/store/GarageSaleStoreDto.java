package co.edu.udea.ayds2.dto.store;

import co.edu.udea.ayds2.dto.store.product.ProductDto;
import co.edu.udea.ayds2.dto.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class GarageSaleStoreDto {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("storeName")
    private String storeName;

    @JsonProperty("storeExistencePeriod")
    private StoreExistencePeriodDto storeExistencePeriod;

    @JsonProperty("storeDescription")
    private StoreDescriptionDto storeDescription;

    @JsonProperty("storeVisualDescription")
    private StoreVisualDescriptionDto storeVisualDescription;

    @JsonProperty("user")
    private User user;

    @JsonProperty("purchaseTestimonialList")
    private List<PurchaseTestimonialDto> purchaseTestimonialList;

    @JsonProperty("purchaseOrderList")
    private List<PurchaseOrderDto> purchaseOrderList;

    @JsonProperty("productLis")
    private List<ProductDto> productList;

}
