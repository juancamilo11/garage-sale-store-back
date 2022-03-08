package co.edu.udea.ayds2.collection.store;

import co.edu.udea.ayds2.collection.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PurchaseTestimonial {

    private String title;
    private String purchaseTestimonial;
    private Integer grade;
    private User user;
    private LocalDate date;

}
