package com.nas.recovery.web.action.customforms;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.trkincidents.customforms.CustomForm;

public class CustomFormActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<CustomForm> {

	CustomFormAction customFormAction = new CustomFormAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<CustomForm> getAction() {
		return customFormAction;
	}

}
