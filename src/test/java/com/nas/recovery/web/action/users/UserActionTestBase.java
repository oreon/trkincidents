package com.nas.recovery.web.action.users;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.trkincidents.users.User;

public class UserActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<User> {

	UserAction userAction = new UserAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<User> getAction() {
		return userAction;
	}

}
