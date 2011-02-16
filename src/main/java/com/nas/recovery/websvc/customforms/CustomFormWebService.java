package com.nas.recovery.websvc.customforms;

import javax.jws.WebService;
import com.oreon.trkincidents.customforms.CustomForm;
import java.util.List;

@WebService
public interface CustomFormWebService {

	public CustomForm loadById(Long id);

	public List<CustomForm> findByExample(CustomForm exampleCustomForm);

	public void save(CustomForm customForm);

}
