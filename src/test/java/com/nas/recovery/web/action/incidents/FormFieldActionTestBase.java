package com.nas.recovery.web.action.incidents;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.incidents.FormField;
import com.oreon.incidents.web.action.incidents.FormFieldAction;

public class FormFieldActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<FormField> {

	FormFieldAction formFieldAction = new FormFieldAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<FormField> getAction() {
		return formFieldAction;
	}

}
