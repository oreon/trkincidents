package  org.witchcraft.action.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.seam.mock.SeamTest;
import org.testng.annotations.AfterClass;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.seam.action.BaseAction;

public abstract class BaseTest<T extends BusinessEntity> extends SeamTest{
	
	private static final String NOMBRE_PERSISTENCE_UNIT = "appEntityManager";
	private EntityManagerFactory emf;
	private EntityManager em;

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	
	public void init() {
		emf = Persistence.createEntityManagerFactory(NOMBRE_PERSISTENCE_UNIT);
		em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		//getAction().setEntityManager(Search.getFullTextEntityManager(em));
	}
	
	abstract public BaseAction<T> getAction();

	@AfterClass
	public void destroy() {
	//	em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
