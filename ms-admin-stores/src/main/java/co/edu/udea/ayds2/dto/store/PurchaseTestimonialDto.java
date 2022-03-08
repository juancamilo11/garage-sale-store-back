package co.edu.udea.ayds2.dto.store;

import co.edu.udea.ayds2.dto.user.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseTestimonialDto {

    @JsonProperty("title")
    private String title;

    @JsonProperty("purchaseTestimonial")
    private String purchaseTestimonial;

    @JsonProperty("date")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate date;

    @JsonProperty("grade")
    private Integer grade;

    @JsonProperty("user")
    private UserDto user;
}
