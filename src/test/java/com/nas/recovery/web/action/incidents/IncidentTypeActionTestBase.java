package com.nas.recovery.web.action.incidents;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.incidents.IncidentType;
import com.oreon.incidents.web.action.incidents.IncidentTypeAction;

public class IncidentTypeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<IncidentType> {

	IncidentTypeAction incidentTypeAction = new IncidentTypeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<IncidentType> getAction() {
		return incidentTypeAction;
	}

}
