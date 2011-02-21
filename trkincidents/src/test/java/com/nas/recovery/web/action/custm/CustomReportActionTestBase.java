package com.nas.recovery.web.action.custm;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.trkincidents.custm.CustomReport;

public class CustomReportActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<CustomReport> {

	CustomReportAction customReportAction = new CustomReportAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<CustomReport> getAction() {
		return customReportAction;
	}

}
