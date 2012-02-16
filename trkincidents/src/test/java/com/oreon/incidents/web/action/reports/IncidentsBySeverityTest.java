package com.oreon.incidents.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

import org.jboss.seam.Component;

import com.oreon.incidents.web.action.reports.IncidentsBySeverityAction;

public class IncidentsBySeverityTest extends BaseReportsTest {

	@Test
	public void testIncidentsBySeverityReport() {
		try {
			runReportTest("IncidentsBySeverity");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//@Test
	public void testIncidentsBySeverityReportAction() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				IncidentsBySeverityAction incidentsBySeverityAction = (IncidentsBySeverityAction) Component
						.getInstance("incidentsBySeverityAction");
				incidentsBySeverityAction.runReport("IncidentsBySeverity",
						BaseReportAction.REPORT_TYPE.LOCAL.name());
			}
		}.run();
	}

}
