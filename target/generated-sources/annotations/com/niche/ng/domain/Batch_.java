package com.niche.ng.domain;

import java.time.Instant;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Batch.class)
public abstract class Batch_ {

	public static volatile SingularAttribute<Batch, String> batchName;
	public static volatile SingularAttribute<Batch, PickListValue> quantityType;
	public static volatile SingularAttribute<Batch, String> batchNo;
	public static volatile SingularAttribute<Batch, PickListValue> pickListCategory;
	public static volatile SingularAttribute<Batch, Long> quantity;
	public static volatile SetAttribute<Batch, Damage> damages;
	public static volatile SingularAttribute<Batch, String> motherBed;
	public static volatile SingularAttribute<Batch, PickListValue> pickListVariety;
	public static volatile SingularAttribute<Batch, LocalDate> sowingDate;
	public static volatile SingularAttribute<Batch, Instant> createdAt;
	public static volatile SingularAttribute<Batch, Integer> round;
	public static volatile SingularAttribute<Batch, Nursery> nursery;
	public static volatile SingularAttribute<Batch, LocalDate> closedDate;
	public static volatile SingularAttribute<Batch, Long> createdBy;
	public static volatile SetAttribute<Batch, ShadeArea> shadeAreas;
	public static volatile SetAttribute<Batch, NurseryStockDetails> nurseryStockDetails;
	public static volatile SingularAttribute<Batch, Long> modifiedBy;
	public static volatile SingularAttribute<Batch, Long> id;
	public static volatile SingularAttribute<Batch, Integer> showingType;
	public static volatile SingularAttribute<Batch, String> remarks;
	public static volatile SingularAttribute<Batch, Integer> status;
	public static volatile SingularAttribute<Batch, Instant> updatedAt;

}

