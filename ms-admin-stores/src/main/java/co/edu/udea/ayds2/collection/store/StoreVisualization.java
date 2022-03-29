package co.edu.udea.ayds2.collection.store;

import co.edu.udea.ayds2.collection.user.UserVisualization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document
public class StoreVisualization {

    @Id
    private String storeId;
    private List<UserVisualization> userVisualizationList;

}
