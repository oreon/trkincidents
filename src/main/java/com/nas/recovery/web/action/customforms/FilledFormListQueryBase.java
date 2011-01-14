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
 * D
 * @author WitchcraftMDA Seam Cartridge
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

}
