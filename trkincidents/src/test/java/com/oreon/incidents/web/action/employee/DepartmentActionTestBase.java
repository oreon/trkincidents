package com.oreon.incidents.web.action.employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.incidents.employee.Department;

public class DepartmentActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Department> {

	DepartmentAction departmentAction = new DepartmentAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Department> getAction() {
		return departmentAction;
	}

}
