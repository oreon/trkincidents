package com.oreon.incidents.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

import org.jboss.seam.Component;

import com.oreon.incidents.web.action.reports.IncidentsByWardAction;

public class IncidentsByWardTest extends BaseReportsTest {

	@Test
	public void testIncidentsByWardReport() {
		try {
			runReportTest("IncidentsByWard");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//@Test
	public void testIncidentsByWardReportAction() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				IncidentsByWardAction incidentsByWardAction = (IncidentsByWardAction) Component
						.getInstance("incidentsByWardAction");
				incidentsByWardAction.runReport("IncidentsByWard",
						BaseReportAction.REPORT_TYPE.LOCAL.name());
			}
		}.run();
	}

}
