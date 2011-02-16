package com.nas.recovery.websvc.incidents;

import javax.jws.WebService;
import com.oreon.trkincidents.incidents.Proccedure;
import java.util.List;

@WebService
public interface ProccedureWebService {

	public Proccedure loadById(Long id);

	public List<Proccedure> findByExample(Proccedure exampleProccedure);

	public void save(Proccedure proccedure);

}
