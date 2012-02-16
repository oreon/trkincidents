package com.oreon.incidents.web.action.drugs;

import com.oreon.incidents.drugs.DrugCategory;

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

import com.oreon.incidents.drugs.DrugCategory;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DrugCategoryListQueryBase
		extends
			BaseQuery<DrugCategory, Long> {

	private static final String EJBQL = "select drugCategory from DrugCategory drugCategory";

	protected DrugCategory drugCategory = new DrugCategory();

	public DrugCategory getDrugCategory() {
		return drugCategory;
	}

	private com.oreon.incidents.drugs.Drug drugsToSearch;

	public void setDrugsToSearch(com.oreon.incidents.drugs.Drug drugToSearch) {
		this.drugsToSearch = drugToSearch;
	}

	public com.oreon.incidents.drugs.Drug getDrugsToSearch() {
		return drugsToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<DrugCategory> getEntityClass() {
		return DrugCategory.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"drugCategory.id = #{drugCategoryList.drugCategory.id}",

			"lower(drugCategory.name) like concat(lower(#{drugCategoryList.drugCategory.name}),'%')",

			"#{drugCategoryList.drugsToSearch} in elements(drugCategory.drugs)",

			"drugCategory.dateCreated <= #{drugCategoryList.dateCreatedRange.end}",
			"drugCategory.dateCreated >= #{drugCategoryList.dateCreatedRange.begin}",};

	@Observer("archivedDrugCategory")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, DrugCategory e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getDrugs() != null ? e.getDrugs() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Drugs" + ",");

		builder.append("\r\n");
	}
}
