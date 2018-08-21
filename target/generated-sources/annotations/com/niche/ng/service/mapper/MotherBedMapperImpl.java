package com.niche.ng.service.mapper;

import com.niche.ng.domain.MotherBed;
import com.niche.ng.domain.Nursery;
import com.niche.ng.service.dto.MotherBedDTO;
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
public class MotherBedMapperImpl implements MotherBedMapper {

    @Autowired
    private NurseryMapper nurseryMapper;

    @Override
    public List<MotherBed> toEntity(List<MotherBedDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MotherBed> list = new ArrayList<MotherBed>( dtoList.size() );
        for ( MotherBedDTO motherBedDTO : dtoList ) {
            list.add( toEntity( motherBedDTO ) );
        }

        return list;
    }

    @Override
    public List<MotherBedDTO> toDto(List<MotherBed> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MotherBedDTO> list = new ArrayList<MotherBedDTO>( entityList.size() );
        for ( MotherBed motherBed : entityList ) {
            list.add( toDto( motherBed ) );
        }

        return list;
    }

    @Override
    public MotherBedDTO toDto(MotherBed motherBed) {
        if ( motherBed == null ) {
            return null;
        }

        MotherBedDTO motherBedDTO = new MotherBedDTO();

        String nurseryName = motherBedNurseryNurseryName( motherBed );
        if ( nurseryName != null ) {
            motherBedDTO.setNurseryNurseryName( nurseryName );
        }
        Long id = motherBedNurseryId( motherBed );
        if ( id != null ) {
            motherBedDTO.setNurseryId( id );
        }
        motherBedDTO.setId( motherBed.getId() );
        motherBedDTO.setValue( motherBed.getValue() );
        motherBedDTO.setStatus( motherBed.getStatus() );
        motherBedDTO.setCreatedBy( motherBed.getCreatedBy() );
        motherBedDTO.setModifiedBy( motherBed.getModifiedBy() );
        motherBedDTO.setCreatedAt( motherBed.getCreatedAt() );
        motherBedDTO.setUpdatedAt( motherBed.getUpdatedAt() );

        return motherBedDTO;
    }

    @Override
    public MotherBed toEntity(MotherBedDTO motherBedDTO) {
        if ( motherBedDTO == null ) {
            return null;
        }

        MotherBed motherBed = new MotherBed();

        motherBed.setNursery( nurseryMapper.fromId( motherBedDTO.getNurseryId() ) );
        motherBed.setId( motherBedDTO.getId() );
        motherBed.setValue( motherBedDTO.getValue() );
        motherBed.setStatus( motherBedDTO.getStatus() );
        motherBed.setCreatedBy( motherBedDTO.getCreatedBy() );
        motherBed.setModifiedBy( motherBedDTO.getModifiedBy() );
        motherBed.setCreatedAt( motherBedDTO.getCreatedAt() );
        motherBed.setUpdatedAt( motherBedDTO.getUpdatedAt() );

        return motherBed;
    }

    private String motherBedNurseryNurseryName(MotherBed motherBed) {
        if ( motherBed == null ) {
            return null;
        }
        Nursery nursery = motherBed.getNursery();
        if ( nursery == null ) {
            return null;
        }
        String nurseryName = nursery.getNurseryName();
        if ( nurseryName == null ) {
            return null;
        }
        return nurseryName;
    }

    private Long motherBedNurseryId(MotherBed motherBed) {
        if ( motherBed == null ) {
            return null;
        }
        Nursery nursery = motherBed.getNursery();
        if ( nursery == null ) {
            return null;
        }
        Long id = nursery.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
