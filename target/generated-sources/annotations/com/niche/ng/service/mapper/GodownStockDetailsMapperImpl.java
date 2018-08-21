package com.niche.ng.service.mapper;

import com.niche.ng.domain.GodownStock;
import com.niche.ng.domain.GodownStockDetails;
import com.niche.ng.service.dto.GodownStockDetailsDTO;
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
public class GodownStockDetailsMapperImpl implements GodownStockDetailsMapper {

    @Autowired
    private GodownStockMapper godownStockMapper;

    @Override
    public List<GodownStockDetails> toEntity(List<GodownStockDetailsDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<GodownStockDetails> list = new ArrayList<GodownStockDetails>( dtoList.size() );
        for ( GodownStockDetailsDTO godownStockDetailsDTO : dtoList ) {
            list.add( toEntity( godownStockDetailsDTO ) );
        }

        return list;
    }

    @Override
    public List<GodownStockDetailsDTO> toDto(List<GodownStockDetails> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<GodownStockDetailsDTO> list = new ArrayList<GodownStockDetailsDTO>( entityList.size() );
        for ( GodownStockDetails godownStockDetails : entityList ) {
            list.add( toDto( godownStockDetails ) );
        }

        return list;
    }

    @Override
    public GodownStockDetailsDTO toDto(GodownStockDetails godownStockDetails) {
        if ( godownStockDetails == null ) {
            return null;
        }

        GodownStockDetailsDTO godownStockDetailsDTO = new GodownStockDetailsDTO();

        Long id = godownStockDetailsGodownStockId( godownStockDetails );
        if ( id != null ) {
            godownStockDetailsDTO.setGodownStockId( id );
        }
        godownStockDetailsDTO.setId( godownStockDetails.getId() );
        godownStockDetailsDTO.setDate( godownStockDetails.getDate() );
        godownStockDetailsDTO.setQuantity( godownStockDetails.getQuantity() );
        godownStockDetailsDTO.setDescription( godownStockDetails.getDescription() );
        godownStockDetailsDTO.setStatus( godownStockDetails.getStatus() );
        godownStockDetailsDTO.setCreatedBy( godownStockDetails.getCreatedBy() );
        godownStockDetailsDTO.setModifiedBy( godownStockDetails.getModifiedBy() );
        godownStockDetailsDTO.setCreatedAt( godownStockDetails.getCreatedAt() );
        godownStockDetailsDTO.setUpdatedAt( godownStockDetails.getUpdatedAt() );

        return godownStockDetailsDTO;
    }

    @Override
    public GodownStockDetails toEntity(GodownStockDetailsDTO godownStockDetailsDTO) {
        if ( godownStockDetailsDTO == null ) {
            return null;
        }

        GodownStockDetails godownStockDetails = new GodownStockDetails();

        godownStockDetails.setGodownStock( godownStockMapper.fromId( godownStockDetailsDTO.getGodownStockId() ) );
        godownStockDetails.setId( godownStockDetailsDTO.getId() );
        godownStockDetails.setDate( godownStockDetailsDTO.getDate() );
        godownStockDetails.setQuantity( godownStockDetailsDTO.getQuantity() );
        godownStockDetails.setDescription( godownStockDetailsDTO.getDescription() );
        godownStockDetails.setStatus( godownStockDetailsDTO.getStatus() );
        godownStockDetails.setCreatedBy( godownStockDetailsDTO.getCreatedBy() );
        godownStockDetails.setModifiedBy( godownStockDetailsDTO.getModifiedBy() );
        godownStockDetails.setCreatedAt( godownStockDetailsDTO.getCreatedAt() );
        godownStockDetails.setUpdatedAt( godownStockDetailsDTO.getUpdatedAt() );

        return godownStockDetails;
    }

    private Long godownStockDetailsGodownStockId(GodownStockDetails godownStockDetails) {
        if ( godownStockDetails == null ) {
            return null;
        }
        GodownStock godownStock = godownStockDetails.getGodownStock();
        if ( godownStock == null ) {
            return null;
        }
        Long id = godownStock.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
