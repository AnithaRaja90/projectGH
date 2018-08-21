package com.niche.ng.service.mapper;

import com.niche.ng.domain.PickList;
import com.niche.ng.service.dto.PickListDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-08-21T18:43:30+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class PickListMapperImpl implements PickListMapper {

    @Override
    public PickListDTO toDto(PickList entity) {
        if ( entity == null ) {
            return null;
        }

        PickListDTO pickListDTO = new PickListDTO();

        pickListDTO.setId( entity.getId() );
        pickListDTO.setPickListName( entity.getPickListName() );
        pickListDTO.setStatus( entity.getStatus() );
        pickListDTO.setCreatedBy( entity.getCreatedBy() );
        pickListDTO.setModifiedBy( entity.getModifiedBy() );
        pickListDTO.setCreatedAt( entity.getCreatedAt() );
        pickListDTO.setUpdatedAt( entity.getUpdatedAt() );

        return pickListDTO;
    }

    @Override
    public List<PickList> toEntity(List<PickListDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PickList> list = new ArrayList<PickList>( dtoList.size() );
        for ( PickListDTO pickListDTO : dtoList ) {
            list.add( toEntity( pickListDTO ) );
        }

        return list;
    }

    @Override
    public List<PickListDTO> toDto(List<PickList> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PickListDTO> list = new ArrayList<PickListDTO>( entityList.size() );
        for ( PickList pickList : entityList ) {
            list.add( toDto( pickList ) );
        }

        return list;
    }

    @Override
    public PickList toEntity(PickListDTO pickListDTO) {
        if ( pickListDTO == null ) {
            return null;
        }

        PickList pickList = new PickList();

        pickList.setId( pickListDTO.getId() );
        pickList.setPickListName( pickListDTO.getPickListName() );
        pickList.setStatus( pickListDTO.getStatus() );
        pickList.setCreatedBy( pickListDTO.getCreatedBy() );
        pickList.setModifiedBy( pickListDTO.getModifiedBy() );
        pickList.setCreatedAt( pickListDTO.getCreatedAt() );
        pickList.setUpdatedAt( pickListDTO.getUpdatedAt() );

        return pickList;
    }
}
