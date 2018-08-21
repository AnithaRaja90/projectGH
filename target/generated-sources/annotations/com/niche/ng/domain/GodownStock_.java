package com.niche.ng.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GodownStock.class)
public abstract class GodownStock_ {

	public static volatile SetAttribute<GodownStock, GodownStockDetails> godownStockDetails;
	public static volatile SingularAttribute<GodownStock, PickListValue> pickListCategory;
	public static volatile SingularAttribute<GodownStock, Godown> godown;
	public static volatile SingularAttribute<GodownStock, String> description;
	public static volatile SingularAttribute<GodownStock, PickListValue> pickListVariety;
	public static volatile SingularAttribute<GodownStock, Long> consumedQuantity;
	public static volatile SingularAttribute<GodownStock, Instant> createdAt;
	public static volatile SingularAttribute<GodownStock, PickListValue> pickListQuantityType;
	public static volatile SingularAttribute<GodownStock, Long> createdBy;
	public static volatile SingularAttribute<GodownStock, Long> modifiedBy;
	public static volatile SingularAttribute<GodownStock, Long> id;
	public static volatile SingularAttribute<GodownStock, Long> addedQuantity;
	public static volatile SingularAttribute<GodownStock, Long> currentQuantity;
	public static volatile SingularAttribute<GodownStock, Integer> status;
	public static volatile SingularAttribute<GodownStock, Instant> updatedAt;

}

