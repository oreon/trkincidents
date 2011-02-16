package com.nas.recovery.websvc.employee;

import javax.jws.WebService;
import com.oreon.trkincidents.employee.Department;
import java.util.List;

@WebService
public interface DepartmentWebService {

	public Department loadById(Long id);

	public List<Department> findByExample(Department exampleDepartment);

	public void save(Department department);

}
