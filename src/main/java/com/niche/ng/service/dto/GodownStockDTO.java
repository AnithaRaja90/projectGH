/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the GodownStock entity.
 */
public class GodownStockDTO implements Serializable {

    private Long id;

    private Long currentQuantity;

    private Long addedQuantity;

    private Long consumedQuantity;

    private String description;

    private Integer status;

    private Long createdBy;

    private Long modifiedBy;

    private Instant createdAt;

    private Instant updatedAt;

    private Long pickListVarietyId;

    private String pickListVarietyPickListValue;

    private Long pickListCategoryId;

    private String pickListCategoryPickListValue;

    private Long pickListQuantityTypeId;

    private String pickListQuantityTypePickListValue;

    private Long godownId;

    private String godownName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Long currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Long getAddedQuantity() {
        return addedQuantity;
    }

    public void setAddedQuantity(Long addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    public Long getConsumedQuantity() {
        return consumedQuantity;
    }

    public void setConsumedQuantity(Long consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getPickListVarietyId() {
        return pickListVarietyId;
    }

    public void setPickListVarietyId(Long pickListValueId) {
        this.pickListVarietyId = pickListValueId;
    }

    public String getPickListVarietyPickListValue() {
        return pickListVarietyPickListValue;
    }

    public void setPickListVarietyPickListValue(String pickListValuePickListValue) {
        this.pickListVarietyPickListValue = pickListValuePickListValue;
    }

    public Long getPickListCategoryId() {
        return pickListCategoryId;
    }

    public void setPickListCategoryId(Long pickListValueId) {
        this.pickListCategoryId = pickListValueId;
    }

    public String getPickListCategoryPickListValue() {
        return pickListCategoryPickListValue;
    }

    public void setPickListCategoryPickListValue(String pickListValuePickListValue) {
        this.pickListCategoryPickListValue = pickListValuePickListValue;
    }

    public Long getPickListQuantityTypeId() {
        return pickListQuantityTypeId;
    }

    public void setPickListQuantityTypeId(Long pickListValueId) {
        this.pickListQuantityTypeId = pickListValueId;
    }

    public String getPickListQuantityTypePickListValue() {
        return pickListQuantityTypePickListValue;
    }

    public void setPickListQuantityTypePickListValue(String pickListValuePickListValue) {
        this.pickListQuantityTypePickListValue = pickListValuePickListValue;
    }

    public Long getGodownId() {
        return godownId;
    }

    public void setGodownId(Long godownId) {
        this.godownId = godownId;
    }

    public String getGodownName() {
        return godownName;
    }

    public void setGodownName(String godownName) {
        this.godownName = godownName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GodownStockDTO godownStockDTO = (GodownStockDTO) o;
        if (godownStockDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godownStockDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GodownStockDTO{" +
            "id=" + getId() +
            ", currentQuantity=" + getCurrentQuantity() +
            ", addedQuantity=" + getAddedQuantity() +
            ", consumedQuantity=" + getConsumedQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", pickListVariety=" + getPickListVarietyId() +
            ", pickListVariety='" + getPickListVarietyPickListValue() + "'" +
            ", pickListCategory=" + getPickListCategoryId() +
            ", pickListCategory='" + getPickListCategoryPickListValue() + "'" +
            ", pickListQuantityType=" + getPickListQuantityTypeId() +
            ", pickListQuantityType='" + getPickListQuantityTypePickListValue() + "'" +
            ", godown=" + getGodownId() +
            ", godown='" + getGodownName() + "'" +
            "}";
    }
}
