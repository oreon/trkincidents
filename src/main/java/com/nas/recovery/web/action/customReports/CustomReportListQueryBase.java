package com.nas.recovery.web.action.customReports;

import com.oreon.trkincidents.customReports.CustomReport;

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

import com.oreon.trkincidents.customReports.CustomReport;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CustomReportListQueryBase
		extends
			BaseQuery<CustomReport, Long> {

	private static final String EJBQL = "select customReport from CustomReport customReport";

	protected CustomReport customReport = new CustomReport();

	public CustomReport getCustomReport() {
		return customReport;
	}

	private com.oreon.trkincidents.customReports.MetaField fieldsToSearch;

	public void setFieldsToSearch(
			com.oreon.trkincidents.customReports.MetaField metaFieldToSearch) {
		this.fieldsToSearch = metaFieldToSearch;
	}

	public com.oreon.trkincidents.customReports.MetaField getFieldsToSearch() {
		return fieldsToSearch;
	}

	private com.oreon.trkincidents.customReports.MetaField groupFieldsToSearch;

	public void setGroupFieldsToSearch(
			com.oreon.trkincidents.customReports.MetaField metaFieldToSearch) {
		this.groupFieldsToSearch = metaFieldToSearch;
	}

	public com.oreon.trkincidents.customReports.MetaField getGroupFieldsToSearch() {
		return groupFieldsToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<CustomReport> getEntityClass() {
		return CustomReport.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"customReport.id = #{customReportList.customReport.id}",

			"#{customReportList.fieldsToSearch} in elements(customReport.fields)",

			"#{customReportList.groupFieldsToSearch} in elements(customReport.groupFields)",

			"lower(customReport.name) like concat(lower(#{customReportList.customReport.name}),'%')",

			"customReport.metaEntity.id = #{customReportList.customReport.metaEntity.id}",

			"customReport.dateCreated <= #{customReportList.dateCreatedRange.end}",
			"customReport.dateCreated >= #{customReportList.dateCreatedRange.begin}",};

	@Observer("archivedCustomReport")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, CustomReport e) {

		builder.append("\"" + (e.getFields() != null ? e.getFields() : "")
				+ "\",");

		builder.append("\""
				+ (e.getGroupFields() != null ? e.getGroupFields() : "")
				+ "\",");

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getMetaEntity() != null ? e.getMetaEntity()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Fields" + ",");

		builder.append("GroupFields" + ",");

		builder.append("Name" + ",");

		builder.append("MetaEntity" + ",");

		builder.append("\r\n");
	}
}
