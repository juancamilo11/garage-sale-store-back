package co.edu.udea.ayds2.dto.helpers.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AppServerResponse {

    @Value("${microservice.name}")
    private String microserviceName;
    private EnumResponseStatus status;
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDateTime currentDate;
    private String detailInfo;

}
