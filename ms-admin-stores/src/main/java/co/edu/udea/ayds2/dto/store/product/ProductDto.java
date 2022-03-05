package co.edu.udea.ayds2.dto.store.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {
    @Id
    @NotBlank
    private String id;

    private String productName;

    private Integer quantity;

    private Double price;

    private EnumProductStatusDto productStatus;

    private String additionalDescription;

    private List<String> productImageUrlList;

    private List<String> productTagList;
}
