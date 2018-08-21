/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.SectorDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Sector and its DTO SectorDTO.
 */
@Mapper(componentModel = "spring", uses = {ZonalMapper.class})
public interface SectorMapper extends EntityMapper<SectorDTO, Sector> {

    @Mapping(source = "zonal.id", target = "zonalId")
    @Mapping(source = "zonal.zoneName", target = "zonalZoneName")
    SectorDTO toDto(Sector sector);

    List<SectorDTO> toDto(List<Sector> sector);

    @Mapping(target = "nurserys", ignore = true)
    @Mapping(source = "zonalId", target = "zonal")
    Sector toEntity(SectorDTO sectorDTO);

    default Sector fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sector sector = new Sector();
        sector.setId(id);
        return sector;
    }
}
