package com.oreon.incidents.web.action.incidents;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.incidents.incidents.FormField;

public class FormFieldActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<FormField> {

	FormFieldAction formFieldAction = new FormFieldAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<FormField> getAction() {
		return formFieldAction;
	}

}
