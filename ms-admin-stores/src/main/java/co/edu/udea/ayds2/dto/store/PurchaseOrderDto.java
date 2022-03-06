package co.edu.udea.ayds2.dto.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class PurchaseOrderDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("date")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate date;

    @JsonProperty("purchasedItemList")
    private List<PurchasedItemDto> purchasedItemList;

    @JsonProperty("purchaseTestimonial")
    private PurchaseTestimonialDto purchaseTestimonial;

    @JsonProperty("totalPrice")
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
