package com.nas.recovery.web.action.incidents;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.incidents.FormFieldInstance;
import com.oreon.incidents.web.action.incidents.FormFieldInstanceAction;

public class FormFieldInstanceActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<FormFieldInstance> {

	FormFieldInstanceAction formFieldInstanceAction = new FormFieldInstanceAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<FormFieldInstance> getAction() {
		return formFieldInstanceAction;
	}

}
