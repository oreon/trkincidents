package com.oreon.trkincidents.incidents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Cascade;

import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;
import org.hibernate.annotations.Filter;

import org.witchcraft.utils.*;

@Entity
@Table(name = "baseincident")
@Filter(name = "archiveFilterDef")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseIncident extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 524671862L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "severity_id", nullable = false, updatable = true)
	@ContainedIn
	protected Severity severity;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String description;

	@OneToMany(mappedBy = "baseIncident", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "baseIncident_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<com.oreon.trkincidents.patient.Document> documents = new HashSet<com.oreon.trkincidents.patient.Document>();

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "document_name")),
			@AttributeOverride(name = "contentType", column = @Column(name = "document_contentType")),
			@AttributeOverride(name = "data", column = @Column(name = "document_data", length = 4194304))})
	protected FileAttachment document = new FileAttachment();

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDocuments(
			Set<com.oreon.trkincidents.patient.Document> documents) {
		this.documents = documents;
	}

	public Set<com.oreon.trkincidents.patient.Document> getDocuments() {
		return documents;
	}

	public void setDocument(FileAttachment document) {
		this.document = document;
	}

	public FileAttachment getDocument() {
		return document;
	}

	@Transient
	public String getDisplayName() {
		try {
			return severity + "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	//Empty setter , needed for richfaces autocomplete to work 
	public void setDisplayName(String name) {
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("description");

		listSearchableFields.add("documents.name");

		listSearchableFields.add("documents.notes");

		return listSearchableFields;
	}

}
