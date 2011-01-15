package org.witchcraft.base.entity;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;



@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidatorClass(UniqueValidator.class)

public @interface Unique { 
	String message() default " already exists";

	String entityName();

	String fieldName();
	
	String idProvider();
}