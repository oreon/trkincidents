
	
package com.oreon.incidents.web.action.employee;
	

import org.jboss.seam.annotations.Name;
import org.jboss.seam.security.Identity;

import com.oreon.incidents.employee.Employee;

	
//@Scope(ScopeType.CONVERSATION)
@Name("employeeAction")
public class EmployeeAction extends EmployeeActionBase implements java.io.Serializable{
	
	
	public Employee getCurrentLoggedInEmployee() {
		String query = "Select e from Employee e where e.user.userName = ?1";
		return (Employee) executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}
}
	