package com.oreon.trkincidents.customforms;

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
@Table(name = "filledform")
@Filter(name = "archiveFilterDef")
@Name("filledForm")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class FilledForm extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 916009334L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customForm_id", nullable = false, updatable = true)
	@ContainedIn
	protected CustomForm customForm;

	@OneToMany(mappedBy = "filledForm", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "filledForm_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<FilledField> filledFields = new HashSet<FilledField>();

	protected Long entityId;

	public void setCustomForm(CustomForm customForm) {
		this.customForm = customForm;
	}

	public CustomForm getCustomForm() {

		return customForm;

	}

	public void setFilledFields(Set<FilledField> filledFields) {
		this.filledFields = filledFields;
	}

	public Set<FilledField> getFilledFields() {
		return filledFields;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getEntityId() {

		return entityId;

	}

	@Transient
	public String getDisplayName() {
		try {
			return customForm + "";
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

		listSearchableFields.add("filledFields.value");

		return listSearchableFields;
	}

}
