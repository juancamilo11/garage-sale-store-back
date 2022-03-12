package co.edu.udea.ayds2.collection.store.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductCategory {

    private String name;
    private String imageUrl;
    private List<Product> productList;

}
