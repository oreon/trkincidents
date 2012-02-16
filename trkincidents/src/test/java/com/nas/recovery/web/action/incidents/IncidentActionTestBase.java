package com.nas.recovery.web.action.incidents;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.incidents.Incident;
import com.oreon.incidents.web.action.incidents.IncidentAction;

public class IncidentActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Incident> {

	IncidentAction incidentAction = new IncidentAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Incident> getAction() {
		return incidentAction;
	}

}
