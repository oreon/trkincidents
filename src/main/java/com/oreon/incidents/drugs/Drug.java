package com.oreon.incidents.drugs;

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
@Table(name = "drug")
@Filter(name = "archiveFilterDef")
@Name("drug")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Drug extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -16274297L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@NotNull
	@Lob
	@Column(name = "absorption", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String absorption

	;

	@NotNull
	@Lob
	@Column(name = "biotransformation", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String biotransformation

	;

	@NotNull
	@Column(name = "atcCodes", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String atcCodes

	;

	@NotNull
	@Lob
	@Column(name = "contraIndication", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String contraIndication

	;

	@NotNull
	@Lob
	@Column(name = "description", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String description

	;

	@NotNull
	@Column(name = "dosageForm", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String dosageForm

	;

	@NotNull
	@Lob
	@Column(name = "foodInteractions", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String foodInteractions

	;

	@NotNull
	@Lob
	@Column(name = "halfLife", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String halfLife

	;

	@NotNull
	@Column(name = "halfLifeNumberOfHours", unique = false)
	protected Double halfLifeNumberOfHours

	;

	@NotNull
	@Lob
	@Column(name = "indication", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String indication

	;

	@NotNull
	@Lob
	@Column(name = "mechanismOfAction", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String mechanismOfAction

	;

	@NotNull
	@Lob
	@Column(name = "patientInfo", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String patientInfo

	;

	@NotNull
	@Lob
	@Column(name = "pharmacology", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String pharmacology

	;

	@OneToMany(mappedBy = "drug", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "drug_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<DrugInteraction> drugInteractions = new HashSet<DrugInteraction>();

	public void addDrugInteraction(DrugInteraction drugInteraction) {
		drugInteraction.setDrug(this);
		this.drugInteractions.add(drugInteraction);
	}

	@Transient
	public List<com.oreon.incidents.drugs.DrugInteraction> getListDrugInteractions() {
		return new ArrayList<com.oreon.incidents.drugs.DrugInteraction>(
				drugInteractions);
	}

	//JSF Friendly function to get count of collections
	public int getDrugInteractionsCount() {
		return drugInteractions.size();
	}

	@ManyToMany(mappedBy = "drugs")
	private Set<DrugCategory> drugCategorys = new HashSet<DrugCategory>();

	@NotNull
	@Lob
	@Column(name = "toxicity", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String toxicity

	;

	@NotNull
	@Lob
	@Column(name = "routeOfElimination", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String routeOfElimination

	;

	@NotNull
	@Column(name = "volumeOfDistribution", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String volumeOfDistribution

	;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String drugBankId

	;

	@Transient
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String categories

	;

	@OneToMany(mappedBy = "drug", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "drug_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<com.oreon.incidents.incidents.Incident> incidents = new HashSet<com.oreon.incidents.incidents.Incident>();

	public void addIncident(com.oreon.incidents.incidents.Incident incident) {
		incident.setDrug(this);
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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setAbsorption(String absorption) {
		this.absorption = absorption;
	}

	public String getAbsorption() {

		return absorption;

	}

	public void setBiotransformation(String biotransformation) {
		this.biotransformation = biotransformation;
	}

	public String getBiotransformation() {

		return biotransformation;

	}

	public void setAtcCodes(String atcCodes) {
		this.atcCodes = atcCodes;
	}

	public String getAtcCodes() {

		return atcCodes;

	}

	public void setContraIndication(String contraIndication) {
		this.contraIndication = contraIndication;
	}

	public String getContraIndication() {

		return contraIndication;

	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {

		return description;

	}

	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}

	public String getDosageForm() {

		return dosageForm;

	}

	public void setFoodInteractions(String foodInteractions) {
		this.foodInteractions = foodInteractions;
	}

	public String getFoodInteractions() {

		return foodInteractions;

	}

	public void setHalfLife(String halfLife) {
		this.halfLife = halfLife;
	}

	public String getHalfLife() {

		return halfLife;

	}

	public void setHalfLifeNumberOfHours(Double halfLifeNumberOfHours) {
		this.halfLifeNumberOfHours = halfLifeNumberOfHours;
	}

	public Double getHalfLifeNumberOfHours() {

		return halfLifeNumberOfHours;

	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getIndication() {

		return indication;

	}

	public void setMechanismOfAction(String mechanismOfAction) {
		this.mechanismOfAction = mechanismOfAction;
	}

	public String getMechanismOfAction() {

		return mechanismOfAction;

	}

	public void setPatientInfo(String patientInfo) {
		this.patientInfo = patientInfo;
	}

	public String getPatientInfo() {

		return patientInfo;

	}

	public void setPharmacology(String pharmacology) {
		this.pharmacology = pharmacology;
	}

	public String getPharmacology() {

		return pharmacology;

	}

	public void setDrugInteractions(Set<DrugInteraction> drugInteractions) {
		this.drugInteractions = drugInteractions;
	}

	public Set<DrugInteraction> getDrugInteractions() {
		return drugInteractions;
	}

	public void setDrugCategorys(Set<DrugCategory> drugCategorys) {
		this.drugCategorys = drugCategorys;
	}

	public Set<DrugCategory> getDrugCategorys() {
		return drugCategorys;
	}

	public void setToxicity(String toxicity) {
		this.toxicity = toxicity;
	}

	public String getToxicity() {

		return toxicity;

	}

	public void setRouteOfElimination(String routeOfElimination) {
		this.routeOfElimination = routeOfElimination;
	}

	public String getRouteOfElimination() {

		return routeOfElimination;

	}

	public void setVolumeOfDistribution(String volumeOfDistribution) {
		this.volumeOfDistribution = volumeOfDistribution;
	}

	public String getVolumeOfDistribution() {

		return volumeOfDistribution;

	}

	public void setDrugBankId(String drugBankId) {
		this.drugBankId = drugBankId;
	}

	public String getDrugBankId() {

		return drugBankId;

	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getCategories() {

		try {
			return getCollectionAsString(drugCategorys);
		} catch (Exception e) {

			return "";

		}

	}

	public void setIncidents(
			Set<com.oreon.incidents.incidents.Incident> incidents) {
		this.incidents = incidents;
	}

	public Set<com.oreon.incidents.incidents.Incident> getIncidents() {
		return incidents;
	}

	@Transient
	public String getDisplayName() {
		try {
			return name;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getPopupInfo() {
		try {
			return description;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getAbsorptionAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(absorption
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return absorption != null ? absorption : "";
		}
	}

	@Transient
	public String getBiotransformationAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(
					biotransformation.trim(), 100, 200, "...");
		} catch (Exception e) {
			return biotransformation != null ? biotransformation : "";
		}
	}

	@Transient
	public String getContraIndicationAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(
					contraIndication.trim(), 100, 200, "...");
		} catch (Exception e) {
			return contraIndication != null ? contraIndication : "";
		}
	}

	@Transient
	public String getDescriptionAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(description
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return description != null ? description : "";
		}
	}

	@Transient
	public String getFoodInteractionsAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(
					foodInteractions.trim(), 100, 200, "...");
		} catch (Exception e) {
			return foodInteractions != null ? foodInteractions : "";
		}
	}

	@Transient
	public String getHalfLifeAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(
					halfLife.trim(), 100, 200, "...");
		} catch (Exception e) {
			return halfLife != null ? halfLife : "";
		}
	}

	@Transient
	public String getIndicationAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(indication
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return indication != null ? indication : "";
		}
	}

	@Transient
	public String getMechanismOfActionAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(
					mechanismOfAction.trim(), 100, 200, "...");
		} catch (Exception e) {
			return mechanismOfAction != null ? mechanismOfAction : "";
		}
	}

	@Transient
	public String getPatientInfoAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(patientInfo
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return patientInfo != null ? patientInfo : "";
		}
	}

	@Transient
	public String getPharmacologyAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(pharmacology
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return pharmacology != null ? pharmacology : "";
		}
	}

	@Transient
	public String getToxicityAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(
					toxicity.trim(), 100, 200, "...");
		} catch (Exception e) {
			return toxicity != null ? toxicity : "";
		}
	}

	@Transient
	public String getRouteOfEliminationAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(
					routeOfElimination.trim(), 100, 200, "...");
		} catch (Exception e) {
			return routeOfElimination != null ? routeOfElimination : "";
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

		listSearchableFields.add("absorption");

		listSearchableFields.add("biotransformation");

		listSearchableFields.add("atcCodes");

		listSearchableFields.add("contraIndication");

		listSearchableFields.add("description");

		listSearchableFields.add("dosageForm");

		listSearchableFields.add("foodInteractions");

		listSearchableFields.add("halfLife");

		listSearchableFields.add("indication");

		listSearchableFields.add("mechanismOfAction");

		listSearchableFields.add("patientInfo");

		listSearchableFields.add("pharmacology");

		listSearchableFields.add("toxicity");

		listSearchableFields.add("routeOfElimination");

		listSearchableFields.add("volumeOfDistribution");

		listSearchableFields.add("drugBankId");

		listSearchableFields.add("categories");

		listSearchableFields.add("drugInteractions.description");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		builder.append(getAbsorption() + " ");

		builder.append(getBiotransformation() + " ");

		builder.append(getAtcCodes() + " ");

		builder.append(getContraIndication() + " ");

		builder.append(getDescription() + " ");

		builder.append(getDosageForm() + " ");

		builder.append(getFoodInteractions() + " ");

		builder.append(getHalfLife() + " ");

		builder.append(getIndication() + " ");

		builder.append(getMechanismOfAction() + " ");

		builder.append(getPatientInfo() + " ");

		builder.append(getPharmacology() + " ");

		builder.append(getToxicity() + " ");

		builder.append(getRouteOfElimination() + " ");

		builder.append(getVolumeOfDistribution() + " ");

		builder.append(getDrugBankId() + " ");

		builder.append(getCategories() + " ");

		for (BaseEntity e : drugInteractions) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : incidents) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
