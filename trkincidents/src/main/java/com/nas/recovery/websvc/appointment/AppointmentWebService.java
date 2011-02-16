package com.nas.recovery.websvc.appointment;

import javax.jws.WebService;
import com.oreon.trkincidents.appointment.Appointment;
import java.util.List;

@WebService
public interface AppointmentWebService {

	public Appointment loadById(Long id);

	public List<Appointment> findByExample(Appointment exampleAppointment);

	public void save(Appointment appointment);

}
