package com.oreon.incidents.web.action.drugs;

import com.oreon.incidents.drugs.Drug;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;

import com.oreon.incidents.drugs.Drug;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DrugListQueryBase extends BaseQuery<Drug, Long> {

	private static final String EJBQL = "select drug from Drug drug";

	protected Drug drug = new Drug();

	public Drug getDrug() {
		return drug;
	}

	private com.oreon.incidents.drugs.DrugCategory drugCategorysToSearch;

	public void setDrugCategorysToSearch(
			com.oreon.incidents.drugs.DrugCategory drugCategoryToSearch) {
		this.drugCategorysToSearch = drugCategoryToSearch;
	}

	public com.oreon.incidents.drugs.DrugCategory getDrugCategorysToSearch() {
		return drugCategorysToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Drug> getEntityClass() {
		return Drug.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> halfLifeNumberOfHoursRange = new Range<Double>();

	public Range<Double> getHalfLifeNumberOfHoursRange() {
		return halfLifeNumberOfHoursRange;
	}
	public void setHalfLifeNumberOfHours(
			Range<Double> halfLifeNumberOfHoursRange) {
		this.halfLifeNumberOfHoursRange = halfLifeNumberOfHoursRange;
	}

	private static final String[] RESTRICTIONS = {
			"drug.id = #{drugList.drug.id}",

			"lower(drug.name) like concat(lower(#{drugList.drug.name}),'%')",

			"lower(drug.absorption) like concat(lower(#{drugList.drug.absorption}),'%')",

			"lower(drug.biotransformation) like concat(lower(#{drugList.drug.biotransformation}),'%')",

			"lower(drug.atcCodes) like concat(lower(#{drugList.drug.atcCodes}),'%')",

			"lower(drug.contraIndication) like concat(lower(#{drugList.drug.contraIndication}),'%')",

			"lower(drug.description) like concat(lower(#{drugList.drug.description}),'%')",

			"lower(drug.dosageForm) like concat(lower(#{drugList.drug.dosageForm}),'%')",

			"lower(drug.foodInteractions) like concat(lower(#{drugList.drug.foodInteractions}),'%')",

			"lower(drug.halfLife) like concat(lower(#{drugList.drug.halfLife}),'%')",

			"drug.halfLifeNumberOfHours >= #{drugList.halfLifeNumberOfHoursRange.begin}",
			"drug.halfLifeNumberOfHours <= #{drugList.halfLifeNumberOfHoursRange.end}",

			"lower(drug.indication) like concat(lower(#{drugList.drug.indication}),'%')",

			"lower(drug.mechanismOfAction) like concat(lower(#{drugList.drug.mechanismOfAction}),'%')",

			"lower(drug.patientInfo) like concat(lower(#{drugList.drug.patientInfo}),'%')",

			"lower(drug.pharmacology) like concat(lower(#{drugList.drug.pharmacology}),'%')",

			"#{drugList.drugCategorysToSearch} in elements(drug.drugCategorys)",

			"lower(drug.toxicity) like concat(lower(#{drugList.drug.toxicity}),'%')",

			"lower(drug.routeOfElimination) like concat(lower(#{drugList.drug.routeOfElimination}),'%')",

			"lower(drug.volumeOfDistribution) like concat(lower(#{drugList.drug.volumeOfDistribution}),'%')",

			"lower(drug.drugBankId) like concat(lower(#{drugList.drug.drugBankId}),'%')",

			"drug.dateCreated <= #{drugList.dateCreatedRange.end}",
			"drug.dateCreated >= #{drugList.dateCreatedRange.begin}",};

	@Observer("archivedDrug")
	public void onArchive() {
		refresh();
	}

	@Override
	protected void setupForAutoComplete(String input) {

		drug.setName(input);

	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Drug e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getAbsorption() != null ? e.getAbsorption().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getBiotransformation() != null ? e.getBiotransformation()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getAtcCodes() != null
						? e.getAtcCodes().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getContraIndication() != null ? e.getContraIndication()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getDosageForm() != null ? e.getDosageForm().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getFoodInteractions() != null ? e.getFoodInteractions()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getHalfLife() != null
						? e.getHalfLife().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getHalfLifeNumberOfHours() != null ? e
						.getHalfLifeNumberOfHours() : "") + "\",");

		builder.append("\""
				+ (e.getIndication() != null ? e.getIndication().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getMechanismOfAction() != null ? e.getMechanismOfAction()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getPatientInfo() != null ? e.getPatientInfo().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getPharmacology() != null ? e.getPharmacology().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getDrugCategorys() != null ? e.getDrugCategorys() : "")
				+ "\",");

		builder.append("\""
				+ (e.getToxicity() != null
						? e.getToxicity().replace(",", "")
						: "") + "\",");

		builder
				.append("\""
						+ (e.getRouteOfElimination() != null ? e
								.getRouteOfElimination().replace(",", "") : "")
						+ "\",");

		builder.append("\""
				+ (e.getVolumeOfDistribution() != null ? e
						.getVolumeOfDistribution().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getDrugBankId() != null ? e.getDrugBankId().replace(",",
						"") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Absorption" + ",");

		builder.append("Biotransformation" + ",");

		builder.append("AtcCodes" + ",");

		builder.append("ContraIndication" + ",");

		builder.append("Description" + ",");

		builder.append("DosageForm" + ",");

		builder.append("FoodInteractions" + ",");

		builder.append("HalfLife" + ",");

		builder.append("HalfLifeNumberOfHours" + ",");

		builder.append("Indication" + ",");

		builder.append("MechanismOfAction" + ",");

		builder.append("PatientInfo" + ",");

		builder.append("Pharmacology" + ",");

		builder.append("DrugCategorys" + ",");

		builder.append("Toxicity" + ",");

		builder.append("RouteOfElimination" + ",");

		builder.append("VolumeOfDistribution" + ",");

		builder.append("DrugBankId" + ",");

		builder.append("\r\n");
	}
}
