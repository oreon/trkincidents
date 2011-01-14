package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.PrivacyBreech;

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

import com.oreon.trkincidents.incidents.PrivacyBreech;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class PrivacyBreechListQueryBase
		extends
			BaseQuery<PrivacyBreech, Long> {

	private static final String EJBQL = "select privacyBreech from PrivacyBreech privacyBreech";

	protected PrivacyBreech privacyBreech = new PrivacyBreech();

	public PrivacyBreech getPrivacyBreech() {
		return privacyBreech;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<PrivacyBreech> getEntityClass() {
		return PrivacyBreech.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"privacyBreech.id = #{privacyBreechList.privacyBreech.id}",

			"privacyBreech.severity.id = #{privacyBreechList.privacyBreech.severity.id}",

			"lower(privacyBreech.description) like concat(lower(#{privacyBreechList.privacyBreech.description}),'%')",

			"privacyBreech.dateCreated <= #{privacyBreechList.dateCreatedRange.end}",
			"privacyBreech.dateCreated >= #{privacyBreechList.dateCreatedRange.begin}",};

	@Observer("archivedPrivacyBreech")
	public void onArchive() {
		refresh();
	}

}
