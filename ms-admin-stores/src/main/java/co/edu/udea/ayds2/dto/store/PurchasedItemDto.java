package co.edu.udea.ayds2.dto.store;

import co.edu.udea.ayds2.dto.store.product.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchasedItemDto {

    @JsonProperty("price")
    private double price;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("product")
    private ProductDto product;

    @JsonProperty("subtotal")
    private double subtotal;

    @Builder(toBuilder = true)
    public PurchasedItemDto(double price, int quantity, ProductDto product) {
        this.price = price;
        this.quantity = quantity;
        this.product = product;
        this.subtotal = this.price * this.quantity;
    }
}
