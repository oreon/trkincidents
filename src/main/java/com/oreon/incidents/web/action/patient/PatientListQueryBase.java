package com.oreon.incidents.web.action.patient;

import com.oreon.incidents.patient.Patient;

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

import java.math.BigDecimal;

import com.oreon.incidents.patient.Patient;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PatientListQueryBase extends BaseQuery<Patient, Long> {

	private static final String EJBQL = "select patient from Patient patient";

	protected Patient patient = new Patient();

	public Patient getPatient() {
		return patient;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Patient> getEntityClass() {
		return Patient.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateOfBirthRange = new Range<Date>();

	public Range<Date> getDateOfBirthRange() {
		return dateOfBirthRange;
	}
	public void setDateOfBirth(Range<Date> dateOfBirthRange) {
		this.dateOfBirthRange = dateOfBirthRange;
	}

	private Range<Integer> ageRange = new Range<Integer>();

	public Range<Integer> getAgeRange() {
		return ageRange;
	}
	public void setAge(Range<Integer> ageRange) {
		this.ageRange = ageRange;
	}

	private static final String[] RESTRICTIONS = {
			"patient.id = #{patientList.patient.id}",

			"lower(patient.firstName) like concat(lower(#{patientList.patient.firstName}),'%')",

			"lower(patient.lastName) like concat(lower(#{patientList.patient.lastName}),'%')",

			"lower(patient.address.streetAddress) like concat(lower(#{patientList.patient.address.streetAddress}),'%')",

			"lower(patient.address.city) like concat(lower(#{patientList.patient.address.city}),'%')",

			"lower(patient.address.state) like concat(lower(#{patientList.patient.address.state}),'%')",

			"lower(patient.address.phone) like concat(lower(#{patientList.patient.address.phone}),'%')",

			"lower(patient.healthNumber) like concat(lower(#{patientList.patient.healthNumber}),'%')",

			"patient.dateOfBirth >= #{patientList.dateOfBirthRange.begin}",
			"patient.dateOfBirth <= #{patientList.dateOfBirthRange.end}",

			"patient.gender = #{patientList.patient.gender}",

			"patient.age >= #{patientList.ageRange.begin}",
			"patient.age <= #{patientList.ageRange.end}",

			"patient.dateCreated <= #{patientList.dateCreatedRange.end}",
			"patient.dateCreated >= #{patientList.dateCreatedRange.begin}",};

	@Observer("archivedPatient")
	public void onArchive() {
		refresh();
	}

	@Override
	protected void setupForAutoComplete(String input) {

		patient.setFirstName(input);

		patient.setLastName(input);

		patient.getAddress().setPhone(input);

	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Patient e) {

		builder.append("\"" + (e.getAddress() != null ? e.getAddress() : "")
				+ "\",");

		builder.append("\""
				+ (e.getHealthNumber() != null ? e.getHealthNumber().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getDateOfBirth() != null ? e.getDateOfBirth() : "")
				+ "\",");

		builder.append("\"" + (e.getGender() != null ? e.getGender() : "")
				+ "\",");

		builder.append("\"" + (e.getAge() != null ? e.getAge() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Address" + ",");

		builder.append("HealthNumber" + ",");

		builder.append("DateOfBirth" + ",");

		builder.append("Gender" + ",");

		builder.append("Age" + ",");

		builder.append("\r\n");
	}
}
