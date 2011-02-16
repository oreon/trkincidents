package com.nas.recovery.websvc.customforms;

import javax.jws.WebService;
import com.oreon.trkincidents.customforms.FilledField;
import java.util.List;

@WebService
public interface FilledFieldWebService {

	public FilledField loadById(Long id);

	public List<FilledField> findByExample(FilledField exampleFilledField);

	public void save(FilledField filledField);

}
