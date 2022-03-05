package co.edu.udea.ayds2.dto.helpers.response;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

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
    private  LocalDate currentDate;

}
