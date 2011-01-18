package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.FormField;

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

import com.oreon.trkincidents.incidents.FormField;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
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

			"formField.dateCreated <= #{formFieldList.dateCreatedRange.end}",
			"formField.dateCreated >= #{formFieldList.dateCreatedRange.begin}",};

	@Observer("archivedFormField")
	public void onArchive() {
		refresh();
	}

}
