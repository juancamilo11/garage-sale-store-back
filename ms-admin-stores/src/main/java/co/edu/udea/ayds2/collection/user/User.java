package co.edu.udea.ayds2.collection.user;

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
public class User {

    private String id;
    private String name;
    private String photoUrl;
    private String occupation;
    private String dateOfBirth;
    private LocalDate creationTime;
    private LocalDate registerDate;
    private LocalDate lastSignInTime;
    private UserContact userContact;

}
