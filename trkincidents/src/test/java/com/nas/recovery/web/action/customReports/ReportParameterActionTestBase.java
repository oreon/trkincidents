package com.nas.recovery.web.action.customReports;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.customReports.ReportParameter;
import com.oreon.incidents.web.action.customReports.ReportParameterAction;

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
