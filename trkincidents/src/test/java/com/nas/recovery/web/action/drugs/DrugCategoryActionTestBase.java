package com.nas.recovery.web.action.drugs;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.drugs.DrugCategory;
import com.oreon.incidents.web.action.drugs.DrugCategoryAction;

public class DrugCategoryActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<DrugCategory> {

	DrugCategoryAction drugCategoryAction = new DrugCategoryAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<DrugCategory> getAction() {
		return drugCategoryAction;
	}

}
