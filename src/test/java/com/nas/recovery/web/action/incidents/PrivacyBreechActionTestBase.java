package com.nas.recovery.web.action.incidents;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.trkincidents.incidents.PrivacyBreech;

public class PrivacyBreechActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PrivacyBreech> {

	PrivacyBreechAction privacyBreechAction = new PrivacyBreechAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<PrivacyBreech> getAction() {
		return privacyBreechAction;
	}

}
