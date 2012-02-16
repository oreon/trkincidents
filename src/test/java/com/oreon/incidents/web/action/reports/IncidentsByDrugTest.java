package com.oreon.incidents.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

import org.jboss.seam.Component;

import com.oreon.incidents.web.action.reports.IncidentsByDrugAction;

public class IncidentsByDrugTest extends BaseReportsTest {

	@Test
	public void testIncidentsByDrugReport() {
		try {
			runReportTest("IncidentsByDrug");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//@Test
	public void testIncidentsByDrugReportAction() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				IncidentsByDrugAction incidentsByDrugAction = (IncidentsByDrugAction) Component
						.getInstance("incidentsByDrugAction");
				incidentsByDrugAction.runReport("IncidentsByDrug",
						BaseReportAction.REPORT_TYPE.LOCAL.name());
			}
		}.run();
	}

}
