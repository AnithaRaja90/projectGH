package com.niche.ng.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Godown.class)
public abstract class Godown_ {

	public static volatile SingularAttribute<Godown, Instant> createdAt;
	public static volatile SetAttribute<Godown, GodownPurchaseDetails> godownPurchaseDetails;
	public static volatile SingularAttribute<Godown, String> address;
	public static volatile SingularAttribute<Godown, Long> createdBy;
	public static volatile SetAttribute<Godown, GodownStock> godownStocks;
	public static volatile SingularAttribute<Godown, String> name;
	public static volatile SingularAttribute<Godown, String> incharge;
	public static volatile SingularAttribute<Godown, Long> modifiedBy;
	public static volatile SingularAttribute<Godown, Long> id;
	public static volatile SingularAttribute<Godown, Integer> status;
	public static volatile SingularAttribute<Godown, Instant> updatedAt;

}

