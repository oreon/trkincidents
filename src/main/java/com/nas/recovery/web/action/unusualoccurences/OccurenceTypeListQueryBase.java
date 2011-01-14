package com.nas.recovery.web.action.unusualoccurences;

import com.oreon.trkincidents.unusualoccurences.OccurenceType;

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

import com.oreon.trkincidents.unusualoccurences.OccurenceType;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class OccurenceTypeListQueryBase
		extends
			BaseQuery<OccurenceType, Long> {

	private static final String EJBQL = "select occurenceType from OccurenceType occurenceType";

	protected OccurenceType occurenceType = new OccurenceType();

	public OccurenceType getOccurenceType() {
		return occurenceType;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<OccurenceType> getEntityClass() {
		return OccurenceType.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"occurenceType.id = #{occurenceTypeList.occurenceType.id}",

			"lower(occurenceType.name) like concat(lower(#{occurenceTypeList.occurenceType.name}),'%')",

			"occurenceType.dateCreated <= #{occurenceTypeList.dateCreatedRange.end}",
			"occurenceType.dateCreated >= #{occurenceTypeList.dateCreatedRange.begin}",};

	@Observer("archivedOccurenceType")
	public void onArchive() {
		refresh();
	}

}
