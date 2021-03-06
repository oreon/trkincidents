package com.oreon.incidents.web.action.customReports;

import com.oreon.incidents.customReports.MetaEntity;

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

import com.oreon.incidents.customReports.MetaEntity;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class MetaEntityListQueryBase
		extends
			BaseQuery<MetaEntity, Long> {

	private static final String EJBQL = "select metaEntity from MetaEntity metaEntity";

	protected MetaEntity metaEntity = new MetaEntity();

	public MetaEntity getMetaEntity() {
		return metaEntity;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<MetaEntity> getEntityClass() {
		return MetaEntity.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"metaEntity.id = #{metaEntityList.metaEntity.id}",

			"lower(metaEntity.name) like concat(lower(#{metaEntityList.metaEntity.name}),'%')",

			"metaEntity.dateCreated <= #{metaEntityList.dateCreatedRange.end}",
			"metaEntity.dateCreated >= #{metaEntityList.dateCreatedRange.begin}",};

	@Observer("archivedMetaEntity")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, MetaEntity e) {

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
