package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.Morbidity;

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

import com.oreon.trkincidents.incidents.Morbidity;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class MorbidityListQueryBase extends BaseQuery<Morbidity, Long> {

	private static final String EJBQL = "select morbidity from Morbidity morbidity";

	protected Morbidity morbidity = new Morbidity();

	public Morbidity getMorbidity() {
		return morbidity;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Morbidity> getEntityClass() {
		return Morbidity.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"morbidity.id = #{morbidityList.morbidity.id}",

			"lower(morbidity.name) like concat(lower(#{morbidityList.morbidity.name}),'%')",

			"lower(morbidity.code) like concat(lower(#{morbidityList.morbidity.code}),'%')",

			"morbidity.dateCreated <= #{morbidityList.dateCreatedRange.end}",
			"morbidity.dateCreated >= #{morbidityList.dateCreatedRange.begin}",};

	@Observer("archivedMorbidity")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Morbidity e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getCode() != null ? e.getCode().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Code" + ",");

		builder.append("\r\n");
	}
}
