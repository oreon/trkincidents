package com.nas.recovery.web.action.customforms;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.trkincidents.customforms.FilledField;

public class FilledFieldActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<FilledField> {

	FilledFieldAction filledFieldAction = new FilledFieldAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<FilledField> getAction() {
		return filledFieldAction;
	}

}
