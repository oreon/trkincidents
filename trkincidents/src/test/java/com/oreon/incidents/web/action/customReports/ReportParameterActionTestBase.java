package com.oreon.incidents.web.action.customReports;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.incidents.customReports.ReportParameter;

public class ReportParameterActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ReportParameter> {

	ReportParameterAction reportParameterAction = new ReportParameterAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ReportParameter> getAction() {
		return reportParameterAction;
	}

}
