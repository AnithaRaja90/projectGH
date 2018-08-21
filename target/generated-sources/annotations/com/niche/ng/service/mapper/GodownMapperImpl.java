package com.niche.ng.service.mapper;

import com.niche.ng.domain.Godown;
import com.niche.ng.service.dto.GodownDTO;
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
public class GodownMapperImpl implements GodownMapper {

    @Override
    public GodownDTO toDto(Godown entity) {
        if ( entity == null ) {
            return null;
        }

        GodownDTO godownDTO = new GodownDTO();

        godownDTO.setId( entity.getId() );
        godownDTO.setName( entity.getName() );
        godownDTO.setAddress( entity.getAddress() );
        godownDTO.setIncharge( entity.getIncharge() );
        godownDTO.setStatus( entity.getStatus() );
        godownDTO.setCreatedBy( entity.getCreatedBy() );
        godownDTO.setModifiedBy( entity.getModifiedBy() );
        godownDTO.setCreatedAt( entity.getCreatedAt() );
        godownDTO.setUpdatedAt( entity.getUpdatedAt() );

        return godownDTO;
    }

    @Override
    public List<Godown> toEntity(List<GodownDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Godown> list = new ArrayList<Godown>( dtoList.size() );
        for ( GodownDTO godownDTO : dtoList ) {
            list.add( toEntity( godownDTO ) );
        }

        return list;
    }

    @Override
    public List<GodownDTO> toDto(List<Godown> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<GodownDTO> list = new ArrayList<GodownDTO>( entityList.size() );
        for ( Godown godown : entityList ) {
            list.add( toDto( godown ) );
        }

        return list;
    }

    @Override
    public Godown toEntity(GodownDTO godownDTO) {
        if ( godownDTO == null ) {
            return null;
        }

        Godown godown = new Godown();

        godown.setId( godownDTO.getId() );
        godown.setName( godownDTO.getName() );
        godown.setAddress( godownDTO.getAddress() );
        godown.setIncharge( godownDTO.getIncharge() );
        godown.setStatus( godownDTO.getStatus() );
        godown.setCreatedBy( godownDTO.getCreatedBy() );
        godown.setModifiedBy( godownDTO.getModifiedBy() );
        godown.setCreatedAt( godownDTO.getCreatedAt() );
        godown.setUpdatedAt( godownDTO.getUpdatedAt() );

        return godown;
    }
}
