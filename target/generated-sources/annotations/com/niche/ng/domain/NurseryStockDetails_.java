package com.niche.ng.domain;

import java.time.Instant;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NurseryStockDetails.class)
public abstract class NurseryStockDetails_ {

	public static volatile SingularAttribute<NurseryStockDetails, LocalDate> date;
	public static volatile SingularAttribute<NurseryStockDetails, Instant> createdAt;
	public static volatile SingularAttribute<NurseryStockDetails, Long> quantity;
	public static volatile SingularAttribute<NurseryStockDetails, Long> createdBy;
	public static volatile SingularAttribute<NurseryStockDetails, Batch> batch;
	public static volatile SingularAttribute<NurseryStockDetails, String> description;
	public static volatile SingularAttribute<NurseryStockDetails, Long> modifiedBy;
	public static volatile SingularAttribute<NurseryStockDetails, Long> id;
	public static volatile SingularAttribute<NurseryStockDetails, NurseryStock> nurseryStock;
	public static volatile SingularAttribute<NurseryStockDetails, Integer> status;
	public static volatile SingularAttribute<NurseryStockDetails, Instant> updatedAt;

}

