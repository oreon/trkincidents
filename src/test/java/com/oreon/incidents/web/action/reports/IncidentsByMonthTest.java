package com.oreon.incidents.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

import org.jboss.seam.Component;

import com.oreon.incidents.web.action.reports.IncidentsByMonthAction;

public class IncidentsByMonthTest extends BaseReportsTest {

	@Test
	public void testIncidentsByMonthReport() {
		try {
			runReportTest("IncidentsByMonth");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//@Test
	public void testIncidentsByMonthReportAction() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				IncidentsByMonthAction incidentsByMonthAction = (IncidentsByMonthAction) Component
						.getInstance("incidentsByMonthAction");
				incidentsByMonthAction.runReport("IncidentsByMonth",
						BaseReportAction.REPORT_TYPE.LOCAL.name());
			}
		}.run();
	}

}
