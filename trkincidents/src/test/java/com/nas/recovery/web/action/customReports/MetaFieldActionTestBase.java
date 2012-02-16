package com.nas.recovery.web.action.customReports;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.customReports.MetaField;
import com.oreon.incidents.web.action.customReports.MetaFieldAction;

public class MetaFieldActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<MetaField> {

	MetaFieldAction metaFieldAction = new MetaFieldAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<MetaField> getAction() {
		return metaFieldAction;
	}

}
