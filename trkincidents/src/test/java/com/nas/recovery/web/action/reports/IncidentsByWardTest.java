package com.nas.recovery.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class IncidentsByWardTest extends BaseReportsTest {

	@Test
	public void testIncidentsByWardReport() {
		try {
			runReportTest("IncidentsByWard");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
