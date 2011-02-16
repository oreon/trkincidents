package com.nas.recovery.websvc.incidents;

import javax.jws.WebService;
import com.oreon.trkincidents.incidents.FormField;
import java.util.List;

@WebService
public interface FormFieldWebService {

	public FormField loadById(Long id);

	public List<FormField> findByExample(FormField exampleFormField);

	public void save(FormField formField);

}
