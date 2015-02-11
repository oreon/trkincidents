/*
 * Copyright (c) 2015 Univeris Corporation. All Rights Reserved.
 *
 * This computer program constitutes confidential and proprietary information of Univeris Corporation
 * and is protected by copyright and other intellectual property laws.  Unless you have entered into a
 * written agreement with Univeris Corporation granting you rights to use this computer program in
 * source code form, you have no rights, and are not authorized, to possess, view, copy, distribute or
 * use this computer program in the form attached in any manner whatsoever and must promptly return this
 * program and all copies thereof in your possession or control to Univeris Corporation.
 * Unauthorized possession, viewing, copying, distribution or use of this computer program or any portion
 * thereof may result in liability and will be prosecuted to the maximum extent possible under the law.
 *
 * File Id: $Id$
 * Current Revision: $Rev$
 * Last Modified: $LastChangedDate$
 * Last Modified By: $LastChangedBy$.
 */

package com.univeris.ewm.provider.data.basedataaccess;

//import com.univeris.uif.core.manager.provider.ProviderBase;
import com.universe.base.basedataaccess.BaseEntity;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.exception.ConstraintViolationException;


import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@Stateless
// @Local(CrudService.class)
//@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AbstractBaseProvider<T extends BaseEntity, K extends Serializable> /*extends ProviderBase*/ {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private Class<T> persistentClass;

    public AbstractBaseProvider() {
        this.persistentClass = (Class<T>)((ParameterizedType)getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T create(T t) {
        this.entityManager.persist(t);
       // this.em.flush();
       // this.em.refresh(t);
        return t;
    }

    public T findById(K id) {
        return entityManager.find(persistentClass, id);
    }

    public void delete(K id) {
        T ref = findById(id);
        if(ref == null) {
            throw new IllegalArgumentException("Entity with id " + id + " not found !");
        }
        this.entityManager.remove(ref);
    }

    public List<T> getAll() {
        String qry = String.format(" SELECT %s FROM %s %s ", "e", getClassName(), "e");
        return getEntityManager().createQuery(qry).getResultList();
    }

    public Long getCount(){
        String qry = String.format(" SELECT COUNT(e) FROM %s e ",  getClassName() );
        return (Long)getEntityManager().createQuery(qry).getSingleResult();
    }

    protected String getClassName() {
        T t = getInstanceOf();
        String name = t.getClass().getSimpleName();
        if(name.indexOf("$$") > 0) {
            name = name.substring(0, name.indexOf("$$"));
        }
        return name;
    }

    public T update(T t) {
        try {
            t = (T)this.entityManager.merge(t);
        } catch(OptimisticLockException ole) {
            //todo:
        } catch(ConstraintViolationException cve) {

        } catch(Exception e){

        }

        return t;
    }

    public Long getRowCount(){
        return null;
    }

    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public PaginatedResults<T> findObjects(int start, int max, String sortField, boolean desc, T t){


        //if("conservativeIndicator".equalsIgnoreCase(sortField))
        //TODO : field names should come from some map in base classes as they may be different in dto vs entity
        //sortField = "_conservativeRanking";

        if(!sortField.startsWith("_"))
            sortField = "_" + sortField;

        if(t == null){
            String qry = String.format(" SELECT %s FROM %s %s " , "e", getClassName(), "e" );

            if( null != sortField){
                String orderByClause = String.format(" order by %s  %s " , "e." + sortField, desc ? "desc":"asc" );
                qry = qry.concat( orderByClause );
            }

            List <T> results = getEntityManager().createQuery(qry).setMaxResults(max).setFirstResult(start).getResultList() ;
            return new PaginatedResults<T>(results,  getCount().intValue() );

        }

        Criteria criteria = createBaseCriteria ( sortField, desc, t)
                .setFirstResult(start)
                .setMaxResults(max);


        Number rowCount = ((Number)createBaseCriteria ( sortField, desc, t).setProjection(Projections.rowCount()).uniqueResult());

        Integer totalResult =   (null != rowCount? rowCount.intValue() : 0 );

        return new PaginatedResults<T>(criteria.list(), totalResult);
    }

    private Criteria createBaseCriteria(String sortField, boolean desc, T t ){

        Criteria criteria =  ((Session)getEntityManager().getDelegate()).createCriteria(getClass()) ;

        if(t != null){
            Example exampleObject  = Example.create(t);
            criteria.add(exampleObject);
        }

        if("name".equals(sortField))
            sortField = "code";

        //if(null != sortField)
        //       criteria.addOrder(  desc ? Property.forName(sortField).asc() : Property.forName(sortField).desc()  ) ;

        return criteria;
    }

    public T getInstanceOf() {
        ParameterizedType superClass = (ParameterizedType)getClass().getGenericSuperclass();
        Class<T> type = (Class<T>)superClass.getActualTypeArguments()[0];
        try {
            return type.newInstance();
        } catch(Exception e) {
            // Oops, no default constructor
            throw new RuntimeException(e);
        }
    }

    public List<T> findLikeMe(T exampleInstance) {
        Session session = (Session)entityManager.getDelegate();
        Criteria crit = session.createCriteria(persistentClass);
        Example example = Example.create(exampleInstance);
        /*
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        } */
        crit.add(example);
        return crit.list();
    }

    /**
     * create if new else update
     *
     * @param t
     * @return
     */
    public T save(T t) {
        if(null == t.getPK()) {
            return create(t);
        } else {
            return update(t);
        }

    }

    @SuppressWarnings("unchecked")
    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public T find(Class type, Object id) {
        return (T)this.entityManager.find(type, id);
    }

    @SuppressWarnings("unchecked")
    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findWithNamedQuery(String namedQueryName) {
        return this.entityManager.createNamedQuery(namedQueryName).getResultList();
    }

    @SuppressWarnings("unchecked")
    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findWithNamedQuery(String namedQueryName, Map parameters) {
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }

    @SuppressWarnings("unchecked")
    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findWithNamedQuery(String queryName, int resultLimit) {
        return this.entityManager.createNamedQuery(queryName).setMaxResults(resultLimit)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findByNativeQuery(String sql, Class type) {
        return this.entityManager.createNativeQuery(sql, type).getResultList();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findWithNamedQuery(String namedQueryName, Map parameters,
                                      int resultLimit) {
        Set<Object> rawParameters = parameters.entrySet();

        Query query = this.entityManager.createNamedQuery(namedQueryName);
        if(resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for(Object object : rawParameters) {
            query.setParameter(String.valueOf(object), parameters.get(object));
        }

        return query.getResultList();
    }

    /**
     * Validates the given entity. If the validation fails an
     * {@link ValidationException} is thrown.
     *
     * @param entity
     *            the entity to validate
     */
//	@SuppressWarnings("unchecked")
//	protected void validate(T entity) {
//
//		if (entity != null) {
//			ClassValidator validator = new ClassValidator(entity.getClass());
//			// get the invalid values
//			InvalidValue[] invalidValues = validator.getInvalidValues(entity);
//			if (invalidValues.length > 0) {
//				List<String> messages = new ArrayList<String>();
//				for (InvalidValue value : invalidValues) {
//					String message = value.getPropertyName() + ": "
//							+ value.getMessage();
//					// add an error message for each invalid value, these
//					// messages will be shown to the user
//					messages.add(message);
//				}
//
//				throw new ValidationException(messages);
//			}
//		}
//	}
}