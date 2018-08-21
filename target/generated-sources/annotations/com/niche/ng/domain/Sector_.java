package com.niche.ng.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sector.class)
public abstract class Sector_ {

	public static volatile SingularAttribute<Sector, Instant> createdAt;
	public static volatile SingularAttribute<Sector, String> sectorAddress;
	public static volatile SingularAttribute<Sector, Long> createdBy;
	public static volatile SetAttribute<Sector, Nursery> nurserys;
	public static volatile SingularAttribute<Sector, Long> modifiedBy;
	public static volatile SingularAttribute<Sector, Long> id;
	public static volatile SingularAttribute<Sector, String> sectorName;
	public static volatile SingularAttribute<Sector, String> sectorIncharge;
	public static volatile SingularAttribute<Sector, Integer> status;
	public static volatile SingularAttribute<Sector, Instant> updatedAt;
	public static volatile SingularAttribute<Sector, Zonal> zonal;

}

