package com.nas.recovery.websvc.employee;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.employee.Department;

@WebService(endpointInterface = "com.nas.recovery.websvc.employee.DepartmentWebService", serviceName = "DepartmentWebService")
@Name("departmentWebService")
public class DepartmentWebServiceImpl implements DepartmentWebService {

	@In(create = true)
	com.nas.recovery.web.action.employee.DepartmentAction departmentAction;

	public Department loadById(Long id) {
		return departmentAction.loadFromId(id);
	}

	public List<Department> findByExample(Department exampleDepartment) {
		return departmentAction.search(exampleDepartment);
	}

	public void save(Department department) {
		departmentAction.persist(department);
	}

}
