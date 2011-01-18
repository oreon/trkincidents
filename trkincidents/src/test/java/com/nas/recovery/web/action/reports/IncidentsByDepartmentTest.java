package com.nas.recovery.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class IncidentsByDepartmentTest extends BaseReportsTest {

	@Test
	public void testIncidentsByDepartmentReport() {
		try {
			runReportTest("IncidentsByDepartment");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
