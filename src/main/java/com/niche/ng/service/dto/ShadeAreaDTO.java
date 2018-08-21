/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ShadeAreaDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ShadeArea entity.
 */
public class ShadeAreaDTO implements Serializable {

    private Long id;

    @NotNull(message = "Quantity cannot be blank.")
    private Long noOfSeedlings;

    @NotNull(message = "Date cannot be blank.")
    private LocalDate date;

    private Integer status;

    private Long createdBy;

    private Long modifiedBy;

    private Instant createdAt;

    private Instant updatedAt;

    private Integer damage;

    private Integer saplings;

    private Long batchId;

    private String batchBatchName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoOfSeedlings() {
        return noOfSeedlings;
    }

    public void setNoOfSeedlings(Long noOfSeedlings) {
        this.noOfSeedlings = noOfSeedlings;
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

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getSaplings() {
        return saplings;
    }

    public void setSaplings(Integer saplings) {
        this.saplings = saplings;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShadeAreaDTO shadeAreaDTO = (ShadeAreaDTO) o;
        if (shadeAreaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shadeAreaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShadeAreaDTO{" +
            "id=" + getId() +
            ", noOfSeedlings=" + getNoOfSeedlings() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", damage=" + getDamage() +
            ", saplings=" + getSaplings() +
            ", batch=" + getBatchId() +
            ", batch='" + getBatchBatchName() + "'" +
            "}";
    }
}
