package com.nas.recovery.web.action.users;

import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.users.User;



//@Scope(ScopeType.CONVERSATION)
@Name("userAction")
public class UserAction extends UserActionBase implements java.io.Serializable {

	public String retrieveCredentials() {

		String email = getInstance().getEmail();

		User user = findByUnqEmail(email);
		
		if (user == null) {
			statusMessages.addFromResourceBundle("noSuchUser", email);
			return "failure";
		}
		setInstance(user);

		sendMail("/mailTemplates/retrievalEmail.xhtml");
		statusMessages.addFromResourceBundle("credentialsEmailed", email);

		return "success";

	}

}
