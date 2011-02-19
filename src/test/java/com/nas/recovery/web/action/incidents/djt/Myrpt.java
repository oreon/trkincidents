package com.nas.recovery.web.action.incidents.djt;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

import com.nas.recovery.web.action.incidents.ReportExporter;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

public class Myrpt extends BaseReportsTest {

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

		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER);
		oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
		oddRowStyle.setTransparency(Transparency.OPAQUE);
  
        DynamicReportBuilder drb = new DynamicReportBuilder();
		int margin = 20;
		drb
				.setTitleStyle(titleStyle)
				.setTitle("Incidents report")
				.setDetailHeight(new Integer(15)).setLeftMargin(margin)
				.setMargins(margin, margin, margin, margin)
				.setPrintColumnNames(false).setOddRowBackgroundStyle(
						oddRowStyle);

		try {
			AbstractColumn colTitle = ColumnBuilder.getNew().setColumnProperty(
					"title", String.class.getName()).setTitle("Title")
					.setWidth(new Integer(55)).setStyle(detailStyle)
					.setHeaderStyle(headerStyle).build();

			AbstractColumn colDesc = ColumnBuilder.getNew().setColumnProperty("description", String.class.getName())
					.setTitle("Description")
					.setWidth(new Integer(85)).setStyle(detailStyle)
					.setHeaderStyle(headerStyle).build();

			drb.addColumn(colTitle);
			drb.addColumn(colDesc);
			
			drb.setQuery("select e from Incident e" , "ejbql");
			
			drb.setUseFullPageWidth(true);

		} catch (ColumnBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//JRDataSource ds = getDataSource();

		/**
		 * Creates the JasperReport object, we pass as a Parameter the
		 * DynamicReport, a new ClassicLayoutManager instance (this one does the
		 * magic) and the JRDataSource
		 */
		try {
			jr = DynamicJasperHelper.generateJasperReport(drb.build(), getLayoutManager(), params);
			
			jp = JasperFillManager.fillReport(jr, params);

			// log.debug("Filling done!");
			// log.debug("Exporting the report (pdf, xls, etc)");
			exportToHTML(jp);
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * Creates the JasperPrint object, we pass as a Parameter the
		 * JasperReport object, and the JRDataSource 
		 */
		// log.debug("Filling the report");
	
	

	}

	protected static LayoutManager getLayoutManager() {
		return new ClassicLayoutManager();
	}
	
	protected static void exportToHTML(JasperPrint jp) throws Exception {
		ReportExporter.exportReport(jp, System.getProperty("user.dir")+ "/resources/reports/" + /*this.getClass().getName()*/ "Myrpt"  + ".pdf");
	}

}
