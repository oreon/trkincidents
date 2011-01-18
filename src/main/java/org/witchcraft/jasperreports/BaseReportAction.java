package org.witchcraft.jasperreports;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.log.Log;
import org.witchcraft.exceptions.ContractViolationException;

public abstract class BaseReportAction {

	@In
	// @PersistenceContext(type=EXTENDED)
	protected FullTextEntityManager entityManager;
	
	@In
	protected StatusMessages statusMessages;
	
	@Logger
	Log log;

	public FullTextEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(FullTextEntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// ///// Jasper Reports //////////////////////////////////////////////
	public void runReport(String reportName) {

		JasperReport jasperReport;

		Map<String, Object> parameters = new HashMap<String, Object>();
		updateParameters(parameters);
		parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER,
				getEntityManager());

		try {
			InputStream reportStreamXML = this.getClass().getResourceAsStream(
					"/reports/" + reportName + ".jrxml");
			jasperReport = JasperCompileManager.compileReport(reportStreamXML);
			// new JRHtmlExporter().exportReport()
			sendReportToPdf(jasperReport, parameters);
			// JasperRunManager.runReportToHtmlFile(jasperReport, parameters);
		} catch (Exception e) {
			statusMessages.add(Severity.ERROR, "Error Running Report: " + e.getMessage() );
		}
	}

	public void sendReportToPdf(JasperReport jasperReport,
			Map<String, Object> parameters) {
		try {
			byte[] bytes = JasperRunManager.runReportToPdf(jasperReport,
					parameters);
			HttpServletResponse response = (HttpServletResponse) javax.faces.context.FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition",
					"attachment;filename=Report.pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream servletOutputStream = response
					.getOutputStream();
			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();
			javax.faces.context.FacesContext.getCurrentInstance()
					.responseComplete();
		} catch (Exception e) {
			log.error("running report " , e);
			throw new ContractViolationException(e.getMessage());
		}

	}
	
	public abstract void updateParameters(Map map);

}
