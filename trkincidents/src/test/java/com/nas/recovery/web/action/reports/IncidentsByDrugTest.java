package com.nas.recovery.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class IncidentsByDrugTest extends BaseReportsTest {

	@Test
	public void testIncidentsByDrugReport() {
		try {
			runReportTest("IncidentsByDrug");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
