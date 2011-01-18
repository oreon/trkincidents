package org.witchcraft.action.test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseReportsTest extends BaseTest<Object> {

	protected JasperReport jasperReport;
	protected JasperPrint jasperPrint;

	@BeforeClass
	public void init() {
		super.init();
	}

	// @Test
	public void runReportTest(String reportName) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER,
				em);

		InputStream reportStreamXML = this.getClass().getResourceAsStream(
				"/reports/" + reportName + ".jrxml");

		jasperReport = JasperCompileManager.compileReport(reportStreamXML);
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"src/main/resources/reports/" + reportName + ".pdf");

	}

	@Test
	public void testIncidents() {
		try {
			runReportTest("IncidentsByDepartment");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Object getAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
