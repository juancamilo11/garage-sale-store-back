package co.edu.udea.ayds2.collection.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PurchaseOrder {

    @Id
    private String orderId;
    private String storeId;
    private String productId;
    private String sellerId;
    private Integer quantity;
    private String customerId;
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate dateCreated;

}
