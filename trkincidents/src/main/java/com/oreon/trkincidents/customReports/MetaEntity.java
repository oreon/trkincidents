package com.oreon.trkincidents.customReports;

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
@Table(name = "metaentity")
@Filter(name = "archiveFilterDef")
@Name("metaEntity")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class MetaEntity extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1562422680L;

	@NotNull
	@Length(min = 2, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name;

	@OneToMany(mappedBy = "metaEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "metaEntity_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<MetaField> metaFields = new HashSet<MetaField>();

	public void addMetaFields(MetaField metaFields) {
		metaFields.setMetaEntity(this);
		this.metaFields.add(metaFields);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setMetaFields(Set<MetaField> metaFields) {
		this.metaFields = metaFields;
	}

	public Set<MetaField> getMetaFields() {
		return metaFields;
	}

	@Transient
	public String getDisplayName() {
		try {
			return com.nas.recovery.ProjectUtils.getSimpleName(name);
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

		listSearchableFields.add("metaFields.name");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		for (BusinessEntity e : metaFields) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
