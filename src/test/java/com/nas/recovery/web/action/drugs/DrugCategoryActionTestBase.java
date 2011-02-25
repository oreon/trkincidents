package com.nas.recovery.web.action.drugs;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.trkincidents.drugs.DrugCategory;

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
