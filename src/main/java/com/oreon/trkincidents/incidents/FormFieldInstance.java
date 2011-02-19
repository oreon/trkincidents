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
@Table(name = "formfieldinstance")
@Filter(name = "archiveFilterDef")
@Name("formFieldInstance")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@AnalyzerDef(name = "FormFieldInstanceanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class FormFieldInstance extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 1050165182L;

	@Field(index = Index.TOKENIZED)
	// @Analyzer(definition = "FormFieldInstanceanalyzer") 
	protected String value;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "incident_id", nullable = false, updatable = true)
	@ContainedIn
	protected Incident incident;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "formField_id", nullable = false, updatable = true)
	@ContainedIn
	protected FormField formField;

	protected Boolean boolValue;

	protected Date dateValue;

	protected Integer enumOrdinal;

	@Lob
	@Field(index = Index.TOKENIZED)
	// @Analyzer(definition = "FormFieldInstanceanalyzer") 
	protected String description;

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {

		return value;

	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public Incident getIncident() {

		return incident;

	}

	public void setFormField(FormField formField) {
		this.formField = formField;
	}

	public FormField getFormField() {

		return formField;

	}

	public void setBoolValue(Boolean boolValue) {
		this.boolValue = boolValue;
	}

	public Boolean getBoolValue() {

		return boolValue;

	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public Date getDateValue() {

		return dateValue;

	}

	public void setEnumOrdinal(Integer enumOrdinal) {
		this.enumOrdinal = enumOrdinal;
	}

	public Integer getEnumOrdinal() {

		return enumOrdinal;

	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {

		return description;

	}

	@Transient
	public String getDisplayName() {
		try {
			return value;
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

		listSearchableFields.add("value");

		listSearchableFields.add("description");

		return listSearchableFields;
	}

}
