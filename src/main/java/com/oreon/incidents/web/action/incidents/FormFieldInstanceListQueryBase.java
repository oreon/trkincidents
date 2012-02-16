package com.oreon.incidents.web.action.incidents;

import com.oreon.incidents.incidents.FormFieldInstance;

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

import com.oreon.incidents.incidents.FormFieldInstance;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FormFieldInstanceListQueryBase
		extends
			BaseQuery<FormFieldInstance, Long> {

	private static final String EJBQL = "select formFieldInstance from FormFieldInstance formFieldInstance";

	protected FormFieldInstance formFieldInstance = new FormFieldInstance();

	public FormFieldInstance getFormFieldInstance() {
		return formFieldInstance;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<FormFieldInstance> getEntityClass() {
		return FormFieldInstance.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateValueRange = new Range<Date>();

	public Range<Date> getDateValueRange() {
		return dateValueRange;
	}
	public void setDateValue(Range<Date> dateValueRange) {
		this.dateValueRange = dateValueRange;
	}

	private Range<Integer> enumOrdinalRange = new Range<Integer>();

	public Range<Integer> getEnumOrdinalRange() {
		return enumOrdinalRange;
	}
	public void setEnumOrdinal(Range<Integer> enumOrdinalRange) {
		this.enumOrdinalRange = enumOrdinalRange;
	}

	private static final String[] RESTRICTIONS = {
			"formFieldInstance.id = #{formFieldInstanceList.formFieldInstance.id}",

			"lower(formFieldInstance.value) like concat(lower(#{formFieldInstanceList.formFieldInstance.value}),'%')",

			"formFieldInstance.formField.id = #{formFieldInstanceList.formFieldInstance.formField.id}",

			"formFieldInstance.boolValue = #{formFieldInstanceList.formFieldInstance.boolValue}",

			"formFieldInstance.dateValue >= #{formFieldInstanceList.dateValueRange.begin}",
			"formFieldInstance.dateValue <= #{formFieldInstanceList.dateValueRange.end}",

			"formFieldInstance.enumOrdinal >= #{formFieldInstanceList.enumOrdinalRange.begin}",
			"formFieldInstance.enumOrdinal <= #{formFieldInstanceList.enumOrdinalRange.end}",

			"lower(formFieldInstance.description) like concat(lower(#{formFieldInstanceList.formFieldInstance.description}),'%')",

			"formFieldInstance.incident.id = #{formFieldInstanceList.formFieldInstance.incident.id}",

			"formFieldInstance.dateCreated <= #{formFieldInstanceList.dateCreatedRange.end}",
			"formFieldInstance.dateCreated >= #{formFieldInstanceList.dateCreatedRange.begin}",};

	public List<FormFieldInstance> getFormFieldInstancesByIncident(
			com.oreon.incidents.incidents.Incident incident) {
		//setMaxResults(10000);
		formFieldInstance.setIncident(incident);
		return getResultList();
	}

	@Observer("archivedFormFieldInstance")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, FormFieldInstance e) {

		builder.append("\""
				+ (e.getValue() != null ? e.getValue().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getFormField() != null ? e.getFormField().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getBoolValue() != null ? e.getBoolValue() : "") + "\",");

		builder.append("\""
				+ (e.getDateValue() != null ? e.getDateValue() : "") + "\",");

		builder.append("\""
				+ (e.getEnumOrdinal() != null ? e.getEnumOrdinal() : "")
				+ "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getIncident() != null ? e.getIncident().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Value" + ",");

		builder.append("FormField" + ",");

		builder.append("BoolValue" + ",");

		builder.append("DateValue" + ",");

		builder.append("EnumOrdinal" + ",");

		builder.append("Description" + ",");

		builder.append("Incident" + ",");

		builder.append("\r\n");
	}
}
