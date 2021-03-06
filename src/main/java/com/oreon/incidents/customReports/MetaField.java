package com.oreon.incidents.customReports;

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
@Table(name = "metafield")
@Filter(name = "archiveFilterDef")
@Name("metaField")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class MetaField extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 31365911L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "metaEntity_id", nullable = false, updatable = true)
	@ContainedIn
	protected MetaEntity metaEntity

	;

	@ManyToMany(mappedBy = "fields")
	private Set<CustomReport> customReports = new HashSet<CustomReport>();

	@ManyToMany(mappedBy = "groupFields")
	private Set<CustomReport> groupReport = new HashSet<CustomReport>();

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String type

	;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setMetaEntity(MetaEntity metaEntity) {
		this.metaEntity = metaEntity;
	}

	public MetaEntity getMetaEntity() {

		return metaEntity;

	}

	public void setCustomReports(Set<CustomReport> customReports) {
		this.customReports = customReports;
	}

	public Set<CustomReport> getCustomReports() {
		return customReports;
	}

	public void setGroupReport(Set<CustomReport> groupReport) {
		this.groupReport = groupReport;
	}

	public Set<CustomReport> getGroupReport() {
		return groupReport;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {

		return type;

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
	 * @see org.witchcraft.model.support.BaseEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("name");

		listSearchableFields.add("type");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		builder.append(getType() + " ");

		if (getMetaEntity() != null)
			builder.append("metaEntity:" + getMetaEntity().getDisplayName()
					+ " ");

		return builder.toString();
	}

}
