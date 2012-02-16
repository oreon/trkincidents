package com.nas.recovery.web.action.customReports;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.customReports.MetaEntity;
import com.oreon.incidents.web.action.customReports.MetaEntityAction;

public class MetaEntityActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<MetaEntity> {

	MetaEntityAction metaEntityAction = new MetaEntityAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<MetaEntity> getAction() {
		return metaEntityAction;
	}

}
