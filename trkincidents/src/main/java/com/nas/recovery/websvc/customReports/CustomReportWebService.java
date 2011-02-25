package com.nas.recovery.websvc.customReports;

import javax.jws.WebService;
import com.oreon.trkincidents.customReports.CustomReport;
import java.util.List;

@WebService
public interface CustomReportWebService {

	public CustomReport loadById(Long id);

	public List<CustomReport> findByExample(CustomReport exampleCustomReport);

	public void save(CustomReport customReport);

}
