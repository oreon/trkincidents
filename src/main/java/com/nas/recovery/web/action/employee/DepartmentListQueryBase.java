package com.nas.recovery.web.action.employee;

import com.oreon.trkincidents.employee.Department;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.oreon.trkincidents.employee.Department;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DepartmentListQueryBase
		extends
			BaseQuery<Department, Long> {

	private static final String EJBQL = "select department from Department department";

	protected Department department = new Department();

	public Department getDepartment() {
		return department;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Department> getEntityClass() {
		return Department.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"department.id = #{departmentList.department.id}",

			"lower(department.name) like concat(lower(#{departmentList.department.name}),'%')",

			"department.dateCreated <= #{departmentList.dateCreatedRange.end}",
			"department.dateCreated >= #{departmentList.dateCreatedRange.begin}",};

	@Observer("archivedDepartment")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Department e) {

		builder.append("\"" + (e.getName() != null ? e.getName() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
