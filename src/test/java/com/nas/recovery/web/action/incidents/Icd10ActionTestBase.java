package com.nas.recovery.web.action.incidents;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.trkincidents.incidents.Icd10;

public class Icd10ActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Icd10> {

	Icd10Action icd10Action = new Icd10Action();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Icd10> getAction() {
		return icd10Action;
	}

}
