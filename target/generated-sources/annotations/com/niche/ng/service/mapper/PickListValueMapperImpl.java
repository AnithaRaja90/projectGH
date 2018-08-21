package com.niche.ng.service.mapper;

import com.niche.ng.domain.PickList;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.service.dto.PickListValueDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-08-21T18:43:30+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class PickListValueMapperImpl implements PickListValueMapper {

    @Autowired
    private PickListMapper pickListMapper;

    @Override
    public List<PickListValue> toEntity(List<PickListValueDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PickListValue> list = new ArrayList<PickListValue>( dtoList.size() );
        for ( PickListValueDTO pickListValueDTO : dtoList ) {
            list.add( toEntity( pickListValueDTO ) );
        }

        return list;
    }

    @Override
    public PickListValueDTO toDto(PickListValue pickListValue) {
        if ( pickListValue == null ) {
            return null;
        }

        PickListValueDTO pickListValueDTO = new PickListValueDTO();

        String pickListValue1 = pickListValuePickValuePickListValue( pickListValue );
        if ( pickListValue1 != null ) {
            pickListValueDTO.setPickValuePickListValue( pickListValue1 );
        }
        Long id = pickListValuePickListId( pickListValue );
        if ( id != null ) {
            pickListValueDTO.setPickListId( id );
        }
        Long id1 = pickListValuePickValueId( pickListValue );
        if ( id1 != null ) {
            pickListValueDTO.setPickValueId( id1 );
        }
        String pickListName = pickListValuePickListPickListName( pickListValue );
        if ( pickListName != null ) {
            pickListValueDTO.setPickListPickListName( pickListName );
        }
        pickListValueDTO.setId( pickListValue.getId() );
        pickListValueDTO.setPickListValue( pickListValue.getPickListValue() );
        pickListValueDTO.setStatus( pickListValue.getStatus() );
        pickListValueDTO.setCreatedBy( pickListValue.getCreatedBy() );
        pickListValueDTO.setModifiedBy( pickListValue.getModifiedBy() );
        pickListValueDTO.setCreatedAt( pickListValue.getCreatedAt() );
        pickListValueDTO.setUpdatedAt( pickListValue.getUpdatedAt() );

        return pickListValueDTO;
    }

    @Override
    public PickListValue toEntity(PickListValueDTO pickListValueDTO) {
        if ( pickListValueDTO == null ) {
            return null;
        }

        PickListValue pickListValue = new PickListValue();

        pickListValue.setPickValue( fromId( pickListValueDTO.getPickValueId() ) );
        pickListValue.setPickList( pickListMapper.fromId( pickListValueDTO.getPickListId() ) );
        pickListValue.setId( pickListValueDTO.getId() );
        pickListValue.setPickListValue( pickListValueDTO.getPickListValue() );
        pickListValue.setStatus( pickListValueDTO.getStatus() );
        pickListValue.setCreatedBy( pickListValueDTO.getCreatedBy() );
        pickListValue.setModifiedBy( pickListValueDTO.getModifiedBy() );
        pickListValue.setCreatedAt( pickListValueDTO.getCreatedAt() );
        pickListValue.setUpdatedAt( pickListValueDTO.getUpdatedAt() );

        return pickListValue;
    }

    @Override
    public List<PickListValueDTO> toDto(List<PickListValue> pickListValue) {
        if ( pickListValue == null ) {
            return null;
        }

        List<PickListValueDTO> list = new ArrayList<PickListValueDTO>( pickListValue.size() );
        for ( PickListValue pickListValue1 : pickListValue ) {
            list.add( toDto( pickListValue1 ) );
        }

        return list;
    }

    private String pickListValuePickValuePickListValue(PickListValue pickListValue) {
        if ( pickListValue == null ) {
            return null;
        }
        PickListValue pickValue = pickListValue.getPickValue();
        if ( pickValue == null ) {
            return null;
        }
        String pickListValue1 = pickValue.getPickListValue();
        if ( pickListValue1 == null ) {
            return null;
        }
        return pickListValue1;
    }

    private Long pickListValuePickListId(PickListValue pickListValue) {
        if ( pickListValue == null ) {
            return null;
        }
        PickList pickList = pickListValue.getPickList();
        if ( pickList == null ) {
            return null;
        }
        Long id = pickList.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long pickListValuePickValueId(PickListValue pickListValue) {
        if ( pickListValue == null ) {
            return null;
        }
        PickListValue pickValue = pickListValue.getPickValue();
        if ( pickValue == null ) {
            return null;
        }
        Long id = pickValue.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String pickListValuePickListPickListName(PickListValue pickListValue) {
        if ( pickListValue == null ) {
            return null;
        }
        PickList pickList = pickListValue.getPickList();
        if ( pickList == null ) {
            return null;
        }
        String pickListName = pickList.getPickListName();
        if ( pickListName == null ) {
            return null;
        }
        return pickListName;
    }
}
