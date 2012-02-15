package org.witchcraft.jasperreports;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.international.StatusMessage.Severity;
import org.witchcraft.exceptions.ContractViolationException;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;

import com.nas.recovery.web.action.customReports.CustomReportAction;
import com.oreon.trkincidents.customReports.CustomReport;
import com.oreon.trkincidents.customReports.MetaField;

@Name("customReportExecutorAction")
public class CustomReportExecutorAction extends BaseReportAction {

	@In(create = true)
	CustomReportAction customReportAction;

	public static final String[] TYPES = { "String", "Date", "Long", "Integer",
			"Double", "Boolean", "Float" };

	public void runReport(Long reportId) {
		try {
			doRunReport(reportId);
		} catch (Exception e) {
			statusMessages.add(Severity.ERROR, "Error Running Report: " + e.getMessage() );
		}
	}

	public void doRunReport(Long reportId) {
		CustomReport report = customReportAction.loadFromId(reportId);

		JasperPrint jp;
		JasperReport jr;
		Map params = new HashMap();
		Style detailStyle = new Style();
		
		String qry = "";

		Style headerStyle = new Style();
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setBorder(Border.PEN_2_POINT);
		headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		detailStyle.setVerticalAlign(VerticalAlign.TOP);

		Style titleStyle = new Style();
		titleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));
		Style importeStyle = new Style();
		importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);

		params.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER,
				getEntityManager());

		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER);
		oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
		// oddRowStyle.setTransparency(Transparency.OPAQUE);

		DynamicReportBuilder drb = new DynamicReportBuilder();
		int margin = 20;
		drb

		.setTitle(report.getName()).setDefaultStyles(titleStyle, null,
				headerStyle, detailStyle).setHeaderHeight(15).setMargins(
				margin, margin, margin, margin).setPrintBackgroundOnOddRows(
				true);

		try {

			drb.setUseFullPageWidth(true);

			Set<MetaField> fields = report.getFields();

			for (MetaField metaField : fields) {
				drb.addColumn(createColumn(metaField));
			}

			// drb.addColumn(createColumn("description"));
			// drb.addColumn(createColumn("dateOfIncident"));
			// drb.addColumn(createColumn("reportedTo.displayName"));

			Set<MetaField> groupFields = report.getGroupFields();
			for (MetaField metaField : groupFields) {
				drb.addGroup(createGroup(metaField, drb));
			}

			String entity = report.getMetaEntity().getName();
			
			qry = "select e from " + entity + " e " + getOrderby(groupFields);
			
			drb.setQuery(qry, "ejbql");

		} catch (Exception e) {

			throw new ContractViolationException(
					"Error building report columns ", e);
		}

		/**
		 * Creates the JasperReport object, we pass as a Parameter the
		 * DynamicReport, a new ClassicLayoutManager instance (this one does the
		 * magic) and the JRDataSource
		 */

		try {
			jr = DynamicJasperHelper.generateJasperReport(drb.build(),
					getLayoutManager(), params);
		} catch (Exception e) {
			throw new ContractViolationException("Error creating reprot with query " + qry, e);
		}

		sendReportToPdf(jr, params);
		// jp = JasperFillManager.fillReport(jr, params);
	}

	private String getOrderby(Set<MetaField> groupFields) {
		if (groupFields.isEmpty())
			return "  ";
		else {

			StringBuilder orderString = new StringBuilder();
			int size = groupFields.size();
			int index = 0;
			for (MetaField metaField : groupFields) {
				orderString.append("e." + metaField.getName());
				if (++index < size)
					orderString.append(",");
			}

			return " order by  " + orderString.toString();
		}

	}

	/*
	 * protected void exportToHTML(JasperPrint jp) throws Exception {
	 * ReportExporter.exportReport(jp, System.getProperty("user.dir") +
	 * "/src/main/resources/reports/" + this.getClass().getName() + ".pdf"); }
	 */

	public static AbstractColumn createColumn(MetaField field) throws Exception {
		return createColumn(field, true);
	}

	public static AbstractColumn createColumn(MetaField field, boolean showText)
			throws Exception {

		Class cls = Class.forName(field.getMetaEntity().getName());
		Field[] flds = cls.getDeclaredFields();
		for (Field field2 : flds) {
			System.out.println(field2.getName());
		}
		Field typefield = cls.getDeclaredField(field.getName());

		AbstractColumn col = ColumnBuilder.getNew().setColumnProperty(
				getFieldName(field),
				(Arrays.asList(TYPES).contains(
						typefield.getType().getSimpleName())
						|| typefield.getType().isPrimitive() ? typefield
						.getType().getName() : String.class.getName()))
				.setTitle(StringUtils.capitalize(field.getName())).setShowText(
						showText).build();

		return col;
	}

	public static String getFieldName(MetaField fld) {
		Field field = null;

		try {

			Class cls = Class.forName(fld.getMetaEntity().getName());
			field = cls.getDeclaredField(fld.getName());

		} catch (SecurityException e) {
			throw new ContractViolationException("cant access field "
					+ fld.getName());
		} catch (NoSuchFieldException e) {
			throw new ContractViolationException("no such field "
					+ fld.getName());
		} catch (ClassNotFoundException e) {
			throw new ContractViolationException("no such field "
					+ fld.getName());
		}

		if (Arrays.asList(TYPES).contains(field.getType().getSimpleName())
				|| field.getType().isPrimitive()) {
			return fld.getName();
		}
		return fld.getName() + ".displayName";
	}

	public static DJGroup createGroup(MetaField field, DynamicReportBuilder drb) {

		AbstractColumn colDepartment;
		try {
			colDepartment = createColumn(field, false);
			drb.addColumn(colDepartment);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		GroupBuilder gb1 = new GroupBuilder();
		// define the criteria column to group by (columnState)
		DJGroup grp = gb1.setCriteriaColumn((PropertyColumn) colDepartment)
				.addFooterVariable(colDepartment, DJCalculation.COUNT)
				.setGroupLayout(GroupLayout.VALUE_IN_HEADER)
				.setReprintHeaderOnEachPage(false).build();
		return grp;
	}

	/*
	 * private static AbstractColumn createGrpColumn(String property, String
	 * title) throws ColumnBuilderException { AbstractColumn col =
	 * ColumnBuilder.getNew() .setColumnProperty(property,
	 * String.class.getName()).setTitle(title) .setShowText(false).build();
	 * return col; }
	 */

	protected static LayoutManager getLayoutManager() {
		return new ClassicLayoutManager();
	}

	@Override
	public void updateParameters(Map map) {
		// TODO Auto-generated method stub

	}

}
