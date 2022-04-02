package co.edu.udea.ayds2.dto.user;

import co.edu.udea.ayds2.dto.helpers.enums.EnumColombianStates;
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
public class UserDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("photoUrl")
    private String photoUrl;
    @JsonProperty("occupation")
    private String occupation;
    @JsonProperty("cellphone")
    private String cellphone;
    @JsonProperty("email")
    private String email;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("colombianState")
    private EnumColombianStates colombianState;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("address")
    private String address;
    @JsonProperty("dateOfBirth")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @JsonProperty("registerDate")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate registerDate;
}
