package co.edu.udea.ayds2.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserContactDto {

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonProperty("address")
    private String address;

    @JsonProperty("colombianState")
    private EnumColombianStatesDto colombianState;

}
