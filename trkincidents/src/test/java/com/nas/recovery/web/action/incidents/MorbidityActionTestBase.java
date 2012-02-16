package com.nas.recovery.web.action.incidents;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.incidents.Morbidity;
import com.oreon.incidents.web.action.incidents.MorbidityAction;

public class MorbidityActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Morbidity> {

	MorbidityAction morbidityAction = new MorbidityAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Morbidity> getAction() {
		return morbidityAction;
	}

}
