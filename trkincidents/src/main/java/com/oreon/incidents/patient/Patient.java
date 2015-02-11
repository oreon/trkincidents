package com.oreon.incidents.patient;

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
@Table(name = "patient")
@Filter(name = "archiveFilterDef")
@Name("patient")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Patient extends com.oreon.incidents.patient.Person
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -586507236L;

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "streetAddress", column = @Column(name = "address_streetAddress")),

			@AttributeOverride(name = "city", column = @Column(name = "address_city")),

			@AttributeOverride(name = "state", column = @Column(name = "address_state")),

			@AttributeOverride(name = "phone", column = @Column(name = "address_phone"))

	})
	protected Address address = new Address();

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "patient_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<com.oreon.incidents.incidents.Incident> incidents = new HashSet<com.oreon.incidents.incidents.Incident>();

	public void addIncident(com.oreon.incidents.incidents.Incident incident) {
		incident.setPatient(this);
		this.incidents.add(incident);
	}

	@Transient
	public List<com.oreon.incidents.incidents.Incident> getListIncidents() {
		return new ArrayList<com.oreon.incidents.incidents.Incident>(incidents);
	}

	//JSF Friendly function to get count of collections
	public int getIncidentsCount() {
		return incidents.size();
	}

	@NotNull
	@Column(name = "healthNumber", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String healthNumber

	;

	@NotNull
	@Column(name = "dateOfBirth", unique = false)
	protected Date dateOfBirth

	;

	@NotNull
	@Column(name = "gender", unique = false)
	protected Gender gender

	;

	@Formula(value = "(DATEDIFF(NOW(),dateOfBirth)/365.25)")
	protected Integer age

	;

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {

		return address;

	}

	public void setIncidents(
			Set<com.oreon.incidents.incidents.Incident> incidents) {
		this.incidents = incidents;
	}

	public Set<com.oreon.incidents.incidents.Incident> getIncidents() {
		return incidents;
	}

	public void setHealthNumber(String healthNumber) {
		this.healthNumber = healthNumber;
	}

	public String getHealthNumber() {

		return healthNumber;

	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfBirth() {

		return dateOfBirth;

	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gender getGender() {

		return gender;

	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {

		return age;

	}

	@Transient
	public String getDisplayName() {
		try {
			return super.getDisplayName();
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getPopupInfo() {
		try {
			return age + " " + gender;
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

		listSearchableFields.add("healthNumber");

		listSearchableFields.add("address.streetAddress");

		listSearchableFields.add("address.city");

		listSearchableFields.add("address.state");

		listSearchableFields.add("address.phone");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getHealthNumber() + " ");

		for (BaseEntity e : incidents) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
