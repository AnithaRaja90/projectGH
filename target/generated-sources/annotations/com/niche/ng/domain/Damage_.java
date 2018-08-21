package com.niche.ng.domain;

import java.time.Instant;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Damage.class)
public abstract class Damage_ {

	public static volatile SingularAttribute<Damage, LocalDate> date;
	public static volatile SingularAttribute<Damage, Instant> createdAt;
	public static volatile SingularAttribute<Damage, Long> createdBy;
	public static volatile SingularAttribute<Damage, Batch> batch;
	public static volatile SingularAttribute<Damage, PickListValue> description;
	public static volatile SingularAttribute<Damage, Long> modifiedBy;
	public static volatile SingularAttribute<Damage, Long> id;
	public static volatile SingularAttribute<Damage, Long> noOfQuantity;
	public static volatile SingularAttribute<Damage, Integer> status;
	public static volatile SingularAttribute<Damage, Instant> updatedAt;

}

