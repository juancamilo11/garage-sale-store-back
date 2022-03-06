package co.edu.udea.ayds2.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @JsonProperty("uid")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("photoUrl")
    private String photoUrl;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("dateOfBirth")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonProperty("registerDate")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate registerDate;

    @JsonProperty("creationTime")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate creationTime;

    @JsonProperty("lastSignInTime")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate lastSignInTime;

    @JsonProperty("userContact")
    private UserContactDto userContact;
}
