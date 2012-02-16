package com.oreon.incidents.web.action.incidents;

import com.oreon.incidents.incidents.Proccedure;

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

import com.oreon.incidents.incidents.Proccedure;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ProccedureListQueryBase
		extends
			BaseQuery<Proccedure, Long> {

	private static final String EJBQL = "select proccedure from Proccedure proccedure";

	protected Proccedure proccedure = new Proccedure();

	public Proccedure getProccedure() {
		return proccedure;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Proccedure> getEntityClass() {
		return Proccedure.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"proccedure.id = #{proccedureList.proccedure.id}",

			"lower(proccedure.name) like concat(lower(#{proccedureList.proccedure.name}),'%')",

			"proccedure.dateCreated <= #{proccedureList.dateCreatedRange.end}",
			"proccedure.dateCreated >= #{proccedureList.dateCreatedRange.begin}",};

	@Observer("archivedProccedure")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Proccedure e) {

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
