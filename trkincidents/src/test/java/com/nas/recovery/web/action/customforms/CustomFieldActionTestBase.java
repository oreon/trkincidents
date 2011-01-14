package com.nas.recovery.web.action.customforms;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.trkincidents.customforms.CustomField;

public class CustomFieldActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<CustomField> {

	CustomFieldAction customFieldAction = new CustomFieldAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<CustomField> getAction() {
		return customFieldAction;
	}

}
