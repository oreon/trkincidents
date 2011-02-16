package com.nas.recovery.web.action.incidents.djt;

import java.awt.Color;
import java.util.Date;

import net.sf.jasperreports.engine.JasperPrint;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DJCrosstabColumn;
import ar.com.fdvs.dj.domain.DJCrosstabRow;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.CrosstabBuilder;
import ar.com.fdvs.dj.domain.builders.CrosstabColumnBuilder;
import ar.com.fdvs.dj.domain.builders.CrosstabRowBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.util.SortUtils;

import com.nas.recovery.web.action.incidents.ReportExporter;

public class CTrpt extends BaseReportsTest {

	// private DJCrosstab djcross;
	private Style totalHeader;
	private Style colAndRowHeaderStyle;
	private Style mainHeaderStyle;
	private Style totalStyle;
	private Style measureStyle;

	@Test
	public void mytest() {

		JasperPrint jp;
		JasperReport jr;
		Map params = new HashMap();

		Style detailStyle = new Style();

		Style headerStyle = new Style();
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setBorder(Border.PEN_2_POINT);
		headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);

		Style titleStyle = new Style();
		titleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));
		Style importeStyle = new Style();
		importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);

		params.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);

	}

	public DynamicReport buildReport() throws Exception {

		/**
		 * Creates the DynamicReportBuilder and sets the basic options for the
		 * report
		 */
		FastReportBuilder drb = new FastReportBuilder();

		drb.addColumn("State", "state", String.class.getName(), 30).addColumn(
				"Branch", "branch", String.class.getName(), 30).addColumn(
				"Product Line", "productLine", String.class.getName(), 50)
				.addColumn("Item", "item", String.class.getName(), 50)
				.addColumn("Item Code", "id", Long.class.getName(), 30, true)
				.addColumn("Quantity", "quantity", Long.class.getName(), 60,
						true).addColumn("Amount", "amount",
						Float.class.getName(), 70, true).addGroups(1)
				.addFooterVariable(1, 7, DJCalculation.SUM, null)
				.addFooterVariable(1, 6, DJCalculation.SUM, null).setTitle(
						"November 2006 sales report").setSubtitle(
						"This report was generated at " + new Date())
				.setPageSizeAndOrientation(Page.Page_A4_Landscape())
				.setUseFullPageWidth(true);
		// drb.setTemplateFile("templates/crosstab2-test.jrxml");

		initStyles();

		CrosstabBuilder cb = new CrosstabBuilder();

		cb.setHeight(200)
				.setWidth(500)
				// .setHeaderStyle(mainHeaderStyle)
				.setDatasource("sr", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER,
						DJConstants.DATA_SOURCE_TYPE_COLLECTION)
				.setUseFullWidth(true).setColorScheme(4)
				.setAutomaticTitle(true).setCellBorder(Border.PEN_1_POINT);

		cb.addMeasure("count", Integer.class.getName(), DJCalculation.COUNT,
				"Count", measureStyle);

		DJCrosstabRow row = new CrosstabRowBuilder().setProperty("productLine",
				String.class.getName()).setHeaderWidth(100).setHeight(0)
				.setTitle("Product Line").setShowTotals(true).setTotalStyle(
						totalStyle).setTotalHeaderStyle(totalHeader)
				.setHeaderStyle(colAndRowHeaderStyle).build();

		cb.addRow(row);

		row = new CrosstabRowBuilder().setProperty("item",
				String.class.getName()).setHeaderWidth(100).setHeight(20)
				.setTitle("Item").setShowTotals(true).setTotalStyle(totalStyle)
				.setTotalHeaderStyle(totalHeader).setHeaderStyle(
						colAndRowHeaderStyle).build();

		row.setTotalHeaderHeight(100);

		cb.addRow(row);

		// row = new CrosstabRowBuilder().setProperty("id",Long.class.getName())
		// .setHeaderWidth(100).setHeight(30)
		// .setTitle("ID").setShowTotals(true)
		// .setTotalStyle(totalStyle).setTotalHeaderStyle(totalHeader)
		// .setHeaderStyle(colAndRowHeaderStyle)
		// .build();

		// cb.addRow(row);

		DJCrosstabColumn col = new CrosstabColumnBuilder().setProperty("state",
				String.class.getName()).setHeaderHeight(60).setWidth(50)
				.setTitle("State").setShowTotals(true)
				.setTotalStyle(totalStyle).setTotalHeaderStyle(totalHeader)
				.setHeaderStyle(colAndRowHeaderStyle).build();

		cb.addColumn(col);

		col = new CrosstabColumnBuilder().setProperty("branch",
				String.class.getName()).setHeaderHeight(30).setWidth(60)
				.setShowTotals(true).setTitle("Branch").setTotalStyle(
						totalStyle).setTotalHeaderStyle(totalHeader)
				.setTotalLegend("Total for \"+ $V{branch}+ \"").setHeaderStyle(
						colAndRowHeaderStyle).build();

		cb.addColumn(col);

		col = new CrosstabColumnBuilder().setProperty("id",
				Long.class.getName()).setHeaderHeight(40).setWidth(70)
				.setShowTotals(true).setTitle("ID").setTotalStyle(totalStyle)
				.setTotalHeaderStyle(totalHeader).setHeaderStyle(
						colAndRowHeaderStyle).build();

		// cb.addColumn(col);

		djcross = cb.build();

		drb.addHeaderCrosstab(djcross);

		DynamicReport dr = drb.build();

		params.put("sr", SortUtils.sortCollection(TestRepositoryProducts
				.getDummyCollection(), djcross));
		return dr;
	}

	/**
	 *
	 */
	private void initStyles() {
		totalHeader = new StyleBuilder(false).setHorizontalAlign(
				HorizontalAlign.CENTER).setVerticalAlign(VerticalAlign.MIDDLE)
				.setFont(Font.ARIAL_MEDIUM_BOLD).setTextColor(Color.BLUE)
				.build();
		colAndRowHeaderStyle = new StyleBuilder(false).setHorizontalAlign(
				HorizontalAlign.LEFT).setVerticalAlign(VerticalAlign.TOP)
				.setFont(Font.ARIAL_MEDIUM_BOLD).build();
		mainHeaderStyle = new StyleBuilder(false).setHorizontalAlign(
				HorizontalAlign.CENTER).setVerticalAlign(VerticalAlign.MIDDLE)
				.setFont(Font.ARIAL_BIG_BOLD).setTextColor(Color.BLACK).build();
		totalStyle = new StyleBuilder(false).setPattern("#,###.##")
				.setHorizontalAlign(HorizontalAlign.RIGHT).setFont(
						Font.ARIAL_MEDIUM_BOLD).build();
		measureStyle = new StyleBuilder(false).setPattern("#,###.##")
				.setHorizontalAlign(HorizontalAlign.RIGHT).setFont(
						Font.ARIAL_MEDIUM).build();
	}

	protected static LayoutManager getLayoutManager() {
		return new ClassicLayoutManager();
	}

	protected static void exportToHTML(JasperPrint jp) throws Exception {
		ReportExporter.exportReportHtml(jp, System.getProperty("user.dir")
				+ "/resources/reports/" + /* this.getClass().getName() */"Myrpt"
				+ ".html");
	}

}
