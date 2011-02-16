package com.nas.recovery.websvc.appointment;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.appointment.Appointment;

@WebService(endpointInterface = "com.nas.recovery.websvc.appointment.AppointmentWebService", serviceName = "AppointmentWebService")
@Name("appointmentWebService")
public class AppointmentWebServiceImpl implements AppointmentWebService {

	@In(create = true)
	com.nas.recovery.web.action.appointment.AppointmentAction appointmentAction;

	public Appointment loadById(Long id) {
		return appointmentAction.loadFromId(id);
	}

	public List<Appointment> findByExample(Appointment exampleAppointment) {
		return appointmentAction.search(exampleAppointment);
	}

	public void save(Appointment appointment) {
		appointmentAction.persist(appointment);
	}

}
