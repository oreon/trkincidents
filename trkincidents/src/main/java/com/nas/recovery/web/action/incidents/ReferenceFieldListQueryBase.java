package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.ReferenceField;

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

import com.oreon.trkincidents.incidents.ReferenceField;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ReferenceFieldListQueryBase
		extends
			BaseQuery<ReferenceField, Long> {

	private static final String EJBQL = "select referenceField from ReferenceField referenceField";

	protected ReferenceField referenceField = new ReferenceField();

	public ReferenceField getReferenceField() {
		return referenceField;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ReferenceField> getEntityClass() {
		return ReferenceField.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"referenceField.id = #{referenceFieldList.referenceField.id}",

			"referenceField.incidentType.id = #{referenceFieldList.referenceField.incidentType.id}",

			"referenceField.referencesEntity = #{referenceFieldList.referenceField.referencesEntity}",

			"referenceField.required = #{referenceFieldList.referenceField.required}",

			"referenceField.dateCreated <= #{referenceFieldList.dateCreatedRange.end}",
			"referenceField.dateCreated >= #{referenceFieldList.dateCreatedRange.begin}",};

	public List<ReferenceField> getReferenceFieldsByIncidentType(
			com.oreon.trkincidents.incidents.IncidentType incidentType) {
		//setMaxResults(10000);
		referenceField.setIncidentType(incidentType);
		return getResultList();
	}

	@Observer("archivedReferenceField")
	public void onArchive() {
		refresh();
	}

}
