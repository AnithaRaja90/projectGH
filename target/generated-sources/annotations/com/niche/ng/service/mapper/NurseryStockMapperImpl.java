package com.niche.ng.service.mapper;

import com.niche.ng.domain.Nursery;
import com.niche.ng.domain.NurseryStock;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.service.dto.NurseryStockDTO;
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
public class NurseryStockMapperImpl implements NurseryStockMapper {

    @Autowired
    private NurseryMapper nurseryMapper;
    @Autowired
    private PickListValueMapper pickListValueMapper;

    @Override
    public List<NurseryStock> toEntity(List<NurseryStockDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<NurseryStock> list = new ArrayList<NurseryStock>( dtoList.size() );
        for ( NurseryStockDTO nurseryStockDTO : dtoList ) {
            list.add( toEntity( nurseryStockDTO ) );
        }

        return list;
    }

    @Override
    public NurseryStockDTO toDto(NurseryStock nurseryStock) {
        if ( nurseryStock == null ) {
            return null;
        }

        NurseryStockDTO nurseryStockDTO = new NurseryStockDTO();

        Long id = nurseryStockPickListCategoryId( nurseryStock );
        if ( id != null ) {
            nurseryStockDTO.setPickListCategoryId( id );
        }
        Long id1 = nurseryStockPickListVarietyId( nurseryStock );
        if ( id1 != null ) {
            nurseryStockDTO.setPickListVarietyId( id1 );
        }
        Long id2 = nurseryStockNurseryId( nurseryStock );
        if ( id2 != null ) {
            nurseryStockDTO.setNurseryId( id2 );
        }
        String nurseryName = nurseryStockNurseryNurseryName( nurseryStock );
        if ( nurseryName != null ) {
            nurseryStockDTO.setNurseryNurseryName( nurseryName );
        }
        String pickListValue = nurseryStockPickListCategoryPickListValue( nurseryStock );
        if ( pickListValue != null ) {
            nurseryStockDTO.setPickListCategoryPickListValue( pickListValue );
        }
        String pickListValue1 = nurseryStockPickListVarietyPickListValue( nurseryStock );
        if ( pickListValue1 != null ) {
            nurseryStockDTO.setPickListVarietyPickListValue( pickListValue1 );
        }
        nurseryStockDTO.setId( nurseryStock.getId() );
        nurseryStockDTO.setCurrentQuantity( nurseryStock.getCurrentQuantity() );
        nurseryStockDTO.setAddedQuantity( nurseryStock.getAddedQuantity() );
        nurseryStockDTO.setConsumedQuantity( nurseryStock.getConsumedQuantity() );
        nurseryStockDTO.setDescription( nurseryStock.getDescription() );
        nurseryStockDTO.setStatus( nurseryStock.getStatus() );
        nurseryStockDTO.setCreatedBy( nurseryStock.getCreatedBy() );
        nurseryStockDTO.setModifiedBy( nurseryStock.getModifiedBy() );
        nurseryStockDTO.setCreatedAt( nurseryStock.getCreatedAt() );
        nurseryStockDTO.setUpdatedAt( nurseryStock.getUpdatedAt() );

        return nurseryStockDTO;
    }

    @Override
    public NurseryStock toEntity(NurseryStockDTO nurseryStockDTO) {
        if ( nurseryStockDTO == null ) {
            return null;
        }

        NurseryStock nurseryStock = new NurseryStock();

        nurseryStock.setPickListCategory( pickListValueMapper.fromId( nurseryStockDTO.getPickListCategoryId() ) );
        nurseryStock.setPickListVariety( pickListValueMapper.fromId( nurseryStockDTO.getPickListVarietyId() ) );
        nurseryStock.setNursery( nurseryMapper.fromId( nurseryStockDTO.getNurseryId() ) );
        nurseryStock.setId( nurseryStockDTO.getId() );
        nurseryStock.setCurrentQuantity( nurseryStockDTO.getCurrentQuantity() );
        nurseryStock.setAddedQuantity( nurseryStockDTO.getAddedQuantity() );
        nurseryStock.setConsumedQuantity( nurseryStockDTO.getConsumedQuantity() );
        nurseryStock.setDescription( nurseryStockDTO.getDescription() );
        nurseryStock.setStatus( nurseryStockDTO.getStatus() );
        nurseryStock.setCreatedBy( nurseryStockDTO.getCreatedBy() );
        nurseryStock.setModifiedBy( nurseryStockDTO.getModifiedBy() );
        nurseryStock.setCreatedAt( nurseryStockDTO.getCreatedAt() );
        nurseryStock.setUpdatedAt( nurseryStockDTO.getUpdatedAt() );

        return nurseryStock;
    }

    @Override
    public List<NurseryStockDTO> toDto(List<NurseryStock> nurseryStock) {
        if ( nurseryStock == null ) {
            return null;
        }

        List<NurseryStockDTO> list = new ArrayList<NurseryStockDTO>( nurseryStock.size() );
        for ( NurseryStock nurseryStock1 : nurseryStock ) {
            list.add( toDto( nurseryStock1 ) );
        }

        return list;
    }

    private Long nurseryStockPickListCategoryId(NurseryStock nurseryStock) {
        if ( nurseryStock == null ) {
            return null;
        }
        PickListValue pickListCategory = nurseryStock.getPickListCategory();
        if ( pickListCategory == null ) {
            return null;
        }
        Long id = pickListCategory.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long nurseryStockPickListVarietyId(NurseryStock nurseryStock) {
        if ( nurseryStock == null ) {
            return null;
        }
        PickListValue pickListVariety = nurseryStock.getPickListVariety();
        if ( pickListVariety == null ) {
            return null;
        }
        Long id = pickListVariety.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long nurseryStockNurseryId(NurseryStock nurseryStock) {
        if ( nurseryStock == null ) {
            return null;
        }
        Nursery nursery = nurseryStock.getNursery();
        if ( nursery == null ) {
            return null;
        }
        Long id = nursery.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String nurseryStockNurseryNurseryName(NurseryStock nurseryStock) {
        if ( nurseryStock == null ) {
            return null;
        }
        Nursery nursery = nurseryStock.getNursery();
        if ( nursery == null ) {
            return null;
        }
        String nurseryName = nursery.getNurseryName();
        if ( nurseryName == null ) {
            return null;
        }
        return nurseryName;
    }

    private String nurseryStockPickListCategoryPickListValue(NurseryStock nurseryStock) {
        if ( nurseryStock == null ) {
            return null;
        }
        PickListValue pickListCategory = nurseryStock.getPickListCategory();
        if ( pickListCategory == null ) {
            return null;
        }
        String pickListValue = pickListCategory.getPickListValue();
        if ( pickListValue == null ) {
            return null;
        }
        return pickListValue;
    }

    private String nurseryStockPickListVarietyPickListValue(NurseryStock nurseryStock) {
        if ( nurseryStock == null ) {
            return null;
        }
        PickListValue pickListVariety = nurseryStock.getPickListVariety();
        if ( pickListVariety == null ) {
            return null;
        }
        String pickListValue = pickListVariety.getPickListValue();
        if ( pickListValue == null ) {
            return null;
        }
        return pickListValue;
    }
}
