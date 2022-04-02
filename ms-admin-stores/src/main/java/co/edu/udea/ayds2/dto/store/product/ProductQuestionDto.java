package co.edu.udea.ayds2.dto.store.product;

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
public class ProductQuestionDto {

    @JsonProperty("questionId")
    private String id;

    @JsonProperty("questionDate")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate questionDate;

    @JsonProperty("answerDate")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate answerDate;

    @JsonProperty("question")
    private String question;

    @JsonProperty("response")
    private String response;

    @JsonProperty("customerId")
    private String customerId;
}
