package com.niche.ng.domain;

import java.time.Instant;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShadeArea.class)
public abstract class ShadeArea_ {

	public static volatile SingularAttribute<ShadeArea, LocalDate> date;
	public static volatile SingularAttribute<ShadeArea, Instant> createdAt;
	public static volatile SingularAttribute<ShadeArea, Integer> damage;
	public static volatile SingularAttribute<ShadeArea, Long> createdBy;
	public static volatile SingularAttribute<ShadeArea, Batch> batch;
	public static volatile SingularAttribute<ShadeArea, Long> noOfSeedlings;
	public static volatile SingularAttribute<ShadeArea, Long> modifiedBy;
	public static volatile SingularAttribute<ShadeArea, Long> id;
	public static volatile SingularAttribute<ShadeArea, Integer> saplings;
	public static volatile SingularAttribute<ShadeArea, Integer> status;
	public static volatile SingularAttribute<ShadeArea, Instant> updatedAt;

}

