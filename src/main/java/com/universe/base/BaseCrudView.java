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

package com.universe.base;


import java.io.Serializable;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;

import com.univeris.ewm.dataobjects.support.BaseDto;
import com.univeris.ewm.service.data.common.baseservice.ServiceCommons;

/**
 * Created with IntelliJ IDEA.
 * User: jasingh
 * Date: 22/01/15
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseCrudView<D extends BaseDto, S extends ServiceCommons, K extends Serializable>  {

    public abstract S getMyService();

    private ServiceBasedDataModel<D, K> _modifiableDataModel;

    private D _currentInstance;

    private D _searchInstance;

    public D getCurrentInstance() {
        return _currentInstance;
    }

    public void setCurrentInstance(final D currentInstance) {
        _currentInstance = currentInstance;
    }



    //TODO uncomment the following method whenever the FunctionalSecurity exception is resolved.
    /*public D findById(K id) {
        return (D)getMyService().findById(id);
    }*/

    public D save(D d) {
        return (D)getMyService().save(d);
    }

    public void delete(K id) {
        getMyService().delete(id);
    }

    public List<D> findLike(D d) {
        return getMyService().findLike(d);
    }

    public List<D> getAll(){
        return getMyService().getAll();
    }

    /**
     * cancel editing current profile
     */
    @End
    public void reset() {
        setCurrentInstance(null);
    }

    /**
     * cancel editing current profile
     */
    @End
    public D save() {
        if(_currentInstance != null) {
            D d = (D)getMyService().save(_currentInstance) ;
            reset();
            return d;
        }
        return null;
    }

    /**
     * edit an existing entity
    private D _currentInstance;
     *
     * @param d The profile to edit
     */
    @Begin(join = true)
    public void edit(D d) {
        setCurrentInstance(d);
    }

    public ServiceBasedDataModel getDataModel(){
        if(_modifiableDataModel == null)
            _modifiableDataModel = new ServiceBasedDataModel<D, K>(getMyService(), getSearchInstance());
        return _modifiableDataModel;
    }



    public D getSearchInstance() {
        return _searchInstance;
    }

    public void setSearchInstance(final D searchInstance) {
        _searchInstance = searchInstance;
    }


   

}
