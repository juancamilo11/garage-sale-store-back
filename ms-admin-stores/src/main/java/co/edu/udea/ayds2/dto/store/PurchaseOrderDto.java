package co.edu.udea.ayds2.dto.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDto {

    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("storeId")
    private String storeId;
    @JsonProperty("productId")
    private String productId;
    @JsonProperty("sellerId")
    private String sellerId;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("customerId")
    private String customerId;
    @DateTimeFormat(style = "yyyy-MM-dd")
    @JsonProperty("dateCreated")
    private LocalDate dateCreated;

}
