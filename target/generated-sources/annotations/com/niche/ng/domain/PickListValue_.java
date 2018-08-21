package com.niche.ng.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PickListValue.class)
public abstract class PickListValue_ {

	public static volatile SetAttribute<PickListValue, Batch> categorys;
	public static volatile SingularAttribute<PickListValue, PickListValue> pickValue;
	public static volatile SetAttribute<PickListValue, GodownStock> godownStockCategorys;
	public static volatile SetAttribute<PickListValue, Nursery> nurserys;
	public static volatile SetAttribute<PickListValue, GodownPurchaseDetails> godownPurchaseCategorys;
	public static volatile SetAttribute<PickListValue, PickListValue> selfIds;
	public static volatile SetAttribute<PickListValue, Batch> varietys;
	public static volatile SetAttribute<PickListValue, GodownPurchaseDetails> godownPurchaseQuantityTypes;
	public static volatile SetAttribute<PickListValue, GodownStock> godownStockVarietys;
	public static volatile SetAttribute<PickListValue, GodownStock> godownStockQuantityTypes;
	public static volatile SingularAttribute<PickListValue, Instant> createdAt;
	public static volatile SetAttribute<PickListValue, GodownPurchaseDetails> godownPurchaseVarietys;
	public static volatile SingularAttribute<PickListValue, String> pickListValue;
	public static volatile SingularAttribute<PickListValue, Long> createdBy;
	public static volatile SetAttribute<PickListValue, Batch> batchQuantityTypes;
	public static volatile SingularAttribute<PickListValue, PickList> pickList;
	public static volatile SetAttribute<PickListValue, NurseryStock> nurseryStockVarietys;
	public static volatile SingularAttribute<PickListValue, Long> modifiedBy;
	public static volatile SingularAttribute<PickListValue, Long> id;
	public static volatile SetAttribute<PickListValue, NurseryStock> nurseryStockCategorys;
	public static volatile SingularAttribute<PickListValue, Integer> status;
	public static volatile SingularAttribute<PickListValue, Instant> updatedAt;

}

