package com.nas.recovery.web.action.facility;

import com.oreon.trkincidents.facility.Facility;

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

import com.oreon.trkincidents.facility.Facility;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class FacilityListQueryBase extends BaseQuery<Facility, Long> {

	private static final String EJBQL = "select facility from Facility facility";

	protected Facility facility = new Facility();

	public Facility getFacility() {
		return facility;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Facility> getEntityClass() {
		return Facility.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"facility.id = #{facilityList.facility.id}",

			"lower(facility.name) like concat(lower(#{facilityList.facility.name}),'%')",

			"facility.dateCreated <= #{facilityList.dateCreatedRange.end}",
			"facility.dateCreated >= #{facilityList.dateCreatedRange.begin}",};

	@Observer("archivedFacility")
	public void onArchive() {
		refresh();
	}

}
