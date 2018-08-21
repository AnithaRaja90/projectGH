package com.niche.ng.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MotherBed.class)
public abstract class MotherBed_ {

	public static volatile SingularAttribute<MotherBed, Instant> createdAt;
	public static volatile SingularAttribute<MotherBed, Nursery> nursery;
	public static volatile SingularAttribute<MotherBed, Long> createdBy;
	public static volatile SingularAttribute<MotherBed, Long> modifiedBy;
	public static volatile SingularAttribute<MotherBed, Long> id;
	public static volatile SingularAttribute<MotherBed, Integer> value;
	public static volatile SingularAttribute<MotherBed, Integer> status;
	public static volatile SingularAttribute<MotherBed, Instant> updatedAt;

}

