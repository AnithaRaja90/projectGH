/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Nursery entity.
 */
public class NurseryDTO implements Serializable {

    private Long id;

    @NotNull(message = "Sector name cannot be blank.")
    private Long sectorId;

    @NotNull(message = "Nursery name cannot be blank.")
    private String nurseryName;

    private String nurseryAddress;

    private String nurseryIncharge;

    private Integer status;

    private Long createdBy;

    private Long modifiedBy;

    private Instant createdAt;

    private Instant updatedAt;

    private String sectorSectorName;

    private Long nurseryTypeId;

    private String nurseryTypePickListValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNurseryName() {
        return nurseryName;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    public String getNurseryAddress() {
        return nurseryAddress;
    }

    public void setNurseryAddress(String nurseryAddress) {
        this.nurseryAddress = nurseryAddress;
    }

    public String getNurseryIncharge() {
        return nurseryIncharge;
    }

    public void setNurseryIncharge(String nurseryIncharge) {
        this.nurseryIncharge = nurseryIncharge;
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

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorSectorName() {
        return sectorSectorName;
    }

    public void setSectorSectorName(String sectorSectorName) {
        this.sectorSectorName = sectorSectorName;
    }

    public Long getNurseryTypeId() {
        return nurseryTypeId;
    }

    public void setNurseryTypeId(Long pickListValueId) {
        this.nurseryTypeId = pickListValueId;
    }

    public String getNurseryTypePickListValue() {
        return nurseryTypePickListValue;
    }

    public void setNurseryTypePickListValue(String pickListValuePickListValue) {
        this.nurseryTypePickListValue = pickListValuePickListValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NurseryDTO nurseryDTO = (NurseryDTO) o;
        if (nurseryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NurseryDTO{" +
            "id=" + getId() +
            ", nurseryName='" + getNurseryName() + "'" +
            ", nurseryAddress='" + getNurseryAddress() + "'" +
            ", nurseryIncharge='" + getNurseryIncharge() + "'" +
            ", status=" + getStatus() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", sector=" + getSectorId() +
            ", sector='" + getSectorSectorName() + "'" +
            ", nurseryType=" + getNurseryTypeId() +
            ", nurseryType='" + getNurseryTypePickListValue() + "'" +
            "}";
    }
}
