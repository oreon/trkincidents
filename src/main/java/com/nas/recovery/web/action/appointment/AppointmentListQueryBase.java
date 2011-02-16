package com.nas.recovery.web.action.appointment;

import com.oreon.trkincidents.appointment.Appointment;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.oreon.trkincidents.appointment.Appointment;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AppointmentListQueryBase
		extends
			BaseQuery<Appointment, Long> {

	private static final String EJBQL = "select appointment from Appointment appointment";

	protected Appointment appointment = new Appointment();

	public Appointment getAppointment() {
		return appointment;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Appointment> getEntityClass() {
		return Appointment.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> startRange = new Range<Date>();
	public Range<Date> getStartRange() {
		return startRange;
	}
	public void setStart(Range<Date> startRange) {
		this.startRange = startRange;
	}

	private Range<Date> endRange = new Range<Date>();
	public Range<Date> getEndRange() {
		return endRange;
	}
	public void setEnd(Range<Date> endRange) {
		this.endRange = endRange;
	}

	private static final String[] RESTRICTIONS = {
			"appointment.id = #{appointmentList.appointment.id}",

			"appointment.start >= #{appointmentList.startRange.begin}",
			"appointment.start <= #{appointmentList.startRange.end}",

			"appointment.end >= #{appointmentList.endRange.begin}",
			"appointment.end <= #{appointmentList.endRange.end}",

			"appointment.patient.id = #{appointmentList.appointment.patient.id}",

			"lower(appointment.remarks) like concat(lower(#{appointmentList.appointment.remarks}),'%')",

			"appointment.dateCreated <= #{appointmentList.dateCreatedRange.end}",
			"appointment.dateCreated >= #{appointmentList.dateCreatedRange.begin}",};

	@Observer("archivedAppointment")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Appointment e) {

		builder.append("\"" + (e.getStart() != null ? e.getStart() : "")
				+ "\",");

		builder.append("\"" + (e.getEnd() != null ? e.getEnd() : "") + "\",");

		builder.append("\""
				+ (e.getPatient() != null
						? e.getPatient().getDisplayName()
						: "") + "\",");

		builder.append("\"" + (e.getRemarks() != null ? e.getRemarks() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Start" + ",");

		builder.append("End" + ",");

		builder.append("Patient" + ",");

		builder.append("Remarks" + ",");

		builder.append("\r\n");
	}
}
