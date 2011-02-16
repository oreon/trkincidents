package com.nas.recovery.web.action.customforms;

import com.oreon.trkincidents.customforms.FilledField;

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

import com.oreon.trkincidents.customforms.FilledField;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FilledFieldListQueryBase
		extends
			BaseQuery<FilledField, Long> {

	private static final String EJBQL = "select filledField from FilledField filledField";

	protected FilledField filledField = new FilledField();

	public FilledField getFilledField() {
		return filledField;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<FilledField> getEntityClass() {
		return FilledField.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"filledField.id = #{filledFieldList.filledField.id}",

			"filledField.customField.id = #{filledFieldList.filledField.customField.id}",

			"filledField.filledForm.id = #{filledFieldList.filledField.filledForm.id}",

			"lower(filledField.value) like concat(lower(#{filledFieldList.filledField.value}),'%')",

			"filledField.dateCreated <= #{filledFieldList.dateCreatedRange.end}",
			"filledField.dateCreated >= #{filledFieldList.dateCreatedRange.begin}",};

	public List<FilledField> getFilledFieldsByFilledForm(
			com.oreon.trkincidents.customforms.FilledForm filledForm) {
		//setMaxResults(10000);
		filledField.setFilledForm(filledForm);
		return getResultList();
	}

	@Observer("archivedFilledField")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, FilledField e) {

		builder.append("\""
				+ (e.getCustomField() != null ? e.getCustomField()
						.getDisplayName() : "") + "\",");

		builder.append("\""
				+ (e.getFilledForm() != null ? e.getFilledForm()
						.getDisplayName() : "") + "\",");

		builder.append("\"" + (e.getValue() != null ? e.getValue() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("CustomField" + ",");

		builder.append("FilledForm" + ",");

		builder.append("Value" + ",");

		builder.append("\r\n");
	}
}
