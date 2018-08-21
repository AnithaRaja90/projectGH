package com.niche.ng.service.mapper;

import com.niche.ng.domain.Zonal;
import com.niche.ng.service.dto.ZonalDTO;
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
public class ZonalMapperImpl implements ZonalMapper {

    @Override
    public ZonalDTO toDto(Zonal entity) {
        if ( entity == null ) {
            return null;
        }

        ZonalDTO zonalDTO = new ZonalDTO();

        zonalDTO.setId( entity.getId() );
        zonalDTO.setZoneName( entity.getZoneName() );
        zonalDTO.setZoneAddress( entity.getZoneAddress() );
        zonalDTO.setZoneIncharge( entity.getZoneIncharge() );
        zonalDTO.setStatus( entity.getStatus() );
        zonalDTO.setCreatedBy( entity.getCreatedBy() );
        zonalDTO.setModifiedBy( entity.getModifiedBy() );
        zonalDTO.setCreatedAt( entity.getCreatedAt() );
        zonalDTO.setUpdatedAt( entity.getUpdatedAt() );

        return zonalDTO;
    }

    @Override
    public List<Zonal> toEntity(List<ZonalDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Zonal> list = new ArrayList<Zonal>( dtoList.size() );
        for ( ZonalDTO zonalDTO : dtoList ) {
            list.add( toEntity( zonalDTO ) );
        }

        return list;
    }

    @Override
    public List<ZonalDTO> toDto(List<Zonal> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ZonalDTO> list = new ArrayList<ZonalDTO>( entityList.size() );
        for ( Zonal zonal : entityList ) {
            list.add( toDto( zonal ) );
        }

        return list;
    }

    @Override
    public Zonal toEntity(ZonalDTO zonalDTO) {
        if ( zonalDTO == null ) {
            return null;
        }

        Zonal zonal = new Zonal();

        zonal.setId( zonalDTO.getId() );
        zonal.setZoneName( zonalDTO.getZoneName() );
        zonal.setZoneAddress( zonalDTO.getZoneAddress() );
        zonal.setZoneIncharge( zonalDTO.getZoneIncharge() );
        zonal.setStatus( zonalDTO.getStatus() );
        zonal.setCreatedBy( zonalDTO.getCreatedBy() );
        zonal.setModifiedBy( zonalDTO.getModifiedBy() );
        zonal.setCreatedAt( zonalDTO.getCreatedAt() );
        zonal.setUpdatedAt( zonalDTO.getUpdatedAt() );

        return zonal;
    }
}
