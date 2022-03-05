package co.edu.udea.ayds2.collection.store.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductCategory {

    private String name;
    private String imageUrl;
    private List<Product> productList;

}
