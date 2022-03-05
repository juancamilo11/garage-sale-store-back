package co.edu.udea.ayds2.collection.store;

import co.edu.udea.ayds2.collection.store.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchasedItem {

    private double price;
    private int quantity;
    private Product product;
    private double subtotal;

    @Builder(toBuilder = true)
    public PurchasedItem(double price, int quantity, Product product) {
        this.price = price;
        this.quantity = quantity;
        this.product = product;
        this.subtotal = this.price * this.quantity;
    }
}
