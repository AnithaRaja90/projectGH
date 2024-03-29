/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ShadeAreaRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.ShadeArea;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Spring Data  repository for the ShadeArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShadeAreaRepository extends JpaRepository<ShadeArea, Long> {
    List<ShadeArea> findByBatchId(Long batchId);
}
