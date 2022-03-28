package co.edu.udea.ayds2.dto.helpers.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
/**
 * Class for requests traceability
* */
public class AppServerResponse {

    @Value("${microservice.name}")
    private String microserviceName;
    private final EnumResponseStatus status;
    @DateTimeFormat(style = "yyyy-MM-dd")
    private final LocalDate currentDate;
    private final String detailInfo;

}
