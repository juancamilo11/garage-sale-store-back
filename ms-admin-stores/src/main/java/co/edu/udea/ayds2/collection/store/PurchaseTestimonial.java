package co.edu.udea.ayds2.collection.store;

import co.edu.udea.ayds2.collection.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseTestimonial {
    @JsonProperty("title")
    private String title;
    @JsonProperty("purchaseTestimonial")
    private String purchaseTestimonial;
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("grade")
    private int grade;
    @JsonProperty("user")
    private User user;
}
