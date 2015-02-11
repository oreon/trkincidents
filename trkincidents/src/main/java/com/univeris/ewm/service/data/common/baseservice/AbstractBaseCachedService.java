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
import java.util.List;

import com.univeris.ewm.dataobjects.support.BaseDto;
import com.univeris.ewm.provider.data.basedataaccess.ProviderCommons;
import com.universe.base.basedataaccess.BaseEntity;

/**
 * Services for entities that use cache should inherit from this class instead of AbstractBaseService
 * User: jasingh
 * Date: 23/01/15
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
//public abstract class AbstractBaseCachedService<D extends BaseDto, K extends Serializable, T extends BaseEntity,
//        P extends ProviderCommons>  extends AbstractBaseService<D,K,T,P>  implements Cacheable {
//
//
//    @Override
//    @FunctionalSecurity
//    public D save(final D d) {
//        D newd = (D) super.save(d);
//        removeFromCache();
//        return newd;
//    }
//
//    @Override
//    @FunctionalSecurity
//    public void delete(final K id) {
//        super.delete(id);
//        removeFromCache();
//    }
//
//    @Override
//    @FunctionalSecurity
//    public List<D> getAll() {
//        return entityToDto(findFromCache());
//    }
//
//
//
//    protected void removeFromCache(){
//        getCache().remove(getCacheKey());
//    }
//
//    protected void updateCache(){
//
//    }
//
//    protected List<T> findFromCache(){
//        List<T> entities = (List<T>)getCache().get(getCacheKey());
//        if(entities == null) {
//            entities = super.getAllEntities();
//            getCache().put(getCacheKey(), entities);
//        }
//        return  entities;
//    }
//}
