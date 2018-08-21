/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ZonalMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.ZonalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Zonal and its DTO ZonalDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ZonalMapper extends EntityMapper<ZonalDTO, Zonal> {


    @Mapping(target = "sectors", ignore = true)
    Zonal toEntity(ZonalDTO zonalDTO);

    default Zonal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Zonal zonal = new Zonal();
        zonal.setId(id);
        return zonal;
    }
}
