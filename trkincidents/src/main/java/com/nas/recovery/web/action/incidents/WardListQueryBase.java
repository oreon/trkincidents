package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.Ward;

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

import com.oreon.trkincidents.incidents.Ward;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class WardListQueryBase extends BaseQuery<Ward, Long> {

	private static final String EJBQL = "select ward from Ward ward";

	protected Ward ward = new Ward();

	public Ward getWard() {
		return ward;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Ward> getEntityClass() {
		return Ward.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"ward.id = #{wardList.ward.id}",

			"lower(ward.name) like concat(lower(#{wardList.ward.name}),'%')",

			"ward.dateCreated <= #{wardList.dateCreatedRange.end}",
			"ward.dateCreated >= #{wardList.dateCreatedRange.begin}",};

	@Observer("archivedWard")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Ward e) {

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
