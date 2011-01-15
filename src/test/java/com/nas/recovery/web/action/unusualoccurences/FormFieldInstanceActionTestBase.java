package com.nas.recovery.web.action.unusualoccurences;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.trkincidents.unusualoccurences.FormFieldInstance;

public class FormFieldInstanceActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<FormFieldInstance> {

	FormFieldInstanceAction formFieldInstanceAction = new FormFieldInstanceAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<FormFieldInstance> getAction() {
		return formFieldInstanceAction;
	}

}
