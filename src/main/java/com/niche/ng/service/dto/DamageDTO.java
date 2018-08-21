/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs DamageDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Damage entity.
 */
public class DamageDTO implements Serializable {

    private Long id;

    @NotNull(message = "Quantity cannot be blank.")
    private Long noOfQuantity;

    @NotNull(message = "Date cannot be blank.")
    private LocalDate date;

    private Integer status;

    private Long createdBy;

    private Long modifiedBy;

    private Instant createdAt;

    private Instant updatedAt;

    private Long batchId;

    private String batchBatchName;

    private Long descriptionId;

    private String descriptionPickListValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoOfQuantity() {
        return noOfQuantity;
    }

    public void setNoOfQuantity(Long noOfQuantity) {
        this.noOfQuantity = noOfQuantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getBatchBatchName() {
        return batchBatchName;
    }

    public void setBatchBatchName(String batchBatchName) {
        this.batchBatchName = batchBatchName;
    }

    public Long getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(Long pickListValueId) {
        this.descriptionId = pickListValueId;
    }

    public String getDescriptionPickListValue() {
        return descriptionPickListValue;
    }

    public void setDescriptionPickListValue(String pickListValuePickListValue) {
        this.descriptionPickListValue = pickListValuePickListValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DamageDTO damageDTO = (DamageDTO) o;
        if (damageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), damageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DamageDTO{" +
            "id=" + getId() +
            ", noOfQuantity=" + getNoOfQuantity() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", batch=" + getBatchId() +
            ", batch='" + getBatchBatchName() + "'" +
            ", description=" + getDescriptionId() +
            ", description='" + getDescriptionPickListValue() + "'" +
            "}";
    }
}
