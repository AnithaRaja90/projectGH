/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListValue Generation.
 *  Declared the table fields and data types for the PickListValue table.
 *  Defined the following Relation for the PickListValue Table :
 *  OneToMany Relation to Batch, GodownPurchaseDetails, GodownStock, NurseryStock, PickListValue
 *  ManyToOne Relation to PickList, PickListValue
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
 * A PickListValue.
 */
@Entity
@Table(name = "pick_list_value")
public class PickListValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "pick_list_value", nullable = false)
    private String pickListValue;

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

    @OneToMany(mappedBy = "pickValue", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<PickListValue> selfIds = new HashSet<>();

    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> varietys = new HashSet<>();

    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> categorys = new HashSet<>();

    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStock> nurseryStockVarietys = new HashSet<>();

    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStock> nurseryStockCategorys = new HashSet<>();

    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownPurchaseDetails> godownPurchaseVarietys = new HashSet<>();

    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownPurchaseDetails> godownPurchaseCategorys = new HashSet<>();

    @OneToMany(mappedBy = "pickListQuantityType", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownPurchaseDetails> godownPurchaseQuantityTypes = new HashSet<>();

    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStock> godownStockVarietys = new HashSet<>();

    @OneToMany(mappedBy = "pickListCategory")
    private Set<GodownStock> godownStockCategorys = new HashSet<>();

    @OneToMany(mappedBy = "pickListQuantityType")
    private Set<GodownStock> godownStockQuantityTypes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("pickListValues")
    @JoinColumn(name="pick_list_id", referencedColumnName="id")
    private PickList pickList;

    @ManyToOne
    @JsonIgnoreProperties("selfIds")
    @JoinColumn(name="pick_value_id", referencedColumnName="id")
    private PickListValue pickValue;

    @OneToMany(mappedBy = "nurseryType")
    private Set<Nursery> nurserys = new HashSet<>();

    @OneToMany(mappedBy = "quantityType")
    private Set<Batch> batchQuantityTypes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickListValue() {
        return pickListValue;
    }

    public PickListValue pickListValue(String pickListValue) {
        this.pickListValue = pickListValue;
        return this;
    }

    public void setPickListValue(String pickListValue) {
        this.pickListValue = pickListValue;
    }

    public Integer getStatus() {
        return status;
    }

    public PickListValue status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public PickListValue createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public PickListValue modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public PickListValue createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public PickListValue updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<PickListValue> getSelfIds() {
        return selfIds;
    }

    public PickListValue selfIds(Set<PickListValue> pickListValues) {
        this.selfIds = pickListValues;
        return this;
    }

    public PickListValue addSelfIds(PickListValue pickListValue) {
        this.selfIds.add(pickListValue);
        pickListValue.setPickValue(this);
        return this;
    }

    public PickListValue removeSelfIds(PickListValue pickListValue) {
        this.selfIds.remove(pickListValue);
        pickListValue.setPickValue(null);
        return this;
    }

    public void setSelfIds(Set<PickListValue> pickListValues) {
        this.selfIds = pickListValues;
    }

    public Set<Batch> getVarietys() {
        return varietys;
    }

    public PickListValue varietys(Set<Batch> batches) {
        this.varietys = batches;
        return this;
    }

    public PickListValue addVarietys(Batch batch) {
        this.varietys.add(batch);
        batch.setPickListVariety(this);
        return this;
    }

    public PickListValue removeVarietys(Batch batch) {
        this.varietys.remove(batch);
        batch.setPickListVariety(null);
        return this;
    }

    public void setVarietys(Set<Batch> batches) {
        this.varietys = batches;
    }

    public Set<Batch> getCategorys() {
        return categorys;
    }

    public PickListValue categorys(Set<Batch> batches) {
        this.categorys = batches;
        return this;
    }

    public PickListValue addCategorys(Batch batch) {
        this.categorys.add(batch);
        batch.setPickListCategory(this);
        return this;
    }

    public PickListValue removeCategorys(Batch batch) {
        this.categorys.remove(batch);
        batch.setPickListCategory(null);
        return this;
    }

    public void setCategorys(Set<Batch> batches) {
        this.categorys = batches;
    }

    public Set<NurseryStock> getNurseryStockVarietys() {
        return nurseryStockVarietys;
    }

    public PickListValue nurseryStockVarietys(Set<NurseryStock> nurseryStocks) {
        this.nurseryStockVarietys = nurseryStocks;
        return this;
    }

    public PickListValue addNurseryStockVarietys(NurseryStock nurseryStock) {
        this.nurseryStockVarietys.add(nurseryStock);
        nurseryStock.setPickListVariety(this);
        return this;
    }

    public PickListValue removeNurseryStockVarietys(NurseryStock nurseryStock) {
        this.nurseryStockVarietys.remove(nurseryStock);
        nurseryStock.setPickListVariety(null);
        return this;
    }

    public void setNurseryStockVarietys(Set<NurseryStock> nurseryStocks) {
        this.nurseryStockVarietys = nurseryStocks;
    }

    public Set<NurseryStock> getNurseryStockCategorys() {
        return nurseryStockCategorys;
    }

    public PickListValue nurseryStockCategorys(Set<NurseryStock> nurseryStocks) {
        this.nurseryStockCategorys = nurseryStocks;
        return this;
    }

    public PickListValue addNurseryStockCategorys(NurseryStock nurseryStock) {
        this.nurseryStockCategorys.add(nurseryStock);
        nurseryStock.setPickListCategory(this);
        return this;
    }

    public PickListValue removeNurseryStockCategorys(NurseryStock nurseryStock) {
        this.nurseryStockCategorys.remove(nurseryStock);
        nurseryStock.setPickListCategory(null);
        return this;
    }

    public void setNurseryStockCategorys(Set<NurseryStock> nurseryStocks) {
        this.nurseryStockCategorys = nurseryStocks;
    }

    public Set<GodownPurchaseDetails> getGodownPurchaseVarietys() {
        return godownPurchaseVarietys;
    }

    public PickListValue godownPurchaseVarietys(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseVarietys = godownPurchaseDetails;
        return this;
    }

    public PickListValue addGodownPurchaseVarietys(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseVarietys.add(godownPurchaseDetails);
        godownPurchaseDetails.setPickListVariety(this);
        return this;
    }

    public PickListValue removeGodownPurchaseVarietys(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseVarietys.remove(godownPurchaseDetails);
        godownPurchaseDetails.setPickListVariety(null);
        return this;
    }

    public void setGodownPurchaseVarietys(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseVarietys = godownPurchaseDetails;
    }

    public Set<GodownPurchaseDetails> getGodownPurchaseCategorys() {
        return godownPurchaseCategorys;
    }

    public PickListValue godownPurchaseCategorys(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseCategorys = godownPurchaseDetails;
        return this;
    }

    public PickListValue addGodownPurchaseCategorys(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseCategorys.add(godownPurchaseDetails);
        godownPurchaseDetails.setPickListCategory(this);
        return this;
    }

    public PickListValue removeGodownPurchaseCategorys(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseCategorys.remove(godownPurchaseDetails);
        godownPurchaseDetails.setPickListCategory(null);
        return this;
    }

    public void setGodownPurchaseCategorys(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseCategorys = godownPurchaseDetails;
    }

    public Set<GodownPurchaseDetails> getGodownPurchaseQuantityTypes() {
        return godownPurchaseQuantityTypes;
    }

    public PickListValue godownPurchaseQuantityTypes(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseQuantityTypes = godownPurchaseDetails;
        return this;
    }

    public PickListValue addGodownPurchaseQuantityType(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseQuantityTypes.add(godownPurchaseDetails);
        godownPurchaseDetails.setPickListQuantityType(this);
        return this;
    }

    public PickListValue removeGodownPurchaseQuantityType(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseQuantityTypes.remove(godownPurchaseDetails);
        godownPurchaseDetails.setPickListQuantityType(null);
        return this;
    }

    public void setGodownPurchaseQuantityTypes(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseQuantityTypes = godownPurchaseDetails;
    }

    public Set<GodownStock> getGodownStockVarietys() {
        return godownStockVarietys;
    }

    public PickListValue godownStockVarietys(Set<GodownStock> godownStocks) {
        this.godownStockVarietys = godownStocks;
        return this;
    }

    public PickListValue addGodownStockVarietys(GodownStock godownStock) {
        this.godownStockVarietys.add(godownStock);
        godownStock.setPickListVariety(this);
        return this;
    }

    public PickListValue removeGodownStockVarietys(GodownStock godownStock) {
        this.godownStockVarietys.remove(godownStock);
        godownStock.setPickListVariety(null);
        return this;
    }

    public void setGodownStockVarietys(Set<GodownStock> godownStocks) {
        this.godownStockVarietys = godownStocks;
    }

    public Set<GodownStock> getGodownStockCategorys() {
        return godownStockCategorys;
    }

    public PickListValue godownStockCategorys(Set<GodownStock> godownStocks) {
        this.godownStockCategorys = godownStocks;
        return this;
    }

    public PickListValue addGodownStockCategorys(GodownStock godownStock) {
        this.godownStockCategorys.add(godownStock);
        godownStock.setPickListCategory(this);
        return this;
    }

    public PickListValue removeGodownStockCategorys(GodownStock godownStock) {
        this.godownStockCategorys.remove(godownStock);
        godownStock.setPickListCategory(null);
        return this;
    }

    public void setGodownStockCategorys(Set<GodownStock> godownStocks) {
        this.godownStockCategorys = godownStocks;
    }

    public Set<GodownStock> getGodownStockQuantityTypes() {
        return godownStockQuantityTypes;
    }

    public PickListValue godownStockQuantityTypes(Set<GodownStock> godownStocks) {
        this.godownStockQuantityTypes = godownStocks;
        return this;
    }

    public PickListValue addGodownStockQuantityTypes(GodownStock godownStock) {
        this.godownStockQuantityTypes.add(godownStock);
        godownStock.setPickListQuantityType(this);
        return this;
    }

    public PickListValue removeGodownStockQuantityTypes(GodownStock godownStock) {
        this.godownStockQuantityTypes.remove(godownStock);
        godownStock.setPickListQuantityType(null);
        return this;
    }

    public void setGodownStockQuantityTypes(Set<GodownStock> godownStocks) {
        this.godownStockQuantityTypes = godownStocks;
    }

    public PickList getPickList() {
        return pickList;
    }

    public PickListValue pickList(PickList pickList) {
        this.pickList = pickList;
        return this;
    }

    public void setPickList(PickList pickList) {
        this.pickList = pickList;
    }

    public PickListValue getPickValue() {
        return pickValue;
    }

    public PickListValue pickValue(PickListValue pickListValue) {
        this.pickValue = pickListValue;
        return this;
    }

    public void setPickValue(PickListValue pickListValue) {
        this.pickValue = pickListValue;
    }

    public Set<Nursery> getNurserys() {
        return nurserys;
    }

    public PickListValue nurserys(Set<Nursery> nurseries) {
        this.nurserys = nurseries;
        return this;
    }

    public PickListValue addNurserys(Nursery nursery) {
        this.nurserys.add(nursery);
        nursery.setNurseryType(this);
        return this;
    }

    public PickListValue removeNurserys(Nursery nursery) {
        this.nurserys.remove(nursery);
        nursery.setNurseryType(null);
        return this;
    }

    public void setNurserys(Set<Nursery> nurseries) {
        this.nurserys = nurseries;
    }

    public Set<Batch> getBatchQuantityTypes() {
        return batchQuantityTypes;
    }

    public PickListValue batchQuantityTypes(Set<Batch> batches) {
        this.batchQuantityTypes = batches;
        return this;
    }

    public PickListValue addBatchQuantityTypes(Batch batch) {
        this.batchQuantityTypes.add(batch);
        batch.setQuantityType(this);
        return this;
    }

    public PickListValue removeBatchQuantityTypes(Batch batch) {
        this.batchQuantityTypes.remove(batch);
        batch.setQuantityType(null);
        return this;
    }

    public void setBatchQuantityTypes(Set<Batch> batches) {
        this.batchQuantityTypes = batches;
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
        PickListValue pickListValue = (PickListValue) o;
        if (pickListValue.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pickListValue.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PickListValue{" +
            "id=" + getId() +
            ", pickListValue='" + getPickListValue() + "'" +
            ", status=" + getStatus() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
