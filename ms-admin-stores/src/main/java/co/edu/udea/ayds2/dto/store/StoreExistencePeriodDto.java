package co.edu.udea.ayds2.dto.store;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreExistencePeriodDto {

    @JsonProperty("startingDate")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate startingDate;

    @JsonProperty("endingDate")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate endingDate;

}
