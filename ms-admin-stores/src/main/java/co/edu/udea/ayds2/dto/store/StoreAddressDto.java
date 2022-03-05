package co.edu.udea.ayds2.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class StoreAddressDto {
    private String latitude;
    private String longitude;
}
