package com.nas.recovery.web.action.customReports;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.customReports.CustomReport;
import com.oreon.incidents.web.action.customReports.CustomReportAction;

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
