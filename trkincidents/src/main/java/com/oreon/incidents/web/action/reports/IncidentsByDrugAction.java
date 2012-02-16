package com.oreon.incidents.web.action.reports;

import org.witchcraft.jasperreports.BaseReportAction;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.beanutils.PropertyUtils;
import org.jboss.seam.annotations.Name;
import org.witchcraft.jasperreports.BaseReportAction;

import java.util.Date;
import org.witchcraft.utils.DateUtils;

import com.oreon.incidents.ProjectUtils;

@Name("incidentsByDrugAction")
public class IncidentsByDrugAction extends BaseReportAction {

	Date fromDate = DateUtils.createDefaultDate();

	Date toDate = new Date();

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getFromDate() {

		return fromDate;

	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getToDate() {

		return toDate;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateParameters(Map map) {

		map.put("fromDate", fromDate);

		map.put("toDate", toDate);

	}

}
