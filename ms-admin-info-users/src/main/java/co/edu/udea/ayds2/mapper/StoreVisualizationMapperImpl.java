package co.edu.udea.ayds2.mapper;

import co.edu.udea.ayds2.collection.store.StoreVisualization;
import co.edu.udea.ayds2.collection.user.UserVisualization;
import co.edu.udea.ayds2.dto.store.StoreVisualizationDto;
import co.edu.udea.ayds2.dto.user.UserVisualizationDto;
import co.edu.udea.ayds2.mapper.interfaces.StoreVisualizationMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StoreVisualizationMapperImpl implements StoreVisualizationMapper {
    @Override
    public Function<StoreVisualization, StoreVisualizationDto> mapFromEntityToDto() {
        return  storeVisualization ->
                StoreVisualizationDto.builder()
                        .storeId(storeVisualization.getStoreId())
                        .userVisualizationList(mapUserVisualizationList(storeVisualization.getUserVisualizationList()))
                .build();
    }

    private List<UserVisualizationDto> mapUserVisualizationList(List<UserVisualization> userVisualizationList) {
        return userVisualizationList.stream()
                .map(userVisualization -> UserVisualizationDto.builder()
                        .userId(userVisualization.getUserId())
                        .date(userVisualization.getDate())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Function<StoreVisualizationDto, StoreVisualization> mapFromDtoToEntity() {
        return  storeVisualizationDto ->
                StoreVisualization.builder()
                        .storeId(storeVisualizationDto.getStoreId())
                        .userVisualizationList(mapUserVisualizationDtoList(storeVisualizationDto.getUserVisualizationList()))
                        .build();
    }

    private List<UserVisualization> mapUserVisualizationDtoList(List<UserVisualizationDto> userVisualizationDtoList) {
        return userVisualizationDtoList.stream()
                .map(userVisualization -> UserVisualization.builder()
                        .userId(userVisualization.getUserId())
                        .date(userVisualization.getDate())
                        .build())
                .collect(Collectors.toList());
    }

}
