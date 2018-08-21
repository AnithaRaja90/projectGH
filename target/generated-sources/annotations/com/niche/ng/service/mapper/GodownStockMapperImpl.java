package com.niche.ng.service.mapper;

import com.niche.ng.domain.Godown;
import com.niche.ng.domain.GodownStock;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.service.dto.GodownStockDTO;
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
public class GodownStockMapperImpl implements GodownStockMapper {

    @Autowired
    private PickListValueMapper pickListValueMapper;
    @Autowired
    private GodownMapper godownMapper;

    @Override
    public List<GodownStock> toEntity(List<GodownStockDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<GodownStock> list = new ArrayList<GodownStock>( dtoList.size() );
        for ( GodownStockDTO godownStockDTO : dtoList ) {
            list.add( toEntity( godownStockDTO ) );
        }

        return list;
    }

    @Override
    public List<GodownStockDTO> toDto(List<GodownStock> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<GodownStockDTO> list = new ArrayList<GodownStockDTO>( entityList.size() );
        for ( GodownStock godownStock : entityList ) {
            list.add( toDto( godownStock ) );
        }

        return list;
    }

    @Override
    public GodownStockDTO toDto(GodownStock godownStock) {
        if ( godownStock == null ) {
            return null;
        }

        GodownStockDTO godownStockDTO = new GodownStockDTO();

        Long id = godownStockGodownId( godownStock );
        if ( id != null ) {
            godownStockDTO.setGodownId( id );
        }
        String name = godownStockGodownName( godownStock );
        if ( name != null ) {
            godownStockDTO.setGodownName( name );
        }
        Long id1 = godownStockPickListCategoryId( godownStock );
        if ( id1 != null ) {
            godownStockDTO.setPickListCategoryId( id1 );
        }
        Long id2 = godownStockPickListVarietyId( godownStock );
        if ( id2 != null ) {
            godownStockDTO.setPickListVarietyId( id2 );
        }
        String pickListValue = godownStockPickListCategoryPickListValue( godownStock );
        if ( pickListValue != null ) {
            godownStockDTO.setPickListCategoryPickListValue( pickListValue );
        }
        String pickListValue1 = godownStockPickListVarietyPickListValue( godownStock );
        if ( pickListValue1 != null ) {
            godownStockDTO.setPickListVarietyPickListValue( pickListValue1 );
        }
        Long id3 = godownStockPickListQuantityTypeId( godownStock );
        if ( id3 != null ) {
            godownStockDTO.setPickListQuantityTypeId( id3 );
        }
        String pickListValue2 = godownStockPickListQuantityTypePickListValue( godownStock );
        if ( pickListValue2 != null ) {
            godownStockDTO.setPickListQuantityTypePickListValue( pickListValue2 );
        }
        godownStockDTO.setId( godownStock.getId() );
        godownStockDTO.setCurrentQuantity( godownStock.getCurrentQuantity() );
        godownStockDTO.setAddedQuantity( godownStock.getAddedQuantity() );
        godownStockDTO.setConsumedQuantity( godownStock.getConsumedQuantity() );
        godownStockDTO.setDescription( godownStock.getDescription() );
        godownStockDTO.setStatus( godownStock.getStatus() );
        godownStockDTO.setCreatedBy( godownStock.getCreatedBy() );
        godownStockDTO.setModifiedBy( godownStock.getModifiedBy() );
        godownStockDTO.setCreatedAt( godownStock.getCreatedAt() );
        godownStockDTO.setUpdatedAt( godownStock.getUpdatedAt() );

        return godownStockDTO;
    }

    @Override
    public GodownStock toEntity(GodownStockDTO godownStockDTO) {
        if ( godownStockDTO == null ) {
            return null;
        }

        GodownStock godownStock = new GodownStock();

        godownStock.setPickListCategory( pickListValueMapper.fromId( godownStockDTO.getPickListCategoryId() ) );
        godownStock.setPickListQuantityType( pickListValueMapper.fromId( godownStockDTO.getPickListQuantityTypeId() ) );
        godownStock.setPickListVariety( pickListValueMapper.fromId( godownStockDTO.getPickListVarietyId() ) );
        godownStock.setGodown( godownMapper.fromId( godownStockDTO.getGodownId() ) );
        godownStock.setId( godownStockDTO.getId() );
        godownStock.setCurrentQuantity( godownStockDTO.getCurrentQuantity() );
        godownStock.setAddedQuantity( godownStockDTO.getAddedQuantity() );
        godownStock.setConsumedQuantity( godownStockDTO.getConsumedQuantity() );
        godownStock.setDescription( godownStockDTO.getDescription() );
        godownStock.setStatus( godownStockDTO.getStatus() );
        godownStock.setCreatedBy( godownStockDTO.getCreatedBy() );
        godownStock.setModifiedBy( godownStockDTO.getModifiedBy() );
        godownStock.setCreatedAt( godownStockDTO.getCreatedAt() );
        godownStock.setUpdatedAt( godownStockDTO.getUpdatedAt() );

        return godownStock;
    }

    private Long godownStockGodownId(GodownStock godownStock) {
        if ( godownStock == null ) {
            return null;
        }
        Godown godown = godownStock.getGodown();
        if ( godown == null ) {
            return null;
        }
        Long id = godown.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String godownStockGodownName(GodownStock godownStock) {
        if ( godownStock == null ) {
            return null;
        }
        Godown godown = godownStock.getGodown();
        if ( godown == null ) {
            return null;
        }
        String name = godown.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long godownStockPickListCategoryId(GodownStock godownStock) {
        if ( godownStock == null ) {
            return null;
        }
        PickListValue pickListCategory = godownStock.getPickListCategory();
        if ( pickListCategory == null ) {
            return null;
        }
        Long id = pickListCategory.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long godownStockPickListVarietyId(GodownStock godownStock) {
        if ( godownStock == null ) {
            return null;
        }
        PickListValue pickListVariety = godownStock.getPickListVariety();
        if ( pickListVariety == null ) {
            return null;
        }
        Long id = pickListVariety.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String godownStockPickListCategoryPickListValue(GodownStock godownStock) {
        if ( godownStock == null ) {
            return null;
        }
        PickListValue pickListCategory = godownStock.getPickListCategory();
        if ( pickListCategory == null ) {
            return null;
        }
        String pickListValue = pickListCategory.getPickListValue();
        if ( pickListValue == null ) {
            return null;
        }
        return pickListValue;
    }

    private String godownStockPickListVarietyPickListValue(GodownStock godownStock) {
        if ( godownStock == null ) {
            return null;
        }
        PickListValue pickListVariety = godownStock.getPickListVariety();
        if ( pickListVariety == null ) {
            return null;
        }
        String pickListValue = pickListVariety.getPickListValue();
        if ( pickListValue == null ) {
            return null;
        }
        return pickListValue;
    }

    private Long godownStockPickListQuantityTypeId(GodownStock godownStock) {
        if ( godownStock == null ) {
            return null;
        }
        PickListValue pickListQuantityType = godownStock.getPickListQuantityType();
        if ( pickListQuantityType == null ) {
            return null;
        }
        Long id = pickListQuantityType.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String godownStockPickListQuantityTypePickListValue(GodownStock godownStock) {
        if ( godownStock == null ) {
            return null;
        }
        PickListValue pickListQuantityType = godownStock.getPickListQuantityType();
        if ( pickListQuantityType == null ) {
            return null;
        }
        String pickListValue = pickListQuantityType.getPickListValue();
        if ( pickListValue == null ) {
            return null;
        }
        return pickListValue;
    }
}
