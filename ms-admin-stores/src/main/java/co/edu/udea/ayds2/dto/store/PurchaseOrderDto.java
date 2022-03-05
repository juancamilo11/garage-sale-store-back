package co.edu.udea.ayds2.dto.store;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class PurchaseOrderDto {
    private String id;
    private LocalDate date;
    private List<PurchasedItemDto> purchasedItemList;
    private PurchaseTestimonialDto purchaseTestimonial;
    private double totalPrice;

    @Builder(toBuilder = true)
    public PurchaseOrderDto(String id, LocalDate date, List<PurchasedItemDto> purchasedItemList, PurchaseTestimonialDto purchaseTestimonial) {
        this.id = id;
        this.date = date;
        this.purchasedItemList = purchasedItemList;
        this.purchaseTestimonial = purchaseTestimonial;
        this.totalPrice = this.calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        return this.purchasedItemList.stream()
                .mapToDouble(PurchasedItemDto::getPrice)
                .sum();
    }
}
