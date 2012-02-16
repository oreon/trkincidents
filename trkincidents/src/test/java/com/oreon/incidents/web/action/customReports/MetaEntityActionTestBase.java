package com.oreon.incidents.web.action.customReports;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.incidents.customReports.MetaEntity;

public class MetaEntityActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<MetaEntity> {

	MetaEntityAction metaEntityAction = new MetaEntityAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<MetaEntity> getAction() {
		return metaEntityAction;
	}

}
