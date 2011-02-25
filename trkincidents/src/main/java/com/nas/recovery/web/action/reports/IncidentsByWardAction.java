package com.nas.recovery.web.action.reports;

import org.witchcraft.jasperreports.BaseReportAction;

import java.util.Date;
import java.util.Map;

import org.jboss.seam.annotations.Name;

import org.witchcraft.utils.DateUtils;

@Name("incidentsByWardAction")
public class IncidentsByWardAction extends BaseReportAction {

	Date fromDate = DateUtils.createDefaultDate();

	Date toDate = new Date();

	com.oreon.trkincidents.incidents.IncidentType ward;

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

	public void setWard(com.oreon.trkincidents.incidents.IncidentType ward) {
		this.ward = ward;
	}

	public com.oreon.trkincidents.incidents.IncidentType getWard() {

		return ward;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateParameters(Map map) {

		map.put("fromDate", fromDate);

		map.put("toDate", toDate);

		if (ward != null)
			map.put("ward", ward.getId() + "");

	}

}
