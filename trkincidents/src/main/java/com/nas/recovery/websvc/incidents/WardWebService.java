package com.nas.recovery.websvc.incidents;

import javax.jws.WebService;
import com.oreon.trkincidents.incidents.Ward;
import java.util.List;

@WebService
public interface WardWebService {

	public Ward loadById(Long id);

	public List<Ward> findByExample(Ward exampleWard);

	public void save(Ward ward);

}
