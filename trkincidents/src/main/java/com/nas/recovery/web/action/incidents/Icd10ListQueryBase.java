package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.Icd10;

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

import com.oreon.trkincidents.incidents.Icd10;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class Icd10ListQueryBase extends BaseQuery<Icd10, Long> {

	private static final String EJBQL = "select icd10 from Icd10 icd10";

	protected Icd10 icd10 = new Icd10();

	public Icd10 getIcd10() {
		return icd10;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Icd10> getEntityClass() {
		return Icd10.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"icd10.id = #{icd10List.icd10.id}",

			"lower(icd10.code) like concat(lower(#{icd10List.icd10.code}),'%')",

			"lower(icd10.description) like concat(lower(#{icd10List.icd10.description}),'%')",

			"icd10.dateCreated <= #{icd10List.dateCreatedRange.end}",
			"icd10.dateCreated >= #{icd10List.dateCreatedRange.begin}",};

	@Observer("archivedIcd10")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Icd10 e) {

		builder.append("\""
				+ (e.getCode() != null ? e.getCode().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Code" + ",");

		builder.append("Description" + ",");

		builder.append("\r\n");
	}
}
