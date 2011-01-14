package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.EmployeeIncident;

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

import com.oreon.trkincidents.incidents.EmployeeIncident;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class EmployeeIncidentListQueryBase
		extends
			BaseQuery<EmployeeIncident, Long> {

	private static final String EJBQL = "select employeeIncident from EmployeeIncident employeeIncident";

	protected EmployeeIncident employeeIncident = new EmployeeIncident();

	public EmployeeIncident getEmployeeIncident() {
		return employeeIncident;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<EmployeeIncident> getEntityClass() {
		return EmployeeIncident.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"employeeIncident.id = #{employeeIncidentList.employeeIncident.id}",

			"employeeIncident.severity.id = #{employeeIncidentList.employeeIncident.severity.id}",

			"lower(employeeIncident.description) like concat(lower(#{employeeIncidentList.employeeIncident.description}),'%')",

			"employeeIncident.dateCreated <= #{employeeIncidentList.dateCreatedRange.end}",
			"employeeIncident.dateCreated >= #{employeeIncidentList.dateCreatedRange.begin}",};

	@Observer("archivedEmployeeIncident")
	public void onArchive() {
		refresh();
	}

}
