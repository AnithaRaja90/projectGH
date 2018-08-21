package com.niche.ng.service.mapper;

import com.niche.ng.domain.Batch;
import com.niche.ng.domain.ShadeArea;
import com.niche.ng.service.dto.ShadeAreaDTO;
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
public class ShadeAreaMapperImpl implements ShadeAreaMapper {

    @Autowired
    private BatchMapper batchMapper;

    @Override
    public List<ShadeArea> toEntity(List<ShadeAreaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ShadeArea> list = new ArrayList<ShadeArea>( dtoList.size() );
        for ( ShadeAreaDTO shadeAreaDTO : dtoList ) {
            list.add( toEntity( shadeAreaDTO ) );
        }

        return list;
    }

    @Override
    public ShadeAreaDTO toDto(ShadeArea shadeArea) {
        if ( shadeArea == null ) {
            return null;
        }

        ShadeAreaDTO shadeAreaDTO = new ShadeAreaDTO();

        Long id = shadeAreaBatchId( shadeArea );
        if ( id != null ) {
            shadeAreaDTO.setBatchId( id );
        }
        String batchName = shadeAreaBatchBatchName( shadeArea );
        if ( batchName != null ) {
            shadeAreaDTO.setBatchBatchName( batchName );
        }
        shadeAreaDTO.setId( shadeArea.getId() );
        shadeAreaDTO.setNoOfSeedlings( shadeArea.getNoOfSeedlings() );
        shadeAreaDTO.setDate( shadeArea.getDate() );
        shadeAreaDTO.setStatus( shadeArea.getStatus() );
        shadeAreaDTO.setCreatedBy( shadeArea.getCreatedBy() );
        shadeAreaDTO.setModifiedBy( shadeArea.getModifiedBy() );
        shadeAreaDTO.setCreatedAt( shadeArea.getCreatedAt() );
        shadeAreaDTO.setUpdatedAt( shadeArea.getUpdatedAt() );
        shadeAreaDTO.setDamage( shadeArea.getDamage() );
        shadeAreaDTO.setSaplings( shadeArea.getSaplings() );

        return shadeAreaDTO;
    }

    @Override
    public ShadeArea toEntity(ShadeAreaDTO shadeAreaDTO) {
        if ( shadeAreaDTO == null ) {
            return null;
        }

        ShadeArea shadeArea = new ShadeArea();

        shadeArea.setBatch( batchMapper.fromId( shadeAreaDTO.getBatchId() ) );
        shadeArea.setId( shadeAreaDTO.getId() );
        shadeArea.setNoOfSeedlings( shadeAreaDTO.getNoOfSeedlings() );
        shadeArea.setDate( shadeAreaDTO.getDate() );
        shadeArea.setStatus( shadeAreaDTO.getStatus() );
        shadeArea.setCreatedBy( shadeAreaDTO.getCreatedBy() );
        shadeArea.setModifiedBy( shadeAreaDTO.getModifiedBy() );
        shadeArea.setCreatedAt( shadeAreaDTO.getCreatedAt() );
        shadeArea.setUpdatedAt( shadeAreaDTO.getUpdatedAt() );
        shadeArea.setDamage( shadeAreaDTO.getDamage() );
        shadeArea.setSaplings( shadeAreaDTO.getSaplings() );

        return shadeArea;
    }

    @Override
    public List<ShadeAreaDTO> toDto(List<ShadeArea> shadeArea) {
        if ( shadeArea == null ) {
            return null;
        }

        List<ShadeAreaDTO> list = new ArrayList<ShadeAreaDTO>( shadeArea.size() );
        for ( ShadeArea shadeArea1 : shadeArea ) {
            list.add( toDto( shadeArea1 ) );
        }

        return list;
    }

    private Long shadeAreaBatchId(ShadeArea shadeArea) {
        if ( shadeArea == null ) {
            return null;
        }
        Batch batch = shadeArea.getBatch();
        if ( batch == null ) {
            return null;
        }
        Long id = batch.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String shadeAreaBatchBatchName(ShadeArea shadeArea) {
        if ( shadeArea == null ) {
            return null;
        }
        Batch batch = shadeArea.getBatch();
        if ( batch == null ) {
            return null;
        }
        String batchName = batch.getBatchName();
        if ( batchName == null ) {
            return null;
        }
        return batchName;
    }
}
