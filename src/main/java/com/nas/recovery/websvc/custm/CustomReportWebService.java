package com.nas.recovery.websvc.custm;

import javax.jws.WebService;
import com.oreon.trkincidents.custm.CustomReport;
import java.util.List;

@WebService
public interface CustomReportWebService {

	public CustomReport loadById(Long id);

	public List<CustomReport> findByExample(CustomReport exampleCustomReport);

	public void save(CustomReport customReport);

}
