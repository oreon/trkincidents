package com.nas.recovery.websvc.customReports;

import javax.jws.WebService;
import com.oreon.trkincidents.customReports.MetaEntity;
import java.util.List;

@WebService
public interface MetaEntityWebService {

	public MetaEntity loadById(Long id);

	public List<MetaEntity> findByExample(MetaEntity exampleMetaEntity);

	public void save(MetaEntity metaEntity);

}
