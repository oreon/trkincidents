package com.nas.recovery.web.action.incidents;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.incidents.Ward;
import com.oreon.incidents.web.action.incidents.WardAction;

public class WardActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Ward> {

	WardAction wardAction = new WardAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Ward> getAction() {
		return wardAction;
	}

}
