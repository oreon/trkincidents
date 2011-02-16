package com.nas.recovery.web.action.customforms;

import com.oreon.trkincidents.customforms.FilledForm;

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

import com.oreon.trkincidents.customforms.FilledForm;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FilledFormListQueryBase
		extends
			BaseQuery<FilledForm, Long> {

	private static final String EJBQL = "select filledForm from FilledForm filledForm";

	protected FilledForm filledForm = new FilledForm();

	public FilledForm getFilledForm() {
		return filledForm;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<FilledForm> getEntityClass() {
		return FilledForm.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"filledForm.id = #{filledFormList.filledForm.id}",

			"filledForm.customForm.id = #{filledFormList.filledForm.customForm.id}",

			"filledForm.entityId = #{filledFormList.filledForm.entityId}",

			"filledForm.dateCreated <= #{filledFormList.dateCreatedRange.end}",
			"filledForm.dateCreated >= #{filledFormList.dateCreatedRange.begin}",};

	@Observer("archivedFilledForm")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, FilledForm e) {

		builder.append("\""
				+ (e.getCustomForm() != null ? e.getCustomForm()
						.getDisplayName() : "") + "\",");

		builder.append("\"" + (e.getEntityId() != null ? e.getEntityId() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("CustomForm" + ",");

		builder.append("EntityId" + ",");

		builder.append("\r\n");
	}
}
