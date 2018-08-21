package com.niche.ng.service.mapper;

import com.niche.ng.domain.Sector;
import com.niche.ng.domain.Zonal;
import com.niche.ng.service.dto.SectorDTO;
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
public class SectorMapperImpl implements SectorMapper {

    @Autowired
    private ZonalMapper zonalMapper;

    @Override
    public List<Sector> toEntity(List<SectorDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Sector> list = new ArrayList<Sector>( dtoList.size() );
        for ( SectorDTO sectorDTO : dtoList ) {
            list.add( toEntity( sectorDTO ) );
        }

        return list;
    }

    @Override
    public SectorDTO toDto(Sector sector) {
        if ( sector == null ) {
            return null;
        }

        SectorDTO sectorDTO = new SectorDTO();

        String zoneName = sectorZonalZoneName( sector );
        if ( zoneName != null ) {
            sectorDTO.setZonalZoneName( zoneName );
        }
        Long id = sectorZonalId( sector );
        if ( id != null ) {
            sectorDTO.setZonalId( id );
        }
        sectorDTO.setId( sector.getId() );
        sectorDTO.setSectorName( sector.getSectorName() );
        sectorDTO.setSectorAddress( sector.getSectorAddress() );
        sectorDTO.setSectorIncharge( sector.getSectorIncharge() );
        sectorDTO.setStatus( sector.getStatus() );
        sectorDTO.setCreatedBy( sector.getCreatedBy() );
        sectorDTO.setModifiedBy( sector.getModifiedBy() );
        sectorDTO.setCreatedAt( sector.getCreatedAt() );
        sectorDTO.setUpdatedAt( sector.getUpdatedAt() );

        return sectorDTO;
    }

    @Override
    public List<SectorDTO> toDto(List<Sector> sector) {
        if ( sector == null ) {
            return null;
        }

        List<SectorDTO> list = new ArrayList<SectorDTO>( sector.size() );
        for ( Sector sector1 : sector ) {
            list.add( toDto( sector1 ) );
        }

        return list;
    }

    @Override
    public Sector toEntity(SectorDTO sectorDTO) {
        if ( sectorDTO == null ) {
            return null;
        }

        Sector sector = new Sector();

        sector.setZonal( zonalMapper.fromId( sectorDTO.getZonalId() ) );
        sector.setId( sectorDTO.getId() );
        sector.setSectorName( sectorDTO.getSectorName() );
        sector.setSectorAddress( sectorDTO.getSectorAddress() );
        sector.setSectorIncharge( sectorDTO.getSectorIncharge() );
        sector.setStatus( sectorDTO.getStatus() );
        sector.setCreatedBy( sectorDTO.getCreatedBy() );
        sector.setModifiedBy( sectorDTO.getModifiedBy() );
        sector.setCreatedAt( sectorDTO.getCreatedAt() );
        sector.setUpdatedAt( sectorDTO.getUpdatedAt() );

        return sector;
    }

    private String sectorZonalZoneName(Sector sector) {
        if ( sector == null ) {
            return null;
        }
        Zonal zonal = sector.getZonal();
        if ( zonal == null ) {
            return null;
        }
        String zoneName = zonal.getZoneName();
        if ( zoneName == null ) {
            return null;
        }
        return zoneName;
    }

    private Long sectorZonalId(Sector sector) {
        if ( sector == null ) {
            return null;
        }
        Zonal zonal = sector.getZonal();
        if ( zonal == null ) {
            return null;
        }
        Long id = zonal.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
