package com.nas.recovery.web.action.incidents;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.incidents.Severity;
import com.oreon.incidents.web.action.incidents.SeverityAction;

public class SeverityActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Severity> {

	SeverityAction severityAction = new SeverityAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Severity> getAction() {
		return severityAction;
	}

}
