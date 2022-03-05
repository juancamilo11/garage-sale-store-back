package co.edu.udea.ayds2.collection.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreVisualDescription {
    @JsonProperty("portraitUrl")
    private String portraitUrl;
    @JsonProperty("prevImagesList")
    private String[] prevImagesList;
    @JsonProperty("physicalStoreImageUrl")
    private String physicalStoreImageUrl;
}
