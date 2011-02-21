package com.oreon.trkincidents.custm;

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
@Table(name = "customreport")
@Filter(name = "archiveFilterDef")
@Name("customReport")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@AnalyzerDef(name = "CustomReportanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class CustomReport extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -994856749L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "metaEntity_id", nullable = false, updatable = true)
	@ContainedIn
	protected MetaEntity metaEntity;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "customReports_fields", joinColumns = @JoinColumn(name = "customReports_ID"), inverseJoinColumns = @JoinColumn(name = "fields_ID"))
	private Set<MetaField> fields = new HashSet<MetaField>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "groupReport_groupFields", joinColumns = @JoinColumn(name = "groupReport_ID"), inverseJoinColumns = @JoinColumn(name = "groupFields_ID"))
	private Set<MetaField> groupFields = new HashSet<MetaField>();

	@NotNull
	@Length(min = 2, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	// @Analyzer(definition = "CustomReportanalyzer") 
	protected String name;

	public void setMetaEntity(MetaEntity metaEntity) {
		this.metaEntity = metaEntity;
	}

	public MetaEntity getMetaEntity() {

		return metaEntity;

	}

	public void setFields(Set<MetaField> fields) {
		this.fields = fields;
	}

	public Set<MetaField> getFields() {
		return fields;
	}

	public void setGroupFields(Set<MetaField> groupFields) {
		this.groupFields = groupFields;
	}

	public Set<MetaField> getGroupFields() {
		return groupFields;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

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

		return listSearchableFields;
	}

}
