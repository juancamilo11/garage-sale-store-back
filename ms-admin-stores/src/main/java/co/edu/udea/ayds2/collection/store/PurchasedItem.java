package co.edu.udea.ayds2.collection.store;

import co.edu.udea.ayds2.collection.store.product.Product;
import lombok.*;

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
