/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockDetails Generation.
 *  Declared the table fields and data types for the NurseryStockDetails table.
 *  Defined the following Relation for the NurseryStockDetails Table :
 *  ManyToOne Relation to Batch, NurseryStock
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A NurseryStockDetails.
 */
@Entity
@Table(name = "nursery_stock_details")
public class NurseryStockDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "description")
    private String description;

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

    @ManyToOne
    @JsonIgnoreProperties("nurseryStockDetails")
    private Batch batch;

    @ManyToOne
    @JsonIgnoreProperties("nurseryStockDetails")
    private NurseryStock nurseryStock;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public NurseryStockDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getQuantity() {
        return quantity;
    }

    public NurseryStockDetails quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public NurseryStockDetails description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public NurseryStockDetails status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public NurseryStockDetails createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public NurseryStockDetails modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public NurseryStockDetails createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public NurseryStockDetails updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Batch getBatch() {
        return batch;
    }

    public NurseryStockDetails batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public NurseryStock getNurseryStock() {
        return nurseryStock;
    }

    public NurseryStockDetails nurseryStock(NurseryStock nurseryStock) {
        this.nurseryStock = nurseryStock;
        return this;
    }

    public void setNurseryStock(NurseryStock nurseryStock) {
        this.nurseryStock = nurseryStock;
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
        NurseryStockDetails nurseryStockDetails = (NurseryStockDetails) o;
        if (nurseryStockDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryStockDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NurseryStockDetails{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", quantity=" + getQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
