package com.nas.recovery.websvc.customReports;

import javax.jws.WebService;
import com.oreon.trkincidents.customReports.MetaField;
import java.util.List;

@WebService
public interface MetaFieldWebService {

	public MetaField loadById(Long id);

	public List<MetaField> findByExample(MetaField exampleMetaField);

	public void save(MetaField metaField);

}
