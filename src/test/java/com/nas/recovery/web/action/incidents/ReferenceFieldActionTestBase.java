package com.nas.recovery.web.action.incidents;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.trkincidents.incidents.ReferenceField;

public class ReferenceFieldActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ReferenceField> {

	ReferenceFieldAction referenceFieldAction = new ReferenceFieldAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ReferenceField> getAction() {
		return referenceFieldAction;
	}

}
