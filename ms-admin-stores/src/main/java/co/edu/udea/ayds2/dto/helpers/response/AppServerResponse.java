package co.edu.udea.ayds2.dto.helpers.response;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AppServerResponse {

    @Value("${microservice.name}")
    private String microserviceName;
    private EnumResponseStatus status;
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate currentDate;
    private String detailInfo;

}
