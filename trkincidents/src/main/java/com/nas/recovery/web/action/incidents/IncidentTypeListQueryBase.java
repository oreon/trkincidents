package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.IncidentType;

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

import com.oreon.trkincidents.incidents.IncidentType;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class IncidentTypeListQueryBase
		extends
			BaseQuery<IncidentType, Long> {

	private static final String EJBQL = "select incidentType from IncidentType incidentType";

	protected IncidentType incidentType = new IncidentType();

	public IncidentType getIncidentType() {
		return incidentType;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<IncidentType> getEntityClass() {
		return IncidentType.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"incidentType.id = #{incidentTypeList.incidentType.id}",

			"lower(incidentType.name) like concat(lower(#{incidentTypeList.incidentType.name}),'%')",

			"incidentType.dateCreated <= #{incidentTypeList.dateCreatedRange.end}",
			"incidentType.dateCreated >= #{incidentTypeList.dateCreatedRange.begin}",};

	@Observer("archivedIncidentType")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, IncidentType e) {

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
