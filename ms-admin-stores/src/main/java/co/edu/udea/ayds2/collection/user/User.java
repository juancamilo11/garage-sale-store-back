package co.edu.udea.ayds2.collection.user;

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
    private LocalDate dateOfBirth;
    private LocalDate creationTime;
    private LocalDate registerDate;
    private LocalDate lastSignInTime;
    private UserContact userContact;

}
