package com.oreon.trkincidents.unusualoccurences;

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
@Table(name = "incident")
@Filter(name = "archiveFilterDef")
@Name("incident")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Incident extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1953656907L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "occurenceType_id", nullable = false, updatable = true)
	@ContainedIn
	protected OccurenceType occurenceType;

	protected TreatmentCategory category;

	protected Severity severity;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String description;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.trkincidents.patient.Patient patient;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "createdBy_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.trkincidents.employee.Employee createdBy;

	public void setOccurenceType(OccurenceType occurenceType) {
		this.occurenceType = occurenceType;
	}

	public OccurenceType getOccurenceType() {
		return occurenceType;
	}

	public void setCategory(TreatmentCategory category) {
		this.category = category;
	}

	public TreatmentCategory getCategory() {
		return category;
	}

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

	public void setPatient(com.oreon.trkincidents.patient.Patient patient) {
		this.patient = patient;
	}

	public com.oreon.trkincidents.patient.Patient getPatient() {
		return patient;
	}

	public void setCreatedBy(com.oreon.trkincidents.employee.Employee createdBy) {
		this.createdBy = createdBy;
	}

	public com.oreon.trkincidents.employee.Employee getCreatedBy() {
		return createdBy;
	}

	@Transient
	public String getDisplayName() {
		try {
			return occurenceType + "";
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

		return listSearchableFields;
	}

	private Long processId;

	private String processName;

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

}
