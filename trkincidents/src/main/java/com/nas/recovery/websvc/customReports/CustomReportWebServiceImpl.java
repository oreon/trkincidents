package com.nas.recovery.websvc.customReports;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.customReports.CustomReport;

@WebService(endpointInterface = "com.nas.recovery.websvc.customReports.CustomReportWebService", serviceName = "CustomReportWebService")
@Name("customReportWebService")
public class CustomReportWebServiceImpl implements CustomReportWebService {

	@In(create = true)
	com.nas.recovery.web.action.customReports.CustomReportAction customReportAction;

	public CustomReport loadById(Long id) {
		return customReportAction.loadFromId(id);
	}

	public List<CustomReport> findByExample(CustomReport exampleCustomReport) {
		return customReportAction.search(exampleCustomReport);
	}

	public void save(CustomReport customReport) {
		customReportAction.persist(customReport);
	}

}
