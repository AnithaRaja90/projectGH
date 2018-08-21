package com.niche.ng.service.mapper;

import com.niche.ng.domain.Batch;
import com.niche.ng.domain.Nursery;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.service.dto.BatchDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-08-21T18:43:31+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class BatchMapperImpl implements BatchMapper {

    @Autowired
    private NurseryMapper nurseryMapper;
    @Autowired
    private PickListValueMapper pickListValueMapper;

    @Override
    public List<Batch> toEntity(List<BatchDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Batch> list = new ArrayList<Batch>( dtoList.size() );
        for ( BatchDTO batchDTO : dtoList ) {
            list.add( toEntity( batchDTO ) );
        }

        return list;
    }

    @Override
    public List<BatchDTO> toDto(List<Batch> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BatchDTO> list = new ArrayList<BatchDTO>( entityList.size() );
        for ( Batch batch : entityList ) {
            list.add( toDto( batch ) );
        }

        return list;
    }

    @Override
    public BatchDTO toDto(Batch batch) {
        if ( batch == null ) {
            return null;
        }

        BatchDTO batchDTO = new BatchDTO();

        Long id = batchPickListCategoryId( batch );
        if ( id != null ) {
            batchDTO.setPickListCategoryId( id );
        }
        Long id1 = batchQuantityTypeId( batch );
        if ( id1 != null ) {
            batchDTO.setQuantityTypeId( id1 );
        }
        Long id2 = batchPickListVarietyId( batch );
        if ( id2 != null ) {
            batchDTO.setPickListVarietyId( id2 );
        }
        Long id3 = batchNurseryId( batch );
        if ( id3 != null ) {
            batchDTO.setNurseryId( id3 );
        }
        String nurseryName = batchNurseryNurseryName( batch );
        if ( nurseryName != null ) {
            batchDTO.setNurseryNurseryName( nurseryName );
        }
        String pickListValue = batchPickListCategoryPickListValue( batch );
        if ( pickListValue != null ) {
            batchDTO.setPickListCategoryPickListValue( pickListValue );
        }
        String pickListValue1 = batchPickListVarietyPickListValue( batch );
        if ( pickListValue1 != null ) {
            batchDTO.setPickListVarietyPickListValue( pickListValue1 );
        }
        String pickListValue2 = batchQuantityTypePickListValue( batch );
        if ( pickListValue2 != null ) {
            batchDTO.setQuantityTypePickListValue( pickListValue2 );
        }
        batchDTO.setId( batch.getId() );
        batchDTO.setBatchNo( batch.getBatchNo() );
        batchDTO.setBatchName( batch.getBatchName() );
        batchDTO.setQuantity( batch.getQuantity() );
        batchDTO.setMotherBed( batch.getMotherBed() );
        batchDTO.setShowingType( batch.getShowingType() );
        batchDTO.setSowingDate( batch.getSowingDate() );
        batchDTO.setClosedDate( batch.getClosedDate() );
        batchDTO.setRound( batch.getRound() );
        batchDTO.setRemarks( batch.getRemarks() );
        batchDTO.setStatus( batch.getStatus() );
        batchDTO.setCreatedBy( batch.getCreatedBy() );
        batchDTO.setModifiedBy( batch.getModifiedBy() );
        batchDTO.setCreatedAt( batch.getCreatedAt() );
        batchDTO.setUpdatedAt( batch.getUpdatedAt() );

        return batchDTO;
    }

    @Override
    public Batch toEntity(BatchDTO batchDTO) {
        if ( batchDTO == null ) {
            return null;
        }

        Batch batch = new Batch();

        batch.setQuantityType( pickListValueMapper.fromId( batchDTO.getQuantityTypeId() ) );
        batch.setPickListCategory( pickListValueMapper.fromId( batchDTO.getPickListCategoryId() ) );
        batch.setNursery( nurseryMapper.fromId( batchDTO.getNurseryId() ) );
        batch.setPickListVariety( pickListValueMapper.fromId( batchDTO.getPickListVarietyId() ) );
        batch.setId( batchDTO.getId() );
        batch.setBatchNo( batchDTO.getBatchNo() );
        batch.setBatchName( batchDTO.getBatchName() );
        batch.setQuantity( batchDTO.getQuantity() );
        batch.setMotherBed( batchDTO.getMotherBed() );
        batch.setShowingType( batchDTO.getShowingType() );
        batch.setSowingDate( batchDTO.getSowingDate() );
        batch.setClosedDate( batchDTO.getClosedDate() );
        batch.setRound( batchDTO.getRound() );
        batch.setRemarks( batchDTO.getRemarks() );
        batch.setStatus( batchDTO.getStatus() );
        batch.setCreatedBy( batchDTO.getCreatedBy() );
        batch.setModifiedBy( batchDTO.getModifiedBy() );
        batch.setCreatedAt( batchDTO.getCreatedAt() );
        batch.setUpdatedAt( batchDTO.getUpdatedAt() );

        return batch;
    }

    private Long batchPickListCategoryId(Batch batch) {
        if ( batch == null ) {
            return null;
        }
        PickListValue pickListCategory = batch.getPickListCategory();
        if ( pickListCategory == null ) {
            return null;
        }
        Long id = pickListCategory.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long batchQuantityTypeId(Batch batch) {
        if ( batch == null ) {
            return null;
        }
        PickListValue quantityType = batch.getQuantityType();
        if ( quantityType == null ) {
            return null;
        }
        Long id = quantityType.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long batchPickListVarietyId(Batch batch) {
        if ( batch == null ) {
            return null;
        }
        PickListValue pickListVariety = batch.getPickListVariety();
        if ( pickListVariety == null ) {
            return null;
        }
        Long id = pickListVariety.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long batchNurseryId(Batch batch) {
        if ( batch == null ) {
            return null;
        }
        Nursery nursery = batch.getNursery();
        if ( nursery == null ) {
            return null;
        }
        Long id = nursery.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String batchNurseryNurseryName(Batch batch) {
        if ( batch == null ) {
            return null;
        }
        Nursery nursery = batch.getNursery();
        if ( nursery == null ) {
            return null;
        }
        String nurseryName = nursery.getNurseryName();
        if ( nurseryName == null ) {
            return null;
        }
        return nurseryName;
    }

    private String batchPickListCategoryPickListValue(Batch batch) {
        if ( batch == null ) {
            return null;
        }
        PickListValue pickListCategory = batch.getPickListCategory();
        if ( pickListCategory == null ) {
            return null;
        }
        String pickListValue = pickListCategory.getPickListValue();
        if ( pickListValue == null ) {
            return null;
        }
        return pickListValue;
    }

    private String batchPickListVarietyPickListValue(Batch batch) {
        if ( batch == null ) {
            return null;
        }
        PickListValue pickListVariety = batch.getPickListVariety();
        if ( pickListVariety == null ) {
            return null;
        }
        String pickListValue = pickListVariety.getPickListValue();
        if ( pickListValue == null ) {
            return null;
        }
        return pickListValue;
    }

    private String batchQuantityTypePickListValue(Batch batch) {
        if ( batch == null ) {
            return null;
        }
        PickListValue quantityType = batch.getQuantityType();
        if ( quantityType == null ) {
            return null;
        }
        String pickListValue = quantityType.getPickListValue();
        if ( pickListValue == null ) {
            return null;
        }
        return pickListValue;
    }
}
