package com.oreon.incidents.employee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

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
import org.hibernate.search.annotations.Boost;
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

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.oreon.incidents.ProjectUtils;

@Entity
@Table(name = "employee")
@Filter(name = "archiveFilterDef")
@Name("employee")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Employee extends com.oreon.incidents.patient.Person
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -426154292L;

	@NotNull
	@Column(name = "employeeNumber", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String employeeNumber

	;

	@OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "createdBy_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<com.oreon.incidents.incidents.Incident> incidentsCreated = new HashSet<com.oreon.incidents.incidents.Incident>();

	public void addIncidentsCreated(
			com.oreon.incidents.incidents.Incident incidentsCreated) {
		incidentsCreated.setCreatedBy(this);
		this.incidentsCreated.add(incidentsCreated);
	}

	@Transient
	public List<com.oreon.incidents.incidents.Incident> getListIncidentsCreated() {
		return new ArrayList<com.oreon.incidents.incidents.Incident>(
				incidentsCreated);
	}

	//JSF Friendly function to get count of collections
	public int getIncidentsCreatedCount() {
		return incidentsCreated.size();
	}

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.incidents.users.User user = new com.oreon.incidents.users.User();

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", nullable = false, updatable = true)
	@ContainedIn
	@NotNull
	protected Department department

	;

	@OneToMany(mappedBy = "responsibleEmployee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "responsibleEmployee_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<com.oreon.incidents.incidents.Incident> incidentsResponsibleFor = new HashSet<com.oreon.incidents.incidents.Incident>();

	public void addIncidentsResponsibleFor(
			com.oreon.incidents.incidents.Incident incidentsResponsibleFor) {
		incidentsResponsibleFor.setResponsibleEmployee(this);
		this.incidentsResponsibleFor.add(incidentsResponsibleFor);
	}

	@Transient
	public List<com.oreon.incidents.incidents.Incident> getListIncidentsResponsibleFor() {
		return new ArrayList<com.oreon.incidents.incidents.Incident>(
				incidentsResponsibleFor);
	}

	//JSF Friendly function to get count of collections
	public int getIncidentsResponsibleForCount() {
		return incidentsResponsibleFor.size();
	}

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "primaryPhone", column = @Column(name = "contactDetails_primaryPhone")),

			@AttributeOverride(name = "secondaryPhone", column = @Column(name = "contactDetails_secondaryPhone")),

			@AttributeOverride(name = "streetAddress", column = @Column(name = "contactDetails_streetAddress")),

			@AttributeOverride(name = "city", column = @Column(name = "contactDetails_city")),

			@AttributeOverride(name = "zip", column = @Column(name = "contactDetails_zip"))

	})
	protected ContactDetails contactDetails = new ContactDetails();

	@OneToMany(mappedBy = "reportedTo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "reportedTo_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<com.oreon.incidents.incidents.Incident> incidentsReported = new HashSet<com.oreon.incidents.incidents.Incident>();

	public void addIncidentsReported(
			com.oreon.incidents.incidents.Incident incidentsReported) {
		incidentsReported.setReportedTo(this);
		this.incidentsReported.add(incidentsReported);
	}

	@Transient
	public List<com.oreon.incidents.incidents.Incident> getListIncidentsReported() {
		return new ArrayList<com.oreon.incidents.incidents.Incident>(
				incidentsReported);
	}

	//JSF Friendly function to get count of collections
	public int getIncidentsReportedCount() {
		return incidentsReported.size();
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getEmployeeNumber() {

		return employeeNumber;

	}

	public void setIncidentsCreated(
			Set<com.oreon.incidents.incidents.Incident> incidentsCreated) {
		this.incidentsCreated = incidentsCreated;
	}

	public Set<com.oreon.incidents.incidents.Incident> getIncidentsCreated() {
		return incidentsCreated;
	}

	public void setUser(com.oreon.incidents.users.User user) {
		this.user = user;
	}

	public com.oreon.incidents.users.User getUser() {

		return user;

	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDepartment() {

		return department;

	}

	public void setIncidentsResponsibleFor(
			Set<com.oreon.incidents.incidents.Incident> incidentsResponsibleFor) {
		this.incidentsResponsibleFor = incidentsResponsibleFor;
	}

	public Set<com.oreon.incidents.incidents.Incident> getIncidentsResponsibleFor() {
		return incidentsResponsibleFor;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public ContactDetails getContactDetails() {

		return contactDetails;

	}

	public void setIncidentsReported(
			Set<com.oreon.incidents.incidents.Incident> incidentsReported) {
		this.incidentsReported = incidentsReported;
	}

	public Set<com.oreon.incidents.incidents.Incident> getIncidentsReported() {
		return incidentsReported;
	}

	@Transient
	public String getDisplayName() {
		try {
			return super.getDisplayName();
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	//Empty setter , needed for richfaces autocomplete to work 
	public void setDisplayName(String name) {
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BaseEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("employeeNumber");

		listSearchableFields.add("contactDetails.primaryPhone");

		listSearchableFields.add("contactDetails.secondaryPhone");

		listSearchableFields.add("contactDetails.streetAddress");

		listSearchableFields.add("contactDetails.city");

		listSearchableFields.add("contactDetails.zip");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getEmployeeNumber() + " ");

		if (getUser() != null)
			builder.append("user:" + getUser().getDisplayName() + " ");

		if (getDepartment() != null)
			builder.append("department:" + getDepartment().getDisplayName()
					+ " ");

		for (BaseEntity e : incidentsCreated) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : incidentsResponsibleFor) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : incidentsReported) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
