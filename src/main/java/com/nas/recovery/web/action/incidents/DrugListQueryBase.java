package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.Drug;

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

import com.oreon.trkincidents.incidents.Drug;

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

	private static final String[] RESTRICTIONS = {
			"drug.id = #{drugList.drug.id}",

			"lower(drug.name) like concat(lower(#{drugList.drug.name}),'%')",

			"drug.dateCreated <= #{drugList.dateCreatedRange.end}",
			"drug.dateCreated >= #{drugList.dateCreatedRange.begin}",};

	@Observer("archivedDrug")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Drug e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
