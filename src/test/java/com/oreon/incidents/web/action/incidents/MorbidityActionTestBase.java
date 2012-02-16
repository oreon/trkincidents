package com.oreon.incidents.web.action.incidents;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.incidents.incidents.Morbidity;

public class MorbidityActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Morbidity> {

	MorbidityAction morbidityAction = new MorbidityAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Morbidity> getAction() {
		return morbidityAction;
	}

}
