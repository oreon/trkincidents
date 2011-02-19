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

import org.hibernate.annotations.Filter;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

@Entity
@Table(name = "incident")
@Filter(name = "archiveFilterDef")
@Name("incident")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@AnalyzerDef(name = "Incidentanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Incident extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 2028657351L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "incidentType_id", nullable = false, updatable = true)
	@ContainedIn
	protected IncidentType incidentType;

	@NotNull
	@Length(min = 2, max = 250)
	@Field(index = Index.TOKENIZED)
	// @Analyzer(definition = "Incidentanalyzer") 
	protected String title;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", nullable = true, updatable = true)
	@ContainedIn
	protected com.oreon.trkincidents.patient.Patient patient;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "createdBy_id", nullable = true, updatable = true)
	@ContainedIn
	protected com.oreon.trkincidents.employee.Employee createdBy;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.trkincidents.employee.Department department;

	@Column(name = "dateOfIncident", unique = false)
	protected Date dateOfIncident = new Date();;

	@OneToMany(mappedBy = "incident", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "incident_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<FormFieldInstance> formFieldInstances = new HashSet<FormFieldInstance>();

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "reportedTo_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.trkincidents.employee.Employee reportedTo;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "document_name")),
			@AttributeOverride(name = "contentType", column = @Column(name = "document_contentType")),
			@AttributeOverride(name = "data", column = @Column(name = "document_data", length = 4194304))})
	protected FileAttachment document = new FileAttachment();

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "drug_id", nullable = true, updatable = true)
	@ContainedIn
	protected Drug drug;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "proccedure_id", nullable = true, updatable = true)
	@ContainedIn
	protected Proccedure proccedure;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "responsibleEmployee_id", nullable = true, updatable = true)
	@ContainedIn
	protected com.oreon.trkincidents.employee.Employee responsibleEmployee;

	@Lob
	@Field(index = Index.TOKENIZED)
	// @Analyzer(definition = "Incidentanalyzer") 
	protected String description;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "severity_id", nullable = false, updatable = true)
	@ContainedIn
	protected Severity severity;

	@OneToMany(mappedBy = "incident", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "incident_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<SupportingDocuments> supportingDocumentses = new HashSet<SupportingDocuments>();

	public void setIncidentType(IncidentType incidentType) {
		this.incidentType = incidentType;
	}

	public IncidentType getIncidentType() {

		return incidentType;

	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {

		return title;

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

	public void setDepartment(
			com.oreon.trkincidents.employee.Department department) {
		this.department = department;
	}

	public com.oreon.trkincidents.employee.Department getDepartment() {

		return department;

	}

	public void setDateOfIncident(Date dateOfIncident) {
		this.dateOfIncident = dateOfIncident;
	}

	public Date getDateOfIncident() {

		return dateOfIncident;

	}

	public void setFormFieldInstances(Set<FormFieldInstance> formFieldInstances) {
		this.formFieldInstances = formFieldInstances;
	}

	public Set<FormFieldInstance> getFormFieldInstances() {
		return formFieldInstances;
	}

	public void setReportedTo(
			com.oreon.trkincidents.employee.Employee reportedTo) {
		this.reportedTo = reportedTo;
	}

	public com.oreon.trkincidents.employee.Employee getReportedTo() {

		return reportedTo;

	}

	public void setDocument(FileAttachment document) {
		this.document = document;
	}

	public FileAttachment getDocument() {

		return document;

	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Drug getDrug() {

		return drug;

	}

	public void setProccedure(Proccedure proccedure) {
		this.proccedure = proccedure;
	}

	public Proccedure getProccedure() {

		return proccedure;

	}

	public void setResponsibleEmployee(
			com.oreon.trkincidents.employee.Employee responsibleEmployee) {
		this.responsibleEmployee = responsibleEmployee;
	}

	public com.oreon.trkincidents.employee.Employee getResponsibleEmployee() {

		return responsibleEmployee;

	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {

		return description;

	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public Severity getSeverity() {

		return severity;

	}

	public void setSupportingDocumentses(
			Set<SupportingDocuments> supportingDocumentses) {
		this.supportingDocumentses = supportingDocumentses;
	}

	public Set<SupportingDocuments> getSupportingDocumentses() {
		return supportingDocumentses;
	}

	@Transient
	public String getDisplayName() {
		try {
			return title;
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

		listSearchableFields.add("title");

		listSearchableFields.add("description");

		listSearchableFields.add("formFieldInstances.value");

		listSearchableFields.add("formFieldInstances.description");

		listSearchableFields.add("supportingDocumentses.title");

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
