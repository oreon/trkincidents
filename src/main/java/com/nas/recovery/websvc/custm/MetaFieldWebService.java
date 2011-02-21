package com.nas.recovery.websvc.custm;

import javax.jws.WebService;
import com.oreon.trkincidents.custm.MetaField;
import java.util.List;

@WebService
public interface MetaFieldWebService {

	public MetaField loadById(Long id);

	public List<MetaField> findByExample(MetaField exampleMetaField);

	public void save(MetaField metaField);

}
