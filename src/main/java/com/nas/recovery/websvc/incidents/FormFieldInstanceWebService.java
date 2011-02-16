package com.nas.recovery.websvc.incidents;

import javax.jws.WebService;
import com.oreon.trkincidents.incidents.FormFieldInstance;
import java.util.List;

@WebService
public interface FormFieldInstanceWebService {

	public FormFieldInstance loadById(Long id);

	public List<FormFieldInstance> findByExample(
			FormFieldInstance exampleFormFieldInstance);

	public void save(FormFieldInstance formFieldInstance);

}
