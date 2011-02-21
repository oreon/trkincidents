package com.nas.recovery.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class IncidentsByMonthTest extends BaseReportsTest {

	@Test
	public void testIncidentsByMonthReport() {
		try {
			runReportTest("IncidentsByMonth");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
