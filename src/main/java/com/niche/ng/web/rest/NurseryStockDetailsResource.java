/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockDetailsResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.NurseryStockDetailsService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.NurseryStockDetailsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NurseryStockDetails.
 */
@RestController
@RequestMapping("/api")
public class NurseryStockDetailsResource {

    private final Logger log = LoggerFactory.getLogger(NurseryStockDetailsResource.class);

    private static final String ENTITY_NAME = "nurseryStockDetails";

    private final NurseryStockDetailsService nurseryStockDetailsService;

    public NurseryStockDetailsResource(NurseryStockDetailsService nurseryStockDetailsService) {
        this.nurseryStockDetailsService = nurseryStockDetailsService;
    }

    /**
     * POST  /nursery-stock-details : Create a new nurseryStockDetails.
     *
     * @param nurseryStockDetailsDTO the nurseryStockDetailsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nurseryStockDetailsDTO, or with status 400 (Bad Request) if the nurseryStockDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nursery-stock-details")
    @Timed
    public ResponseEntity<NurseryStockDetailsDTO> createNurseryStockDetails(@Valid @RequestBody NurseryStockDetailsDTO nurseryStockDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save NurseryStockDetails : {}", nurseryStockDetailsDTO);
        if (nurseryStockDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new nurseryStockDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NurseryStockDetailsDTO result = nurseryStockDetailsService.save(nurseryStockDetailsDTO);
        return ResponseEntity.created(new URI("/api/nursery-stock-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nursery-stock-details : Updates an existing nurseryStockDetails.
     *
     * @param nurseryStockDetailsDTO the nurseryStockDetailsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nurseryStockDetailsDTO,
     * or with status 400 (Bad Request) if the nurseryStockDetailsDTO is not valid,
     * or with status 500 (Internal Server Error) if the nurseryStockDetailsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nursery-stock-details")
    @Timed
    public ResponseEntity<NurseryStockDetailsDTO> updateNurseryStockDetails(@Valid @RequestBody NurseryStockDetailsDTO nurseryStockDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update NurseryStockDetails : {}", nurseryStockDetailsDTO);
        if (nurseryStockDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NurseryStockDetailsDTO result = nurseryStockDetailsService.save(nurseryStockDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nurseryStockDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nursery-stock-details : get all the nurseryStockDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of nurseryStockDetails in body
     */
    @GetMapping("/nursery-stock-details")
    @Timed
    public ResponseEntity<List<NurseryStockDetailsDTO>> getAllNurseryStockDetails(Pageable pageable) {
        log.debug("REST request to get a page of NurseryStockDetails");
        Page<NurseryStockDetailsDTO> page = nurseryStockDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nursery-stock-details");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /nursery-stock-details/:id : get the "id" nurseryStockDetails.
     *
     * @param id the id of the nurseryStockDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nurseryStockDetailsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nursery-stock-details/{id}")
    @Timed
    public ResponseEntity<NurseryStockDetailsDTO> getNurseryStockDetails(@PathVariable Long id) {
        log.debug("REST request to get NurseryStockDetails : {}", id);
        Optional<NurseryStockDetailsDTO> nurseryStockDetailsDTO = nurseryStockDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nurseryStockDetailsDTO);
    }

    /**
     * DELETE  /nursery-stock-details/:id : delete the "id" nurseryStockDetails.
     *
     * @param id the id of the nurseryStockDetailsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nursery-stock-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteNurseryStockDetails(@PathVariable Long id) {
        log.debug("REST request to delete NurseryStockDetails : {}", id);
        nurseryStockDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
