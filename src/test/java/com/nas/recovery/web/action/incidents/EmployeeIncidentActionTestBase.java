package com.nas.recovery.web.action.incidents;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.trkincidents.incidents.EmployeeIncident;

public class EmployeeIncidentActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<EmployeeIncident> {

	EmployeeIncidentAction employeeIncidentAction = new EmployeeIncidentAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<EmployeeIncident> getAction() {
		return employeeIncidentAction;
	}

}
