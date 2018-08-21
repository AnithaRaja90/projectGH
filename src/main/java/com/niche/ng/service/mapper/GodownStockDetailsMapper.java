/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockDetailsMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.GodownStockDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GodownStockDetails and its DTO GodownStockDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {GodownStockMapper.class})
public interface GodownStockDetailsMapper extends EntityMapper<GodownStockDetailsDTO, GodownStockDetails> {

    @Mapping(source = "godownStock.id", target = "godownStockId")
    GodownStockDetailsDTO toDto(GodownStockDetails godownStockDetails);

    @Mapping(source = "godownStockId", target = "godownStock")
    GodownStockDetails toEntity(GodownStockDetailsDTO godownStockDetailsDTO);

    default GodownStockDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        GodownStockDetails godownStockDetails = new GodownStockDetails();
        godownStockDetails.setId(id);
        return godownStockDetails;
    }
}
