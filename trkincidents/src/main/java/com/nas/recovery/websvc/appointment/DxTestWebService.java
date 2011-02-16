package com.nas.recovery.websvc.appointment;

import javax.jws.WebService;
import com.oreon.trkincidents.appointment.DxTest;
import java.util.List;

@WebService
public interface DxTestWebService {

	public DxTest loadById(Long id);

	public List<DxTest> findByExample(DxTest exampleDxTest);

	public void save(DxTest dxTest);

}
