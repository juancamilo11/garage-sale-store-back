package co.edu.udea.ayds2.collection.store;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class PurchaseOrder {

    private String id;
    private LocalDate date;
    private List<PurchasedItem> purchasedItemList;
    private PurchaseTestimonial purchaseTestimonial;
    private double totalPrice;

    @Builder(toBuilder = true)
    public PurchaseOrder(String id, LocalDate date, List<PurchasedItem> purchasedItemList, PurchaseTestimonial purchaseTestimonial) {
        this.id = id;
        this.date = date;
        this.purchasedItemList = purchasedItemList;
        this.purchaseTestimonial = purchaseTestimonial;
        this.totalPrice = this.calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        return this.purchasedItemList.stream()
                .mapToDouble(PurchasedItem::getPrice)
                .sum();
    }
}
