package com.niche.ng.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PickList.class)
public abstract class PickList_ {

	public static volatile SingularAttribute<PickList, Instant> createdAt;
	public static volatile SetAttribute<PickList, PickListValue> pickListValues;
	public static volatile SingularAttribute<PickList, Long> createdBy;
	public static volatile SingularAttribute<PickList, String> pickListName;
	public static volatile SingularAttribute<PickList, Long> modifiedBy;
	public static volatile SingularAttribute<PickList, Long> id;
	public static volatile SingularAttribute<PickList, Integer> status;
	public static volatile SingularAttribute<PickList, Instant> updatedAt;

}

