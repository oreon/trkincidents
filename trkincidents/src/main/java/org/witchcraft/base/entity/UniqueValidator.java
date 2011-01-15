package org.witchcraft.base.entity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.mapping.Property;
import org.hibernate.validator.PropertyConstraint;
import org.hibernate.validator.Validator;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;
import org.witchcraft.seam.action.BaseAction;



@Name("UniqueValidator")
public class UniqueValidator implements Validator<Unique>, PropertyConstraint {

	private static final long serialVersionUID = -1458203631809206211L;

	
	
	//Entity for which validation is to be fired
    private String targetEntity;
   //Field for which validation is to be fired.
    private String field;
    
    private String idProvider;

	public void initialize(Unique parameters) {
		targetEntity = ((Unique)parameters).entityName();
		field = ((Unique)parameters).fieldName();
		idProvider = ((Unique)parameters).idProvider();
	}

	public boolean isValid(Object value) {
		//return true;
		
		BaseAction<BusinessEntity> action = (BaseAction<BusinessEntity>) Component.getInstance(idProvider);
		
		boolean isEdit = action.getId() != null;
		
		EntityManager entityManager = (EntityManager) Component.getInstance("entityManager");
		entityManager.setFlushMode(javax.persistence.FlushModeType.COMMIT); 
		
		Query query = entityManager.createQuery("select t from " + targetEntity
				+ " t where t." + field + "  = :value" + (isEdit ? " and t.id != :id" : "") );
		query.setParameter("value", value);
		if(isEdit)
			query.setParameter("id", action.getId());

		try {
			query.getSingleResult();
			return false;
		} catch (final NoResultException e) {
			return true;
		}finally{
			entityManager.flush();
		}
	}

	public void apply(Property arg0) {
		// TODO Auto-generated method stub
		
	}

	public String getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(String idProvider) {
		this.idProvider = idProvider;
	}
	
	
}