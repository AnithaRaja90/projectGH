/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs Sector Generation.
 *  Declared the table fields and data types for the Sector table.
 *  Defined the following Relation for the Sector Table :
 *  OneToMany Relation to Nursery
 *  ManyToOne Relation to Zonal
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Sector.
 */
@Entity
@Table(name = "sector")
public class Sector implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "sector_name", nullable = false)
    private String sectorName;

    @Column(name = "sector_address")
    private String sectorAddress;

    @Column(name = "sector_incharge")
    private String sectorIncharge;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "sector", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Nursery> nurserys = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("sectors")
    @JoinColumn(name="zonal_id", referencedColumnName="id")
    private Zonal zonal;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectorName() {
        return sectorName;
    }

    public Sector sectorName(String sectorName) {
        this.sectorName = sectorName;
        return this;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getSectorAddress() {
        return sectorAddress;
    }

    public Sector sectorAddress(String sectorAddress) {
        this.sectorAddress = sectorAddress;
        return this;
    }

    public void setSectorAddress(String sectorAddress) {
        this.sectorAddress = sectorAddress;
    }

    public String getSectorIncharge() {
        return sectorIncharge;
    }

    public Sector sectorIncharge(String sectorIncharge) {
        this.sectorIncharge = sectorIncharge;
        return this;
    }

    public void setSectorIncharge(String sectorIncharge) {
        this.sectorIncharge = sectorIncharge;
    }

    public Integer getStatus() {
        return status;
    }

    public Sector status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Sector createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public Sector modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Sector createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Sector updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Nursery> getNurserys() {
        return nurserys;
    }

    public Sector nurserys(Set<Nursery> nurseries) {
        this.nurserys = nurseries;
        return this;
    }

    public Sector addNurserys(Nursery nursery) {
        this.nurserys.add(nursery);
        nursery.setSector(this);
        return this;
    }

    public Sector removeNurserys(Nursery nursery) {
        this.nurserys.remove(nursery);
        nursery.setSector(null);
        return this;
    }

    public void setNurserys(Set<Nursery> nurseries) {
        this.nurserys = nurseries;
    }

    public Zonal getZonal() {
        return zonal;
    }

    public Sector zonal(Zonal zonal) {
        this.zonal = zonal;
        return this;
    }

    public void setZonal(Zonal zonal) {
        this.zonal = zonal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sector sector = (Sector) o;
        if (sector.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sector.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sector{" +
            "id=" + getId() +
            ", sectorName='" + getSectorName() + "'" +
            ", sectorAddress='" + getSectorAddress() + "'" +
            ", sectorIncharge='" + getSectorIncharge() + "'" +
            ", status=" + getStatus() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
