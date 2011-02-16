package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.SupportingDocuments;

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

import com.oreon.trkincidents.incidents.SupportingDocuments;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SupportingDocumentsListQueryBase
		extends
			BaseQuery<SupportingDocuments, Long> {

	private static final String EJBQL = "select supportingDocuments from SupportingDocuments supportingDocuments";

	protected SupportingDocuments supportingDocuments = new SupportingDocuments();

	public SupportingDocuments getSupportingDocuments() {
		return supportingDocuments;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<SupportingDocuments> getEntityClass() {
		return SupportingDocuments.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"supportingDocuments.id = #{supportingDocumentsList.supportingDocuments.id}",

			"lower(supportingDocuments.title) like concat(lower(#{supportingDocumentsList.supportingDocuments.title}),'%')",

			"supportingDocuments.incident.id = #{supportingDocumentsList.supportingDocuments.incident.id}",

			"supportingDocuments.dateCreated <= #{supportingDocumentsList.dateCreatedRange.end}",
			"supportingDocuments.dateCreated >= #{supportingDocumentsList.dateCreatedRange.begin}",};

	public List<SupportingDocuments> getSupportingDocumentsesByIncident(
			com.oreon.trkincidents.incidents.Incident incident) {
		//setMaxResults(10000);
		supportingDocuments.setIncident(incident);
		return getResultList();
	}

	@Observer("archivedSupportingDocuments")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, SupportingDocuments e) {

		builder.append("\"" + (e.getTitle() != null ? e.getTitle() : "")
				+ "\",");

		builder.append("\""
				+ (e.getIncident() != null
						? e.getIncident().getDisplayName()
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Title" + ",");

		builder.append("Incident" + ",");

		builder.append("\r\n");
	}
}
