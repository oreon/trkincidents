package com.oreon.incidents.web.action.drugs;

import com.oreon.incidents.drugs.DrugInteraction;

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

import com.oreon.incidents.drugs.DrugInteraction;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DrugInteractionListQueryBase
		extends
			BaseQuery<DrugInteraction, Long> {

	private static final String EJBQL = "select drugInteraction from DrugInteraction drugInteraction";

	protected DrugInteraction drugInteraction = new DrugInteraction();

	public DrugInteraction getDrugInteraction() {
		return drugInteraction;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<DrugInteraction> getEntityClass() {
		return DrugInteraction.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"drugInteraction.id = #{drugInteractionList.drugInteraction.id}",

			"lower(drugInteraction.description) like concat(lower(#{drugInteractionList.drugInteraction.description}),'%')",

			"drugInteraction.drug.id = #{drugInteractionList.drugInteraction.drug.id}",

			"drugInteraction.interactingDrug.id = #{drugInteractionList.drugInteraction.interactingDrug.id}",

			"drugInteraction.dateCreated <= #{drugInteractionList.dateCreatedRange.end}",
			"drugInteraction.dateCreated >= #{drugInteractionList.dateCreatedRange.begin}",};

	public List<DrugInteraction> getDrugInteractionsByDrug(
			com.oreon.incidents.drugs.Drug drug) {
		//setMaxResults(10000);
		drugInteraction.setDrug(drug);
		return getResultList();
	}

	@Observer("archivedDrugInteraction")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, DrugInteraction e) {

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getDrug() != null ? e.getDrug().getDisplayName().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getInteractingDrug() != null ? e.getInteractingDrug()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Description" + ",");

		builder.append("Drug" + ",");

		builder.append("InteractingDrug" + ",");

		builder.append("\r\n");
	}
}
