package com.nas.recovery.web.action.customforms;

import com.oreon.trkincidents.customforms.CustomField;

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

import com.oreon.trkincidents.customforms.CustomField;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class CustomFieldListQueryBase
		extends
			BaseQuery<CustomField, Long> {

	private static final String EJBQL = "select customField from CustomField customField";

	protected CustomField customField = new CustomField();

	public CustomField getCustomField() {
		return customField;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<CustomField> getEntityClass() {
		return CustomField.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"customField.id = #{customFieldList.customField.id}",

			"customField.customForm.id = #{customFieldList.customField.customForm.id}",

			"customField.required = #{customFieldList.customField.required}",

			"customField.type = #{customFieldList.customField.type}",

			"lower(customField.name) like concat(lower(#{customFieldList.customField.name}),'%')",

			"customField.dateCreated <= #{customFieldList.dateCreatedRange.end}",
			"customField.dateCreated >= #{customFieldList.dateCreatedRange.begin}",};

	public List<CustomField> getCustomFieldsByCustomForm(
			com.oreon.trkincidents.customforms.CustomForm customForm) {
		//setMaxResults(10000);
		customField.setCustomForm(customForm);
		return getResultList();
	}

	@Observer("archivedCustomField")
	public void onArchive() {
		refresh();
	}

}
