package co.edu.udea.ayds2.dto.helpers.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
/**
 * Class for requests traceability
* */
public class AppServerResponse {

    @Value("${microservice.name}")
    private String microserviceName;
    private  EnumResponseStatus status;
    @DateTimeFormat(style = "yyyy-MM-dd")
    private  LocalDate currentDate;

}
