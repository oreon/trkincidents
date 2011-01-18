package com.nas.recovery.web.action.employee;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

public class InicidentReportTest extends BaseReportsTest{
	
	@Test
	public void testIncidents() {
		try {
			runReportTest("IncidentsByDepartment");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
