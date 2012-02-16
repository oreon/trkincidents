package com.nas.recovery.web.action.incidents.djt;

import java.awt.Color;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
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

import com.nas.recovery.web.action.incidents.ReportExporter;

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
		detailStyle.setVerticalAlign(VerticalAlign.TOP);

		Style titleStyle = new Style();
		titleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));
		Style importeStyle = new Style();
		importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
		
		params.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);

		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER);
		oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
		//oddRowStyle.setTransparency(Transparency.OPAQUE);
  
        DynamicReportBuilder drb = new DynamicReportBuilder();
		int margin = 20;
		drb
				
				.setTitle("Incidents report")
				.setDefaultStyles(titleStyle, null, headerStyle, detailStyle)
				.setHeaderHeight(15)
				.setMargins(margin, margin, margin, margin)
				.setPrintBackgroundOnOddRows(true);
		

		try {
			AbstractColumn colTitle = ColumnBuilder.getNew().setColumnProperty(
					"title", String.class.getName()).setTitle("Title")
					
					.build();

			AbstractColumn colDesc = ColumnBuilder.getNew().setColumnProperty("description", String.class.getName())
					.setTitle("Description")
					
					.build();
			

			AbstractColumn colDepartment = ColumnBuilder.getNew().setColumnProperty("department.displayName", String.class.getName())
					.setTitle("Department")
					.setShowText(false)
					.build();
			
			AbstractColumn colDate= ColumnBuilder.getNew().setColumnProperty("dateOfIncident", Date.class.getName())
			.setTitle("Date of Incident")
			.build();
			
			AbstractColumn colReported = ColumnBuilder.getNew().setColumnProperty("reportedTo.displayName", String.class.getName())
			.setTitle("Reported To")
			.build();
			
			
			GroupBuilder gb1 = new GroupBuilder();
			 //		 define the criteria column to group by (columnState)
	 		DJGroup g1 = gb1.setCriteriaColumn((PropertyColumn) colDepartment)
			 				.addFooterVariable(colDepartment, DJCalculation.COUNT)
			 				.setGroupLayout(GroupLayout.VALUE_IN_HEADER) // tells the group how to be shown, there are manyposibilities, see the GroupLayout for more.
			 				.setReprintHeaderOnEachPage(false)
			 				
			 				.build();
			 
			 

			drb.addColumn(colTitle);
			drb.addColumn(colDesc);
			drb.addColumn(colDate);
			drb.addColumn(colReported);
			drb.addColumn(colDepartment);
			
			drb.addGroup(g1);
			
			drb.setQuery("select e from Incident e order by e.department" , "ejbql");
			
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

	}

	protected static LayoutManager getLayoutManager() {
		return new ClassicLayoutManager();
	}
	
	protected static void exportToHTML(JasperPrint jp) throws Exception {
		ReportExporter.exportReport(jp, System.getProperty("user.dir")+ "/src/main/resources/reports/" + /*this.getClass().getName()*/ "Myrpt"  + ".pdf");
	}

}
