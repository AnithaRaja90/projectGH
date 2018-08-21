package com.niche.ng.service.mapper;

import com.niche.ng.domain.Batch;
import com.niche.ng.domain.NurseryStock;
import com.niche.ng.domain.NurseryStockDetails;
import com.niche.ng.service.dto.NurseryStockDetailsDTO;
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
public class NurseryStockDetailsMapperImpl implements NurseryStockDetailsMapper {

    @Autowired
    private BatchMapper batchMapper;
    @Autowired
    private NurseryStockMapper nurseryStockMapper;

    @Override
    public List<NurseryStockDetails> toEntity(List<NurseryStockDetailsDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<NurseryStockDetails> list = new ArrayList<NurseryStockDetails>( dtoList.size() );
        for ( NurseryStockDetailsDTO nurseryStockDetailsDTO : dtoList ) {
            list.add( toEntity( nurseryStockDetailsDTO ) );
        }

        return list;
    }

    @Override
    public List<NurseryStockDetailsDTO> toDto(List<NurseryStockDetails> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NurseryStockDetailsDTO> list = new ArrayList<NurseryStockDetailsDTO>( entityList.size() );
        for ( NurseryStockDetails nurseryStockDetails : entityList ) {
            list.add( toDto( nurseryStockDetails ) );
        }

        return list;
    }

    @Override
    public NurseryStockDetailsDTO toDto(NurseryStockDetails nurseryStockDetails) {
        if ( nurseryStockDetails == null ) {
            return null;
        }

        NurseryStockDetailsDTO nurseryStockDetailsDTO = new NurseryStockDetailsDTO();

        String batchName = nurseryStockDetailsBatchBatchName( nurseryStockDetails );
        if ( batchName != null ) {
            nurseryStockDetailsDTO.setBatchBatchName( batchName );
        }
        Long id = nurseryStockDetailsNurseryStockId( nurseryStockDetails );
        if ( id != null ) {
            nurseryStockDetailsDTO.setNurseryStockId( id );
        }
        Long id1 = nurseryStockDetailsBatchId( nurseryStockDetails );
        if ( id1 != null ) {
            nurseryStockDetailsDTO.setBatchId( id1 );
        }
        nurseryStockDetailsDTO.setId( nurseryStockDetails.getId() );
        nurseryStockDetailsDTO.setDate( nurseryStockDetails.getDate() );
        nurseryStockDetailsDTO.setQuantity( nurseryStockDetails.getQuantity() );
        nurseryStockDetailsDTO.setDescription( nurseryStockDetails.getDescription() );
        nurseryStockDetailsDTO.setStatus( nurseryStockDetails.getStatus() );
        nurseryStockDetailsDTO.setCreatedBy( nurseryStockDetails.getCreatedBy() );
        nurseryStockDetailsDTO.setModifiedBy( nurseryStockDetails.getModifiedBy() );
        nurseryStockDetailsDTO.setCreatedAt( nurseryStockDetails.getCreatedAt() );
        nurseryStockDetailsDTO.setUpdatedAt( nurseryStockDetails.getUpdatedAt() );

        return nurseryStockDetailsDTO;
    }

    @Override
    public NurseryStockDetails toEntity(NurseryStockDetailsDTO nurseryStockDetailsDTO) {
        if ( nurseryStockDetailsDTO == null ) {
            return null;
        }

        NurseryStockDetails nurseryStockDetails = new NurseryStockDetails();

        nurseryStockDetails.setBatch( batchMapper.fromId( nurseryStockDetailsDTO.getBatchId() ) );
        nurseryStockDetails.setNurseryStock( nurseryStockMapper.fromId( nurseryStockDetailsDTO.getNurseryStockId() ) );
        nurseryStockDetails.setId( nurseryStockDetailsDTO.getId() );
        nurseryStockDetails.setDate( nurseryStockDetailsDTO.getDate() );
        nurseryStockDetails.setQuantity( nurseryStockDetailsDTO.getQuantity() );
        nurseryStockDetails.setDescription( nurseryStockDetailsDTO.getDescription() );
        nurseryStockDetails.setStatus( nurseryStockDetailsDTO.getStatus() );
        nurseryStockDetails.setCreatedBy( nurseryStockDetailsDTO.getCreatedBy() );
        nurseryStockDetails.setModifiedBy( nurseryStockDetailsDTO.getModifiedBy() );
        nurseryStockDetails.setCreatedAt( nurseryStockDetailsDTO.getCreatedAt() );
        nurseryStockDetails.setUpdatedAt( nurseryStockDetailsDTO.getUpdatedAt() );

        return nurseryStockDetails;
    }

    private String nurseryStockDetailsBatchBatchName(NurseryStockDetails nurseryStockDetails) {
        if ( nurseryStockDetails == null ) {
            return null;
        }
        Batch batch = nurseryStockDetails.getBatch();
        if ( batch == null ) {
            return null;
        }
        String batchName = batch.getBatchName();
        if ( batchName == null ) {
            return null;
        }
        return batchName;
    }

    private Long nurseryStockDetailsNurseryStockId(NurseryStockDetails nurseryStockDetails) {
        if ( nurseryStockDetails == null ) {
            return null;
        }
        NurseryStock nurseryStock = nurseryStockDetails.getNurseryStock();
        if ( nurseryStock == null ) {
            return null;
        }
        Long id = nurseryStock.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long nurseryStockDetailsBatchId(NurseryStockDetails nurseryStockDetails) {
        if ( nurseryStockDetails == null ) {
            return null;
        }
        Batch batch = nurseryStockDetails.getBatch();
        if ( batch == null ) {
            return null;
        }
        Long id = batch.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
