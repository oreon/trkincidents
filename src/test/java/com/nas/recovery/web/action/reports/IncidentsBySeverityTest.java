package com.nas.recovery.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class IncidentsBySeverityTest extends BaseReportsTest {

	@Test
	public void testIncidentsBySeverityReport() {
		try {
			runReportTest("IncidentsBySeverity");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
