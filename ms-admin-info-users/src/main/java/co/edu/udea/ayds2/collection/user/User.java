package co.edu.udea.ayds2.collection.user;

import co.edu.udea.ayds2.dto.helpers.enums.EnumColombianStates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {

    @Id
    private String id;
    private String name;
    private String photoUrl;
    private String occupation;
    private String cellphone;
    private String email;
    private String postalCode;
    private EnumColombianStates colombianState;
    private String phone;
    private String address;
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate registerDate;

}
