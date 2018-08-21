package com.niche.ng.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Nursery.class)
public abstract class Nursery_ {

	public static volatile SetAttribute<Nursery, NurseryStock> nurseryStocks;
	public static volatile SingularAttribute<Nursery, String> nurseryAddress;
	public static volatile SingularAttribute<Nursery, String> nurseryName;
	public static volatile SingularAttribute<Nursery, String> nurseryIncharge;
	public static volatile SingularAttribute<Nursery, Instant> createdAt;
	public static volatile SetAttribute<Nursery, Batch> batchs;
	public static volatile SetAttribute<Nursery, MotherBed> motherBeds;
	public static volatile SingularAttribute<Nursery, Long> createdBy;
	public static volatile SingularAttribute<Nursery, Long> modifiedBy;
	public static volatile SingularAttribute<Nursery, Long> id;
	public static volatile SingularAttribute<Nursery, PickListValue> nurseryType;
	public static volatile SingularAttribute<Nursery, Sector> sector;
	public static volatile SingularAttribute<Nursery, Integer> status;
	public static volatile SingularAttribute<Nursery, Instant> updatedAt;

}

