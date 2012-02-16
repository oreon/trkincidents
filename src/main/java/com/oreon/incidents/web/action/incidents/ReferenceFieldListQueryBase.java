package com.oreon.incidents.web.action.incidents;

import com.oreon.incidents.incidents.ReferenceField;

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

import com.oreon.incidents.incidents.ReferenceField;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
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
			com.oreon.incidents.incidents.IncidentType incidentType) {
		//setMaxResults(10000);
		referenceField.setIncidentType(incidentType);
		return getResultList();
	}

	@Observer("archivedReferenceField")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ReferenceField e) {

		builder.append("\""
				+ (e.getIncidentType() != null ? e.getIncidentType()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getReferencesEntity() != null
						? e.getReferencesEntity()
						: "") + "\",");

		builder.append("\"" + (e.getRequired() != null ? e.getRequired() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("IncidentType" + ",");

		builder.append("ReferencesEntity" + ",");

		builder.append("Required" + ",");

		builder.append("\r\n");
	}
}
