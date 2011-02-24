package com.nas.recovery.web.action.customforms;

import com.oreon.trkincidents.customforms.CustomForm;

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

import com.oreon.trkincidents.customforms.CustomForm;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CustomFormListQueryBase
		extends
			BaseQuery<CustomForm, Long> {

	private static final String EJBQL = "select customForm from CustomForm customForm";

	protected CustomForm customForm = new CustomForm();

	public CustomForm getCustomForm() {
		return customForm;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<CustomForm> getEntityClass() {
		return CustomForm.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"customForm.id = #{customFormList.customForm.id}",

			"lower(customForm.name) like concat(lower(#{customFormList.customForm.name}),'%')",

			"customForm.dateCreated <= #{customFormList.dateCreatedRange.end}",
			"customForm.dateCreated >= #{customFormList.dateCreatedRange.begin}",};

	@Observer("archivedCustomForm")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, CustomForm e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
