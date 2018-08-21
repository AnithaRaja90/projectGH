package com.niche.ng.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Zonal.class)
public abstract class Zonal_ {

	public static volatile SingularAttribute<Zonal, Instant> createdAt;
	public static volatile SetAttribute<Zonal, Sector> sectors;
	public static volatile SingularAttribute<Zonal, Long> createdBy;
	public static volatile SingularAttribute<Zonal, Long> modifiedBy;
	public static volatile SingularAttribute<Zonal, Long> id;
	public static volatile SingularAttribute<Zonal, String> zoneName;
	public static volatile SingularAttribute<Zonal, String> zoneAddress;
	public static volatile SingularAttribute<Zonal, String> zoneIncharge;
	public static volatile SingularAttribute<Zonal, Integer> status;
	public static volatile SingularAttribute<Zonal, Instant> updatedAt;

}

