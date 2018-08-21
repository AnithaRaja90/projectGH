/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.GodownStock;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GodownStock entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GodownStockRepository extends JpaRepository<GodownStock, Long> {

}
