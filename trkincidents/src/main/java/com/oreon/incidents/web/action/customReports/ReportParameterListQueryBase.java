package com.oreon.incidents.web.action.customReports;

import com.oreon.incidents.customReports.ReportParameter;

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

import com.oreon.incidents.customReports.ReportParameter;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ReportParameterListQueryBase
		extends
			BaseQuery<ReportParameter, Long> {

	private static final String EJBQL = "select reportParameter from ReportParameter reportParameter";

	protected ReportParameter reportParameter = new ReportParameter();

	public ReportParameter getReportParameter() {
		return reportParameter;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ReportParameter> getEntityClass() {
		return ReportParameter.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"reportParameter.id = #{reportParameterList.reportParameter.id}",

			"reportParameter.customReport.id = #{reportParameterList.reportParameter.customReport.id}",

			"reportParameter.metaField.id = #{reportParameterList.reportParameter.metaField.id}",

			"reportParameter.comparison = #{reportParameterList.reportParameter.comparison}",

			"reportParameter.mandatory = #{reportParameterList.reportParameter.mandatory}",

			"lower(reportParameter.defaultValue) like concat(lower(#{reportParameterList.reportParameter.defaultValue}),'%')",

			"reportParameter.dateCreated <= #{reportParameterList.dateCreatedRange.end}",
			"reportParameter.dateCreated >= #{reportParameterList.dateCreatedRange.begin}",};

	public List<ReportParameter> getReportParametersByCustomReport(
			com.oreon.incidents.customReports.CustomReport customReport) {
		//setMaxResults(10000);
		reportParameter.setCustomReport(customReport);
		return getResultList();
	}

	@Observer("archivedReportParameter")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ReportParameter e) {

		builder.append("\""
				+ (e.getCustomReport() != null ? e.getCustomReport()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getMetaField() != null ? e.getMetaField().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getComparison() != null ? e.getComparison() : "") + "\",");

		builder.append("\""
				+ (e.getMandatory() != null ? e.getMandatory() : "") + "\",");

		builder.append("\""
				+ (e.getDefaultValue() != null ? e.getDefaultValue().replace(
						",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("CustomReport" + ",");

		builder.append("MetaField" + ",");

		builder.append("Comparison" + ",");

		builder.append("Mandatory" + ",");

		builder.append("DefaultValue" + ",");

		builder.append("\r\n");
	}
}
