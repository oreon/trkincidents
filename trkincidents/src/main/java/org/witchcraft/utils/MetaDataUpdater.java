package org.witchcraft.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.nas.recovery.MetaData;
import com.oreon.trkincidents.custm.MetaEntity;
import com.oreon.trkincidents.custm.MetaField;

public class MetaDataUpdater {
	
	private static final String NOMBRE_PERSISTENCE_UNIT = "appEntityManager";

	//@Create
	public synchronized void start() {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(NOMBRE_PERSISTENCE_UNIT);
		EntityManager em = emf.createEntityManager();

		for (String[] arr : MetaData.ARR_FIELDS) {
			MetaEntity metaEntity = new MetaEntity();
			metaEntity.setName(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				MetaField metaField = new MetaField();
				metaField.setName(arr[i]);
				metaEntity.getMetaFields().add(metaField);
				metaField.setMetaEntity(metaEntity);
			}
			try {

				//metaEntityAction.persist(metaEntity);
				em.getTransaction().begin();
				em.persist(metaEntity);
				em.getTransaction().commit();

			} catch (Exception e) {
				System.out.println(e.getMessage());
				em.getTransaction().rollback();
			}
		}
	}

	public static void main(String[] args) {
		new MetaDataUpdater().start();
	}

}
