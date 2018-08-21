package com.niche.ng.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MotherBed entity.
 */
public class MotherBedDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer value;

    private Integer status;

    private Long createdBy;

    private Long modifiedBy;

    private Instant createdAt;

    private Instant updatedAt;

    private Long nurseryId;

    private String nurseryNurseryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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

    public Long getNurseryId() {
        return nurseryId;
    }

    public void setNurseryId(Long nurseryId) {
        this.nurseryId = nurseryId;
    }

    public String getNurseryNurseryName() {
        return nurseryNurseryName;
    }

    public void setNurseryNurseryName(String nurseryNurseryName) {
        this.nurseryNurseryName = nurseryNurseryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MotherBedDTO motherBedDTO = (MotherBedDTO) o;
        if (motherBedDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), motherBedDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MotherBedDTO{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", status=" + getStatus() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", nursery=" + getNurseryId() +
            ", nursery='" + getNurseryNurseryName() + "'" +
            "}";
    }
}
