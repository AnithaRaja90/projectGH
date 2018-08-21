package com.niche.ng.service;

import com.niche.ng.service.dto.MotherBedDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MotherBed.
 */
public interface MotherBedService {

    /**
     * Save a motherBed.
     *
     * @param motherBedDTO the entity to save
     * @return the persisted entity
     */
    MotherBedDTO save(MotherBedDTO motherBedDTO);

    /**
     * Get all the motherBeds.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MotherBedDTO> findAll(Pageable pageable);


    /**
     * Get the "id" motherBed.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MotherBedDTO> findOne(Long id);

    /**
     * Delete the "id" motherBed.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
