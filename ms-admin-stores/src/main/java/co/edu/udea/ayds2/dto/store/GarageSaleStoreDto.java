package co.edu.udea.ayds2.dto.store;

import co.edu.udea.ayds2.dto.store.product.ProductCategoryDto;
import co.edu.udea.ayds2.dto.user.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
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
    private UserDto seller;
    
    @JsonProperty("storeAddress")
    private StoreAddressDto storeAddressDto;

    @JsonProperty("productCategoryList")
    @NotEmpty
    private List<ProductCategoryDto> productCategoryList;

    @JsonProperty("purchaseTestimonialList")
    private List<PurchaseTestimonialDto> purchaseTestimonialList;

    @JsonProperty("purchaseOrderList")
    private List<PurchaseOrderDto> purchaseOrderList;

}
