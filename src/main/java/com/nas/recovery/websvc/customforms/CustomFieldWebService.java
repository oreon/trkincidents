package com.nas.recovery.websvc.customforms;

import javax.jws.WebService;
import com.oreon.trkincidents.customforms.CustomField;
import java.util.List;

@WebService
public interface CustomFieldWebService {

	public CustomField loadById(Long id);

	public List<CustomField> findByExample(CustomField exampleCustomField);

	public void save(CustomField customField);

}
