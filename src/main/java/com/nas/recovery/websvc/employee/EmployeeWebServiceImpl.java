package com.nas.recovery.websvc.employee;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.employee.Employee;

@WebService(endpointInterface = "com.nas.recovery.websvc.employee.EmployeeWebService", serviceName = "EmployeeWebService")
@Name("employeeWebService")
public class EmployeeWebServiceImpl implements EmployeeWebService {

	@In(create = true)
	com.nas.recovery.web.action.employee.EmployeeAction employeeAction;

	public Employee loadById(Long id) {
		return employeeAction.loadFromId(id);
	}

	public List<Employee> findByExample(Employee exampleEmployee) {
		return employeeAction.search(exampleEmployee);
	}

	public void save(Employee employee) {
		employeeAction.persist(employee);
	}

	public String retrieveCredentials() {
		return employeeAction.retrieveCredentials();
	}

	public String login() {
		return employeeAction.login();
	}

	public String register() {
		return employeeAction.register();
	}

}
