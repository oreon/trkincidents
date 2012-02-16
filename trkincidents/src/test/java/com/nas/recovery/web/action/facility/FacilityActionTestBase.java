package com.nas.recovery.web.action.facility;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.incidents.facility.Facility;
import com.oreon.incidents.web.action.facility.FacilityAction;

public class FacilityActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Facility> {

	FacilityAction facilityAction = new FacilityAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Facility> getAction() {
		return facilityAction;
	}

}
