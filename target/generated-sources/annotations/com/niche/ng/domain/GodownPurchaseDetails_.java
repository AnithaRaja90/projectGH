package com.niche.ng.domain;

import java.time.Instant;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GodownPurchaseDetails.class)
public abstract class GodownPurchaseDetails_ {

	public static volatile SingularAttribute<GodownPurchaseDetails, LocalDate> date;
	public static volatile SingularAttribute<GodownPurchaseDetails, PickListValue> pickListCategory;
	public static volatile SingularAttribute<GodownPurchaseDetails, Long> quantity;
	public static volatile SingularAttribute<GodownPurchaseDetails, Godown> godown;
	public static volatile SingularAttribute<GodownPurchaseDetails, String> description;
	public static volatile SingularAttribute<GodownPurchaseDetails, PickListValue> pickListVariety;
	public static volatile SingularAttribute<GodownPurchaseDetails, String> vendorName;
	public static volatile SingularAttribute<GodownPurchaseDetails, Instant> createdAt;
	public static volatile SingularAttribute<GodownPurchaseDetails, Long> vendorPhone;
	public static volatile SingularAttribute<GodownPurchaseDetails, PickListValue> pickListQuantityType;
	public static volatile SingularAttribute<GodownPurchaseDetails, Long> createdBy;
	public static volatile SingularAttribute<GodownPurchaseDetails, Long> price;
	public static volatile SingularAttribute<GodownPurchaseDetails, Long> modifiedBy;
	public static volatile SingularAttribute<GodownPurchaseDetails, Long> id;
	public static volatile SingularAttribute<GodownPurchaseDetails, String> ownedBy;
	public static volatile SingularAttribute<GodownPurchaseDetails, String> vendorAddress;
	public static volatile SingularAttribute<GodownPurchaseDetails, Integer> status;
	public static volatile SingularAttribute<GodownPurchaseDetails, Instant> updatedAt;

}

