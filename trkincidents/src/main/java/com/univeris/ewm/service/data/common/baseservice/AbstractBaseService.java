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

package com.univeris.ewm.service.data.common.baseservice;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.univeris.ewm.dataobjects.support.BaseDto;
import com.univeris.ewm.provider.data.basedataaccess.PaginatedResults;
import com.univeris.ewm.provider.data.basedataaccess.ProviderCommons;
import com.universe.base.basedataaccess.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * User: jasingh
 * Date: 22/01/15
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractBaseService<D extends BaseDto, K extends Serializable, T extends BaseEntity,
        P extends ProviderCommons>  /*implements ServiceCommons<D,K> */ {

    public abstract P getMyProvider();

    //TODO uncomment the following method whenever the FunctionalSecurity exception is resolved in AbstractBaseService.
    /*//@
    @Override
    public D findById(final K id) {
        T t = (T)getMyProvider().findById(id);
        return entityToDto(t);
    }*/

    //@
    //@Override
    public D save(final D d) {
        T t = (T)(dtoToEntity(d));
        return entityToDto((T)getMyProvider().save(t));
    }


    //@
    //@Override
    public void delete(final K id) {
        getMyProvider().delete(id);

    }


    //@
    //@Override
    public PaginatedResults<D> findObjects(int start, int max, String sortField, boolean desc, D d){

        T t = ( d != null ? dtoToEntity(d): null );

        //if(d == null)
        //    d =   getInstanceOfDto();

        PaginatedResults<T> results = getMyProvider().findObjects(start, max, sortField, desc, t);
        return  new PaginatedResults<D>(entityToDto(results.getRows()) , results.getCount());
    }




    //@
    //@Override
    public List<D> getAll() {
        List<T> entities = getMyProvider().getAll();
        return entityToDto(entities);
    }



    //@
    //@Override
    public List<D> findLike(final D d) {
        T t = (T)(dtoToEntity(d));
        return entityToDto(getMyProvider().findLikeMe(t));
    }

    //@
    public T dtoToEntity(final D d) {
        T t = (T)getMyProvider().getInstanceOf();

        try {
            BeanUtils.copyProperties(t, d);
        } catch(Exception e) {
            //TODO: handle me
        }
        return t;
    }

    //@
    public D entityToDto(final T t) {

        D d = getInstanceOfDto();

        try {
            BeanUtils.copyProperties(d, t);
        } catch(Exception e) {
            //TODO: handle me
        }
        return d;
    }

    protected List<D> entityToDto(List<T> entities){
        ArrayList<D> dtos = new ArrayList<D>();
        for(T t : entities) {
            dtos.add(entityToDto(t));
        }
        return dtos;
    }

    private D getInstanceOfDto() {
        ParameterizedType superClass = (ParameterizedType)getClass().getGenericSuperclass();
        Class<D> type = (Class<D>)superClass.getActualTypeArguments()[0];
        try {
            return type.newInstance();
        } catch(Exception e) {
            // Oops, no default constructor
            throw new RuntimeException(e);
        }
    }

    //@
   // @Override
    public List<T> getAllEntities() {
        return getMyProvider().getAll();
    }

    public Long getRowCount(){
       return null; // getMyProvider().getRowCount();
    }


    public List<D> findObjects(int start, int max, String sortField, boolean desc){
        return null;
    }
    
    
    public P locateProvider(){
    	return null;
    }
}
