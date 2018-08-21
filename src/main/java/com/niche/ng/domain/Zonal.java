/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs Zonal Generation.
 *  Declared the table fields and data types for the Zonal table.
 *  Defined the following Relation for the Zonal Table :
 *  OneToMany Relation to Sector Table
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Zonal.
 */
@Entity
@Table(name = "zonal")
public class Zonal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "zone_name", nullable = false)
    private String zoneName;

    @Column(name = "zone_address")
    private String zoneAddress;

    @Column(name = "zone_incharge")
    private String zoneIncharge;

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

    @OneToMany(mappedBy = "zonal", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Sector> sectors = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public Zonal zoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneAddress() {
        return zoneAddress;
    }

    public Zonal zoneAddress(String zoneAddress) {
        this.zoneAddress = zoneAddress;
        return this;
    }

    public void setZoneAddress(String zoneAddress) {
        this.zoneAddress = zoneAddress;
    }

    public String getZoneIncharge() {
        return zoneIncharge;
    }

    public Zonal zoneIncharge(String zoneIncharge) {
        this.zoneIncharge = zoneIncharge;
        return this;
    }

    public void setZoneIncharge(String zoneIncharge) {
        this.zoneIncharge = zoneIncharge;
    }

    public Integer getStatus() {
        return status;
    }

    public Zonal status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Zonal createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public Zonal modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Zonal createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Zonal updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Sector> getSectors() {
        return sectors;
    }

    public Zonal sectors(Set<Sector> sectors) {
        this.sectors = sectors;
        return this;
    }

    public Zonal addSectors(Sector sector) {
        this.sectors.add(sector);
        sector.setZonal(this);
        return this;
    }

    public Zonal removeSectors(Sector sector) {
        this.sectors.remove(sector);
        sector.setZonal(null);
        return this;
    }

    public void setSectors(Set<Sector> sectors) {
        this.sectors = sectors;
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
        Zonal zonal = (Zonal) o;
        if (zonal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), zonal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Zonal{" +
            "id=" + getId() +
            ", zoneName='" + getZoneName() + "'" +
            ", zoneAddress='" + getZoneAddress() + "'" +
            ", zoneIncharge='" + getZoneIncharge() + "'" +
            ", status=" + getStatus() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
