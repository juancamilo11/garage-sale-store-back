package co.edu.udea.ayds2.dto.store.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCategoryDto {

    @JsonProperty("categoryName")
    private String name;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("productList")
    private List<ProductDto> productList;

}
