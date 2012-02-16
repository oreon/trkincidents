package com.nas.recovery.web.action.reports;

import org.jboss.seam.Component;
import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

import com.oreon.incidents.web.action.reports.IncidentsByDepartmentAction;

public class IncidentsByDepartmentTest extends BaseReportsTest {

	@Test
	public void testIncidentsByDepartmentReport() {
		try {
			runReportTest("IncidentsByDepartment");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//@Test
	public void testIncidentsByDepartmentReportAction() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				IncidentsByDepartmentAction incidentsByDepartmentAction = (IncidentsByDepartmentAction) Component
						.getInstance("incidentsByDepartmentAction");
				incidentsByDepartmentAction.runReport("IncidentsByDepartment",
						BaseReportAction.REPORT_TYPE.LOCAL.name());
			}
		}.run();
	}

}
