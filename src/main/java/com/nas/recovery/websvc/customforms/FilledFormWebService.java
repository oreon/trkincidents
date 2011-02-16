package com.nas.recovery.websvc.customforms;

import javax.jws.WebService;
import com.oreon.trkincidents.customforms.FilledForm;
import java.util.List;

@WebService
public interface FilledFormWebService {

	public FilledForm loadById(Long id);

	public List<FilledForm> findByExample(FilledForm exampleFilledForm);

	public void save(FilledForm filledForm);

}
