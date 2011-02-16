package com.nas.recovery.web.action.appointment;

import com.oreon.trkincidents.appointment.History;

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

import com.oreon.trkincidents.appointment.History;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class HistoryListQueryBase extends BaseQuery<History, Long> {

	private static final String EJBQL = "select history from History history";

	protected History history = new History();

	public History getHistory() {
		return history;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<History> getEntityClass() {
		return History.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"history.id = #{historyList.history.id}",

			"history.encounter.id = #{historyList.history.encounter.id}",

			"lower(history.history) like concat(lower(#{historyList.history.history}),'%')",

			"history.dateCreated <= #{historyList.dateCreatedRange.end}",
			"history.dateCreated >= #{historyList.dateCreatedRange.begin}",};

	public List<History> getHistorysByEncounter(
			com.oreon.trkincidents.appointment.Encounter encounter) {
		//setMaxResults(10000);
		history.setEncounter(encounter);
		return getResultList();
	}

	@Observer("archivedHistory")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, History e) {

		builder.append("\""
				+ (e.getEncounter() != null
						? e.getEncounter().getDisplayName()
						: "") + "\",");

		builder.append("\"" + (e.getHistory() != null ? e.getHistory() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Encounter" + ",");

		builder.append("History" + ",");

		builder.append("\r\n");
	}
}
