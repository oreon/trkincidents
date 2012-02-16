package com.nas.recovery.web.action.drugs;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.drugs.Drug;
import com.oreon.incidents.web.action.drugs.DrugAction;

public class DrugActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Drug> {

	DrugAction drugAction = new DrugAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Drug> getAction() {
		return drugAction;
	}

}
