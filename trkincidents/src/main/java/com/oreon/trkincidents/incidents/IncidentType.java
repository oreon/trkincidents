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
@Table(name = "incidenttype")
@Filter(name = "archiveFilterDef")
@Name("incidentType")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class IncidentType extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 2058841249L;

	@NotNull
	@Length(min = 2, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name;

	@OneToMany(mappedBy = "incidentType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "incidentType_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<ReferenceField> referenceFields = new HashSet<ReferenceField>();

	public void addReferenceFields(ReferenceField referenceFields) {
		referenceFields.setIncidentType(this);
		this.referenceFields.add(referenceFields);
	}

	@OneToMany(mappedBy = "incidentType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "incidentType_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<FormField> formFields = new HashSet<FormField>();

	public void addFormFields(FormField formFields) {
		formFields.setIncidentType(this);
		this.formFields.add(formFields);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setReferenceFields(Set<ReferenceField> referenceFields) {
		this.referenceFields = referenceFields;
	}

	public Set<ReferenceField> getReferenceFields() {
		return referenceFields;
	}

	public void setFormFields(Set<FormField> formFields) {
		this.formFields = formFields;
	}

	public Set<FormField> getFormFields() {
		return formFields;
	}

	@Transient
	public String getDisplayName() {
		try {
			return name;
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

		listSearchableFields.add("name");

		listSearchableFields.add("formFields.name");

		listSearchableFields.add("formFields.choiceValues");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		for (BusinessEntity e : referenceFields) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BusinessEntity e : formFields) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
