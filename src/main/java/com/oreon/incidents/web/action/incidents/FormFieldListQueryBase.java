package com.oreon.incidents.web.action.incidents;

import com.oreon.incidents.incidents.FormField;

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

import com.oreon.incidents.incidents.FormField;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FormFieldListQueryBase extends BaseQuery<FormField, Long> {

	private static final String EJBQL = "select formField from FormField formField";

	protected FormField formField = new FormField();

	public FormField getFormField() {
		return formField;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<FormField> getEntityClass() {
		return FormField.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"formField.id = #{formFieldList.formField.id}",

			"lower(formField.name) like concat(lower(#{formFieldList.formField.name}),'%')",

			"formField.type = #{formFieldList.formField.type}",

			"formField.required = #{formFieldList.formField.required}",

			"lower(formField.choiceValues) like concat(lower(#{formFieldList.formField.choiceValues}),'%')",

			"formField.incidentType.id = #{formFieldList.formField.incidentType.id}",

			"formField.dateCreated <= #{formFieldList.dateCreatedRange.end}",
			"formField.dateCreated >= #{formFieldList.dateCreatedRange.begin}",};

	public List<FormField> getFormFieldsByIncidentType(
			com.oreon.incidents.incidents.IncidentType incidentType) {
		//setMaxResults(10000);
		formField.setIncidentType(incidentType);
		return getResultList();
	}

	@Observer("archivedFormField")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, FormField e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getType() != null ? e.getType() : "") + "\",");

		builder.append("\"" + (e.getRequired() != null ? e.getRequired() : "")
				+ "\",");

		builder.append("\""
				+ (e.getChoiceValues() != null ? e.getChoiceValues().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getIncidentType() != null ? e.getIncidentType()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Type" + ",");

		builder.append("Required" + ",");

		builder.append("ChoiceValues" + ",");

		builder.append("IncidentType" + ",");

		builder.append("\r\n");
	}
}
