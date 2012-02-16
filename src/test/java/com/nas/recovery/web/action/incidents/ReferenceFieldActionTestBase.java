package com.nas.recovery.web.action.incidents;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.incidents.ReferenceField;
import com.oreon.incidents.web.action.incidents.ReferenceFieldAction;

public class ReferenceFieldActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ReferenceField> {

	ReferenceFieldAction referenceFieldAction = new ReferenceFieldAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ReferenceField> getAction() {
		return referenceFieldAction;
	}

}
