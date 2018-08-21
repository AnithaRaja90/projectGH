package com.niche.ng.service.mapper;

import com.niche.ng.domain.Godown;
import com.niche.ng.domain.GodownPurchaseDetails;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.service.dto.GodownPurchaseDetailsDTO;
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
public class GodownPurchaseDetailsMapperImpl implements GodownPurchaseDetailsMapper {

    @Autowired
    private PickListValueMapper pickListValueMapper;
    @Autowired
    private GodownMapper godownMapper;

    @Override
    public List<GodownPurchaseDetails> toEntity(List<GodownPurchaseDetailsDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<GodownPurchaseDetails> list = new ArrayList<GodownPurchaseDetails>( dtoList.size() );
        for ( GodownPurchaseDetailsDTO godownPurchaseDetailsDTO : dtoList ) {
            list.add( toEntity( godownPurchaseDetailsDTO ) );
        }

        return list;
    }

    @Override
    public List<GodownPurchaseDetailsDTO> toDto(List<GodownPurchaseDetails> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<GodownPurchaseDetailsDTO> list = new ArrayList<GodownPurchaseDetailsDTO>( entityList.size() );
        for ( GodownPurchaseDetails godownPurchaseDetails : entityList ) {
            list.add( toDto( godownPurchaseDetails ) );
        }

        return list;
    }

    @Override
    public GodownPurchaseDetailsDTO toDto(GodownPurchaseDetails godownPurchaseDetails) {
        if ( godownPurchaseDetails == null ) {
            return null;
        }

        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = new GodownPurchaseDetailsDTO();

        Long id = godownPurchaseDetailsGodownId( godownPurchaseDetails );
        if ( id != null ) {
            godownPurchaseDetailsDTO.setGodownId( id );
        }
        String name = godownPurchaseDetailsGodownName( godownPurchaseDetails );
        if ( name != null ) {
            godownPurchaseDetailsDTO.setGodownName( name );
        }
        Long id1 = godownPurchaseDetailsPickListCategoryId( godownPurchaseDetails );
        if ( id1 != null ) {
            godownPurchaseDetailsDTO.setPickListCategoryId( id1 );
        }
        Long id2 = godownPurchaseDetailsPickListVarietyId( godownPurchaseDetails );
        if ( id2 != null ) {
            godownPurchaseDetailsDTO.setPickListVarietyId( id2 );
        }
        String pickListValue = godownPurchaseDetailsPickListCategoryPickListValue( godownPurchaseDetails );
        if ( pickListValue != null ) {
            godownPurchaseDetailsDTO.setPickListCategoryPickListValue( pickListValue );
        }
        String pickListValue1 = godownPurchaseDetailsPickListVarietyPickListValue( godownPurchaseDetails );
        if ( pickListValue1 != null ) {
            godownPurchaseDetailsDTO.setPickListVarietyPickListValue( pickListValue1 );
        }
        Long id3 = godownPurchaseDetailsPickListQuantityTypeId( godownPurchaseDetails );
        if ( id3 != null ) {
            godownPurchaseDetailsDTO.setPickListQuantityTypeId( id3 );
        }
        String pickListValue2 = godownPurchaseDetailsPickListQuantityTypePickListValue( godownPurchaseDetails );
        if ( pickListValue2 != null ) {
            godownPurchaseDetailsDTO.setPickListQuantityTypePickListValue( pickListValue2 );
        }
        godownPurchaseDetailsDTO.setId( godownPurchaseDetails.getId() );
        godownPurchaseDetailsDTO.setQuantity( godownPurchaseDetails.getQuantity() );
        godownPurchaseDetailsDTO.setDate( godownPurchaseDetails.getDate() );
        godownPurchaseDetailsDTO.setPrice( godownPurchaseDetails.getPrice() );
        godownPurchaseDetailsDTO.setOwnedBy( godownPurchaseDetails.getOwnedBy() );
        godownPurchaseDetailsDTO.setVendorName( godownPurchaseDetails.getVendorName() );
        godownPurchaseDetailsDTO.setVendorAddress( godownPurchaseDetails.getVendorAddress() );
        godownPurchaseDetailsDTO.setVendorPhone( godownPurchaseDetails.getVendorPhone() );
        godownPurchaseDetailsDTO.setDescription( godownPurchaseDetails.getDescription() );
        godownPurchaseDetailsDTO.setStatus( godownPurchaseDetails.getStatus() );
        godownPurchaseDetailsDTO.setCreatedBy( godownPurchaseDetails.getCreatedBy() );
        godownPurchaseDetailsDTO.setModifiedBy( godownPurchaseDetails.getModifiedBy() );
        godownPurchaseDetailsDTO.setCreatedAt( godownPurchaseDetails.getCreatedAt() );
        godownPurchaseDetailsDTO.setUpdatedAt( godownPurchaseDetails.getUpdatedAt() );

        return godownPurchaseDetailsDTO;
    }

    @Override
    public GodownPurchaseDetails toEntity(GodownPurchaseDetailsDTO godownPurchaseDetailsDTO) {
        if ( godownPurchaseDetailsDTO == null ) {
            return null;
        }

        GodownPurchaseDetails godownPurchaseDetails = new GodownPurchaseDetails();

        godownPurchaseDetails.setPickListCategory( pickListValueMapper.fromId( godownPurchaseDetailsDTO.getPickListCategoryId() ) );
        godownPurchaseDetails.setPickListQuantityType( pickListValueMapper.fromId( godownPurchaseDetailsDTO.getPickListQuantityTypeId() ) );
        godownPurchaseDetails.setPickListVariety( pickListValueMapper.fromId( godownPurchaseDetailsDTO.getPickListVarietyId() ) );
        godownPurchaseDetails.setGodown( godownMapper.fromId( godownPurchaseDetailsDTO.getGodownId() ) );
        godownPurchaseDetails.setId( godownPurchaseDetailsDTO.getId() );
        godownPurchaseDetails.setQuantity( godownPurchaseDetailsDTO.getQuantity() );
        godownPurchaseDetails.setDate( godownPurchaseDetailsDTO.getDate() );
        godownPurchaseDetails.setPrice( godownPurchaseDetailsDTO.getPrice() );
        godownPurchaseDetails.setOwnedBy( godownPurchaseDetailsDTO.getOwnedBy() );
        godownPurchaseDetails.setVendorName( godownPurchaseDetailsDTO.getVendorName() );
        godownPurchaseDetails.setVendorAddress( godownPurchaseDetailsDTO.getVendorAddress() );
        godownPurchaseDetails.setVendorPhone( godownPurchaseDetailsDTO.getVendorPhone() );
        godownPurchaseDetails.setDescription( godownPurchaseDetailsDTO.getDescription() );
        godownPurchaseDetails.setStatus( godownPurchaseDetailsDTO.getStatus() );
        godownPurchaseDetails.setCreatedBy( godownPurchaseDetailsDTO.getCreatedBy() );
        godownPurchaseDetails.setModifiedBy( godownPurchaseDetailsDTO.getModifiedBy() );
        godownPurchaseDetails.setCreatedAt( godownPurchaseDetailsDTO.getCreatedAt() );
        godownPurchaseDetails.setUpdatedAt( godownPurchaseDetailsDTO.getUpdatedAt() );

        return godownPurchaseDetails;
    }

    private Long godownPurchaseDetailsGodownId(GodownPurchaseDetails godownPurchaseDetails) {
        if ( godownPurchaseDetails == null ) {
            return null;
        }
        Godown godown = godownPurchaseDetails.getGodown();
        if ( godown == null ) {
            return null;
        }
        Long id = godown.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String godownPurchaseDetailsGodownName(GodownPurchaseDetails godownPurchaseDetails) {
        if ( godownPurchaseDetails == null ) {
            return null;
        }
        Godown godown = godownPurchaseDetails.getGodown();
        if ( godown == null ) {
            return null;
        }
        String name = godown.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long godownPurchaseDetailsPickListCategoryId(GodownPurchaseDetails godownPurchaseDetails) {
        if ( godownPurchaseDetails == null ) {
            return null;
        }
        PickListValue pickListCategory = godownPurchaseDetails.getPickListCategory();
        if ( pickListCategory == null ) {
            return null;
        }
        Long id = pickListCategory.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long godownPurchaseDetailsPickListVarietyId(GodownPurchaseDetails godownPurchaseDetails) {
        if ( godownPurchaseDetails == null ) {
            return null;
        }
        PickListValue pickListVariety = godownPurchaseDetails.getPickListVariety();
        if ( pickListVariety == null ) {
            return null;
        }
        Long id = pickListVariety.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String godownPurchaseDetailsPickListCategoryPickListValue(GodownPurchaseDetails godownPurchaseDetails) {
        if ( godownPurchaseDetails == null ) {
            return null;
        }
        PickListValue pickListCategory = godownPurchaseDetails.getPickListCategory();
        if ( pickListCategory == null ) {
            return null;
        }
        String pickListValue = pickListCategory.getPickListValue();
        if ( pickListValue == null ) {
            return null;
        }
        return pickListValue;
    }

    private String godownPurchaseDetailsPickListVarietyPickListValue(GodownPurchaseDetails godownPurchaseDetails) {
        if ( godownPurchaseDetails == null ) {
            return null;
        }
        PickListValue pickListVariety = godownPurchaseDetails.getPickListVariety();
        if ( pickListVariety == null ) {
            return null;
        }
        String pickListValue = pickListVariety.getPickListValue();
        if ( pickListValue == null ) {
            return null;
        }
        return pickListValue;
    }

    private Long godownPurchaseDetailsPickListQuantityTypeId(GodownPurchaseDetails godownPurchaseDetails) {
        if ( godownPurchaseDetails == null ) {
            return null;
        }
        PickListValue pickListQuantityType = godownPurchaseDetails.getPickListQuantityType();
        if ( pickListQuantityType == null ) {
            return null;
        }
        Long id = pickListQuantityType.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String godownPurchaseDetailsPickListQuantityTypePickListValue(GodownPurchaseDetails godownPurchaseDetails) {
        if ( godownPurchaseDetails == null ) {
            return null;
        }
        PickListValue pickListQuantityType = godownPurchaseDetails.getPickListQuantityType();
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
