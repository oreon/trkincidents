package com.oreon.incidents.incidents;

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
@Table(name = "referencefield")
@Filter(name = "archiveFilterDef")
@Name("referenceField")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class ReferenceField extends BaseEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -897898974L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "incidentType_id", nullable = false, updatable = true)
	@ContainedIn
	protected IncidentType incidentType

	;

	@Column(unique = false)
	protected ReferenceEntity referencesEntity

	;

	@Column(unique = false)
	protected Boolean required

	;

	public void setIncidentType(IncidentType incidentType) {
		this.incidentType = incidentType;
	}

	public IncidentType getIncidentType() {

		return incidentType;

	}

	public void setReferencesEntity(ReferenceEntity referencesEntity) {
		this.referencesEntity = referencesEntity;
	}

	public ReferenceEntity getReferencesEntity() {

		return referencesEntity;

	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Boolean getRequired() {

		return required;

	}

	@Transient
	public String getDisplayName() {
		try {
			return incidentType + "";
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

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		if (getIncidentType() != null)
			builder.append("incidentType:" + getIncidentType().getDisplayName()
					+ " ");

		return builder.toString();
	}

}
