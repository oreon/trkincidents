package com.nas.recovery.web.action.employee;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.nas.recovery.web.action.users.UserAction;
import com.oreon.trkincidents.users.User;

//@Scope(ScopeType.CONVERSATION)
@Name("employeeAction")
public class EmployeeAction extends EmployeeActionBase implements
		java.io.Serializable {

	@In(create = true)
	UserAction userAction;

	@Override
	public String register() {

		super.save();
		return SUCCESS;
	}

	@Override
	public String retrieveCredentials() {

		String email = getInstance().getUser().getEmail();

		User user = userAction.findByUnqEmail(email);
		if (user == null) {
			statusMessages.addFromResourceBundle("noSuchUser", email);
			return "failure";
		}
		userAction.setInstance(user);

		sendMail("/mailTemplates/retrievalEmail.xhtml");
		statusMessages.addFromResourceBundle("credentialsEmailed", email);

		return SUCCESS;

	}

}
