package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.BaseIncident;

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

import com.oreon.trkincidents.incidents.BaseIncident;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class BaseIncidentListQueryBase
		extends
			BaseQuery<BaseIncident, Long> {

	private static final String EJBQL = "select baseIncident from BaseIncident baseIncident";

	protected BaseIncident baseIncident = new com.oreon.trkincidents.incidents.PatientIncident();

	public BaseIncident getBaseIncident() {
		return baseIncident;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<BaseIncident> getEntityClass() {
		return BaseIncident.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"baseIncident.id = #{baseIncidentList.baseIncident.id}",

			"baseIncident.severity.id = #{baseIncidentList.baseIncident.severity.id}",

			"lower(baseIncident.description) like concat(lower(#{baseIncidentList.baseIncident.description}),'%')",

			"baseIncident.dateCreated <= #{baseIncidentList.dateCreatedRange.end}",
			"baseIncident.dateCreated >= #{baseIncidentList.dateCreatedRange.begin}",};

	@Observer("archivedBaseIncident")
	public void onArchive() {
		refresh();
	}

}
