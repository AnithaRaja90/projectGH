/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockDetailsMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.NurseryStockDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NurseryStockDetails and its DTO NurseryStockDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {BatchMapper.class, NurseryStockMapper.class})
public interface NurseryStockDetailsMapper extends EntityMapper<NurseryStockDetailsDTO, NurseryStockDetails> {

    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "batch.batchName", target = "batchBatchName")
    @Mapping(source = "nurseryStock.id", target = "nurseryStockId")
    NurseryStockDetailsDTO toDto(NurseryStockDetails nurseryStockDetails);

    @Mapping(source = "batchId", target = "batch")
    @Mapping(source = "nurseryStockId", target = "nurseryStock")
    NurseryStockDetails toEntity(NurseryStockDetailsDTO nurseryStockDetailsDTO);

    default NurseryStockDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        NurseryStockDetails nurseryStockDetails = new NurseryStockDetails();
        nurseryStockDetails.setId(id);
        return nurseryStockDetails;
    }
}
