package co.edu.udea.ayds2.collection.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class StoreDescription {

    private String slogan;
    private String description;
    private String[] tagsList;

}
