package com.nas.recovery.websvc.incidents;

import javax.jws.WebService;
import com.oreon.trkincidents.incidents.ReferenceField;
import java.util.List;

@WebService
public interface ReferenceFieldWebService {

	public ReferenceField loadById(Long id);

	public List<ReferenceField> findByExample(
			ReferenceField exampleReferenceField);

	public void save(ReferenceField referenceField);

}
