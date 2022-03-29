package co.edu.udea.ayds2.dto.store;

import co.edu.udea.ayds2.dto.user.UserVisualizationDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreVisualizationDto {

    private String storeId;
    private List<UserVisualizationDto> userVisualizationList;

}
