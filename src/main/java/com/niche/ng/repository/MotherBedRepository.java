package com.niche.ng.repository;

import com.niche.ng.domain.MotherBed;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MotherBed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MotherBedRepository extends JpaRepository<MotherBed, Long> {

}
