package com.niche.ng.service.impl;

import com.niche.ng.service.MotherBedService;
import com.niche.ng.domain.MotherBed;
import com.niche.ng.repository.MotherBedRepository;
import com.niche.ng.service.dto.MotherBedDTO;
import com.niche.ng.service.mapper.MotherBedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing MotherBed.
 */
@Service
@Transactional
public class MotherBedServiceImpl implements MotherBedService {

    private final Logger log = LoggerFactory.getLogger(MotherBedServiceImpl.class);

    private final MotherBedRepository motherBedRepository;

    private final MotherBedMapper motherBedMapper;

    public MotherBedServiceImpl(MotherBedRepository motherBedRepository, MotherBedMapper motherBedMapper) {
        this.motherBedRepository = motherBedRepository;
        this.motherBedMapper = motherBedMapper;
    }

    /**
     * Save a motherBed.
     *
     * @param motherBedDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MotherBedDTO save(MotherBedDTO motherBedDTO) {
        log.debug("Request to save MotherBed : {}", motherBedDTO);
        MotherBed motherBed = motherBedMapper.toEntity(motherBedDTO);
        motherBed = motherBedRepository.save(motherBed);
        return motherBedMapper.toDto(motherBed);
    }

    /**
     * Get all the motherBeds.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MotherBedDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MotherBeds");
        return motherBedRepository.findAll(pageable)
            .map(motherBedMapper::toDto);
    }


    /**
     * Get one motherBed by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MotherBedDTO> findOne(Long id) {
        log.debug("Request to get MotherBed : {}", id);
        return motherBedRepository.findById(id)
            .map(motherBedMapper::toDto);
    }

    /**
     * Delete the motherBed by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MotherBed : {}", id);
        motherBedRepository.deleteById(id);
    }
}
