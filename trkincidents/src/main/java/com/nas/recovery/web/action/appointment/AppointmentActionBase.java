package com.nas.recovery.web.action.appointment;

import com.oreon.trkincidents.appointment.Appointment;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public abstract class AppointmentActionBase extends BaseAction<Appointment>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Appointment appointment;

	@In(create = true, value = "patientAction")
	com.nas.recovery.web.action.patient.PatientAction patientAction;

	@DataModel
	private List<Appointment> appointmentRecordList;

	public void setAppointmentId(Long id) {
		if (id == 0) {
			clearInstance();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setAppointmentIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public void setPatientId(Long id) {

		if (id != null && id > 0)
			getInstance().setPatient(patientAction.loadFromId(id));

	}

	public Long getPatientId() {
		if (getInstance().getPatient() != null)
			return getInstance().getPatient().getId();
		return 0L;
	}

	public Long getAppointmentId() {
		return (Long) getId();
	}

	public Appointment getEntity() {
		return appointment;
	}

	//@Override
	public void setEntity(Appointment t) {
		this.appointment = t;
		loadAssociations();
	}

	public Appointment getAppointment() {
		return (Appointment) getInstance();
	}

	@Override
	protected Appointment createInstance() {
		return new Appointment();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.trkincidents.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Appointment getDefinedInstance() {
		return (Appointment) (isIdDefined() ? getInstance() : null);
	}

	public void setAppointment(Appointment t) {
		this.appointment = t;
		loadAssociations();
	}

	@Override
	public Class<Appointment> getEntityClass() {
		return Appointment.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (appointment.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", appointment
					.getPatient().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (appointment.getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
