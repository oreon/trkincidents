package com.nas.recovery.web.action.incidents;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.incidents.Proccedure;
import com.oreon.incidents.web.action.incidents.ProccedureAction;

public class ProccedureActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Proccedure> {

	ProccedureAction proccedureAction = new ProccedureAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Proccedure> getAction() {
		return proccedureAction;
	}

}
