package co.edu.udea.ayds2.collection.store.product;

import co.edu.udea.ayds2.dto.helpers.enums.EnumProductStatus;
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
public class Product {

    @Id
    @NotBlank
    private String id;
    private String productName;
    private String additionalDescription;
    private Double price;
    private Integer quantity;
    private EnumProductStatus productStatus;
    private List<String> productImageUrlList;
    private List<String> productTagList;

}
