/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownPurchaseDetailsDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the GodownPurchaseDetails entity.
 */
public class GodownPurchaseDetailsDTO implements Serializable {

    private Long id;

    @NotNull
    private Long quantity;

    @NotNull
    private LocalDate date;

    private Long price;

    private String ownedBy;

    private String vendorName;

    private String vendorAddress;

    private Long vendorPhone;

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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public Long getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(Long vendorPhone) {
        this.vendorPhone = vendorPhone;
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

        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = (GodownPurchaseDetailsDTO) o;
        if (godownPurchaseDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godownPurchaseDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GodownPurchaseDetailsDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", date='" + getDate() + "'" +
            ", price=" + getPrice() +
            ", ownedBy='" + getOwnedBy() + "'" +
            ", vendorName='" + getVendorName() + "'" +
            ", vendorAddress='" + getVendorAddress() + "'" +
            ", vendorPhone=" + getVendorPhone() +
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
