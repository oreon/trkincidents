package com.oreon.trkincidents.appointment;

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
@Table(name = "encounter")
@Filter(name = "archiveFilterDef")
@Name("encounter")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class Encounter extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -2016546332L;

	@OneToMany(mappedBy = "encounter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "encounter_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<History> historys = new HashSet<History>();

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.trkincidents.patient.Patient patient;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String notes;

	@OneToMany(mappedBy = "encounter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "encounter_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<PrescribedTest> prescribedTests = new HashSet<PrescribedTest>();

	public void setHistorys(Set<History> historys) {
		this.historys = historys;
	}

	public Set<History> getHistorys() {
		return historys;
	}

	public void setPatient(com.oreon.trkincidents.patient.Patient patient) {
		this.patient = patient;
	}

	public com.oreon.trkincidents.patient.Patient getPatient() {

		return patient;

	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {

		return notes;

	}

	public void setPrescribedTests(Set<PrescribedTest> prescribedTests) {
		this.prescribedTests = prescribedTests;
	}

	public Set<PrescribedTest> getPrescribedTests() {
		return prescribedTests;
	}

	@Transient
	public String getDisplayName() {
		try {
			return historys + "";
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

		listSearchableFields.add("notes");

		listSearchableFields.add("historys.history");

		listSearchableFields.add("prescribedTests.remarks");

		return listSearchableFields;
	}

}
