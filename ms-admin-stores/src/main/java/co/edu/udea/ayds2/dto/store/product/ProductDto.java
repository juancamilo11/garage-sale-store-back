package co.edu.udea.ayds2.dto.store.product;

import co.edu.udea.ayds2.dto.helpers.enums.EnumProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    @NotBlank
    @JsonProperty("id")
    private String id;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("productState")
    private EnumProductStatus productStatus;

    @JsonProperty("additionalDescription")
    private String additionalDescription;

    @JsonProperty("productUrlImages")
    private List<String> productImageUrlList;

    @JsonProperty("productTagList")
    private List<String> productTagList;
}
