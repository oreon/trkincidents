package com.nas.recovery.web.action.patient;

import com.oreon.trkincidents.patient.Document;

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

import com.oreon.trkincidents.patient.Document;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class DocumentListQueryBase extends BaseQuery<Document, Long> {

	private static final String EJBQL = "select document from Document document";

	protected Document document = new Document();

	public Document getDocument() {
		return document;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Document> getEntityClass() {
		return Document.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"document.id = #{documentList.document.id}",

			"lower(document.name) like concat(lower(#{documentList.document.name}),'%')",

			"lower(document.notes) like concat(lower(#{documentList.document.notes}),'%')",

			"document.patient.id = #{documentList.document.patient.id}",

			"document.baseIncident.id = #{documentList.document.baseIncident.id}",

			"document.dateCreated <= #{documentList.dateCreatedRange.end}",
			"document.dateCreated >= #{documentList.dateCreatedRange.begin}",};

	public List<Document> getDocumentsByPatient(
			com.oreon.trkincidents.patient.Patient patient) {
		//setMaxResults(10000);
		document.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedDocument")
	public void onArchive() {
		refresh();
	}

}
