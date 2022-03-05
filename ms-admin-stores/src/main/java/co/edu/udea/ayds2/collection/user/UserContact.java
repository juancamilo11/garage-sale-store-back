package co.edu.udea.ayds2.collection.user;

import co.edu.udea.ayds2.dto.helpers.enums.EnumColombianStates;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserContact {

    private String phoneNumber;
    private String email;
    private String postalCode;
    private String address;
    private EnumColombianStates colombianState;

}
