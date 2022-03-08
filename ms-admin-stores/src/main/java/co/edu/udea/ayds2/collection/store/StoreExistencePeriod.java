package co.edu.udea.ayds2.collection.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class StoreExistencePeriod {

    private LocalDate startingDate;
    private LocalDate endingDate;

}
