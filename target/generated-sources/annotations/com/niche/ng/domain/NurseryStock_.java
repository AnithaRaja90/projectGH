package com.niche.ng.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NurseryStock.class)
public abstract class NurseryStock_ {

	public static volatile SingularAttribute<NurseryStock, PickListValue> pickListCategory;
	public static volatile SingularAttribute<NurseryStock, String> description;
	public static volatile SingularAttribute<NurseryStock, PickListValue> pickListVariety;
	public static volatile SingularAttribute<NurseryStock, Long> consumedQuantity;
	public static volatile SingularAttribute<NurseryStock, Instant> createdAt;
	public static volatile SingularAttribute<NurseryStock, Nursery> nursery;
	public static volatile SingularAttribute<NurseryStock, Long> createdBy;
	public static volatile SetAttribute<NurseryStock, NurseryStockDetails> nurseryStockDetails;
	public static volatile SingularAttribute<NurseryStock, Long> modifiedBy;
	public static volatile SingularAttribute<NurseryStock, Long> id;
	public static volatile SingularAttribute<NurseryStock, Long> addedQuantity;
	public static volatile SingularAttribute<NurseryStock, Long> currentQuantity;
	public static volatile SingularAttribute<NurseryStock, Integer> status;
	public static volatile SingularAttribute<NurseryStock, Instant> updatedAt;

}

