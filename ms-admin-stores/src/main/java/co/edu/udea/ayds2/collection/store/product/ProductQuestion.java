package co.edu.udea.ayds2.collection.store.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductQuestion {

    private String id;
    private LocalDate questionDate;
    private LocalDate answerDate;
    private String question;
    private String response;
    private String customerId;

}
