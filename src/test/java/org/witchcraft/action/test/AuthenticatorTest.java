package org.witchcraft.action.test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.oreon.incidents.employee.Department;
import com.oreon.incidents.employee.Employee;
import com.oreon.incidents.incidents.FieldType;
import com.oreon.incidents.incidents.FormField;
import com.oreon.incidents.incidents.IncidentType;
import com.oreon.incidents.incidents.Severity;
import com.oreon.incidents.users.Role;
import com.oreon.incidents.users.User;

public class AuthenticatorTest extends BaseTest<User> {

	@BeforeClass
	public void init() {
		super.init();
	}

	

	@Test(dependsOnMethods = { "testRegisterAction" })
	public void validateAuthenticationBadUser() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				Identity.instance().getCredentials().setUsername("admin");
				Identity.instance().getCredentials().setPassword("admin");

				assert invokeMethod("#{authenticator.authenticate}").equals(
						true);

			}

		}.run();
	}

	@Test
	public void testRegisterAction() throws Exception {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();

		Query query = em
				.createQuery("Select u From User u where u.userName = ?1 ");
		query.setParameter(1, "admin");
		if (!query.getResultList().isEmpty())
			return;

		new ComponentTest() {

			protected void testComponents() throws Exception {
				Identity.instance().getCredentials().setUsername("admin");
				Identity.instance().getCredentials().setPassword("admin");

				createUserAndRole("admin", "admin", "admin");
				createUserAndRole("jim", "jim", "doctor");
				createUserAndRole("roger", "roger", "clerk");
				createUserAndRole("erica", "erica", "nurse");
				createUserAndRole("Max", "Arthur", "nurse manager");
				createEmp("", "", "");
			}

		}.run();

		// em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void testCreateUser() throws Exception {
	
		new ComponentTest() {
			
			@Override
			protected void testComponents() throws Exception {
				/*
				UserAction useraction = (UserAction) Component.getInstance("userAction");
				useraction.setId(2L);
				//useraction.getEntityManager().getTransaction().begin();
				useraction.getInstance().setUserName("sss");
				useraction.save();
				useraction.getEntityManager().getTransaction().commit();*/
			}
			
		}.run();
	}
	
	
	private User createUserAndRole(String username, String password, String role) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();

		User admin = new User();
		admin.setUserName(username);
		admin.setPassword(password);
		admin.setEnabled(true);
		admin.setEmail(username + "@gmail.com");

		Role adminRole = new Role();
		adminRole.setName(role);
		admin.getRoles().add(adminRole);
		// setValue("#{userAction.instance}", admin);
		// invokeMethod("#{userAction.save}");
		em.persist(admin);
		em.getTransaction().commit();
		return admin;
	}

	private User createEmp(String username, String password, String role) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Department dep = new Department();
		dep.setName("Nursing");
		em.persist(dep);
		User user = em.find(User.class, 2L);
		Employee emp = new Employee();
		emp.setDepartment(dep);
		emp.setUser(user);
		emp.setFirstName("Alicia");
		emp.setLastName("Nickson");
		emp.setEmployeeNumber("3434");
		
		
		//setValue("#{userAction.instance}", admin);
		//invokeMethod("#{userAction.save}");
		em.persist(emp);
		
		
		IncidentType itype = new IncidentType();
		itype.setName("Violence");
		
		FormField formField = new FormField();
		formField.setName("Person Name");
		formField.setType(FieldType.TEXT);
		formField.setIncidentType(itype);
		itype.getFormFields().add(formField);
		
		FormField formField2 = new FormField();
		formField2.setName("Reported On");
	
		
		formField2.setIncidentType(itype);
		formField.setType(FieldType.DATE);
		itype.getFormFields().add(formField2);
		
		Severity sev = new Severity();
		sev.setName("Critical");
		em.persist(sev);
		
		Severity sev2 = new Severity();
		sev2.setName("Moderate");
		em.persist(sev2);
		
		Severity sev3 = new Severity();
		sev3.setName("Mild");
		em.persist(sev3);
		
		em.persist(itype);
		
		em.getTransaction().commit();
		return user;
	}



	@Override
	public Object getAction() {
		// TODO Auto-generated method stub
		return null;
	}

}