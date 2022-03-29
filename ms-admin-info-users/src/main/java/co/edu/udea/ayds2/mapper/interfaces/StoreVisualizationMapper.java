package co.edu.udea.ayds2.mapper.interfaces;

import co.edu.udea.ayds2.collection.store.StoreVisualization;
import co.edu.udea.ayds2.dto.store.StoreVisualizationDto;

import java.util.function.Function;

public interface StoreVisualizationMapper {
    Function<StoreVisualization, StoreVisualizationDto> mapFromEntityToDto();
    Function<StoreVisualizationDto, StoreVisualization> mapFromDtoToEntity();
}
