/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Sector entity.
 */
public class SectorDTO implements Serializable {

    private Long id;

    @NotNull(message = "Sector name cannot be blank.")
    private String sectorName;

    private String sectorAddress;

    private String sectorIncharge;

    private Integer status;

    private Long createdBy;

    private Long modifiedBy;

    private Instant createdAt;

    private Instant updatedAt;

    @NotNull(message = "Zonal name cannot be blank.")
    private Long zonalId;

    private String zonalZoneName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getSectorAddress() {
        return sectorAddress;
    }

    public void setSectorAddress(String sectorAddress) {
        this.sectorAddress = sectorAddress;
    }

    public String getSectorIncharge() {
        return sectorIncharge;
    }

    public void setSectorIncharge(String sectorIncharge) {
        this.sectorIncharge = sectorIncharge;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getZonalId() {
        return zonalId;
    }

    public void setZonalId(Long zonalId) {
        this.zonalId = zonalId;
    }

    public String getZonalZoneName() {
        return zonalZoneName;
    }

    public void setZonalZoneName(String zonalZoneName) {
        this.zonalZoneName = zonalZoneName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SectorDTO sectorDTO = (SectorDTO) o;
        if (sectorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sectorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SectorDTO{" +
            "id=" + getId() +
            ", sectorName='" + getSectorName() + "'" +
            ", sectorAddress='" + getSectorAddress() + "'" +
            ", sectorIncharge='" + getSectorIncharge() + "'" +
            ", status=" + getStatus() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", zonal=" + getZonalId() +
            ", zonal='" + getZonalZoneName() + "'" +
            "}";
    }
}
