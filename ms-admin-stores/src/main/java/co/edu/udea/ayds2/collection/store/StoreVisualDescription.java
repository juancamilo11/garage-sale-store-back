package co.edu.udea.ayds2.collection.store;

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
public class StoreVisualDescription {

    private String portraitUrl;
    private String[] prevImagesList;
    private String physicalStoreImageUrl;

}

