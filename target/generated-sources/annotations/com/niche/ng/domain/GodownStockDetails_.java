package com.niche.ng.domain;

import java.time.Instant;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GodownStockDetails.class)
public abstract class GodownStockDetails_ {

	public static volatile SingularAttribute<GodownStockDetails, LocalDate> date;
	public static volatile SingularAttribute<GodownStockDetails, GodownStock> godownStock;
	public static volatile SingularAttribute<GodownStockDetails, Instant> createdAt;
	public static volatile SingularAttribute<GodownStockDetails, Long> quantity;
	public static volatile SingularAttribute<GodownStockDetails, Long> createdBy;
	public static volatile SingularAttribute<GodownStockDetails, String> description;
	public static volatile SingularAttribute<GodownStockDetails, Long> modifiedBy;
	public static volatile SingularAttribute<GodownStockDetails, Long> id;
	public static volatile SingularAttribute<GodownStockDetails, Integer> status;
	public static volatile SingularAttribute<GodownStockDetails, Instant> updatedAt;

}

