package co.edu.udea.ayds2.dto.store;

import co.edu.udea.ayds2.dto.store.product.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchasedItemDto {
    private double price;
    private int quantity;
    private ProductDto product;
    private double subtotal;

    @Builder(toBuilder = true)
    public PurchasedItemDto(double price, int quantity, ProductDto product) {
        this.price = price;
        this.quantity = quantity;
        this.product = product;
        this.subtotal = this.price * this.quantity;
    }
}
