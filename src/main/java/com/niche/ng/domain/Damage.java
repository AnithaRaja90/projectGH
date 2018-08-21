/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs Damage Generation.
 *  Declared the table fields and data types for the damage table.
 *  Defined the following Relation for the damage table :
 *  ManyToOne Relation to Batch
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
 * A Damage.
 */
@Entity
@Table(name = "damage")
public class Damage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "no_of_quantity", nullable = false)
    private Long noOfQuantity;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private LocalDate date;

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
    @JsonIgnoreProperties("damages")
    @JoinColumn(name="batch_id", referencedColumnName="id")
    private Batch batch;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PickListValue description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoOfQuantity() {
        return noOfQuantity;
    }

    public Damage noOfQuantity(Long noOfQuantity) {
        this.noOfQuantity = noOfQuantity;
        return this;
    }

    public void setNoOfQuantity(Long noOfQuantity) {
        this.noOfQuantity = noOfQuantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public Damage date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public Damage status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Damage createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public Damage modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Damage createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Damage updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Batch getBatch() {
        return batch;
    }

    public Damage batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public PickListValue getDescription() {
        return description;
    }

    public Damage description(PickListValue pickListValue) {
        this.description = pickListValue;
        return this;
    }

    public void setDescription(PickListValue pickListValue) {
        this.description = pickListValue;
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
        Damage damage = (Damage) o;
        if (damage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), damage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Damage{" +
            "id=" + getId() +
            ", noOfQuantity=" + getNoOfQuantity() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
