package com.nas.recovery.web.action.drugs;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.drugs.DrugInteraction;
import com.oreon.incidents.web.action.drugs.DrugInteractionAction;

public class DrugInteractionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<DrugInteraction> {

	DrugInteractionAction drugInteractionAction = new DrugInteractionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<DrugInteraction> getAction() {
		return drugInteractionAction;
	}

}
