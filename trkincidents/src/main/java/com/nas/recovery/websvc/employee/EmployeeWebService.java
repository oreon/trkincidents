package com.nas.recovery.websvc.employee;

import javax.jws.WebService;
import com.oreon.trkincidents.employee.Employee;
import java.util.List;

@WebService
public interface EmployeeWebService {

	public Employee loadById(Long id);

	public List<Employee> findByExample(Employee exampleEmployee);

	public void save(Employee employee);

}
