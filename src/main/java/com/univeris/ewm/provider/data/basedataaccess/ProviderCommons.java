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


import com.universe.base.basedataaccess.BaseEntity;

import java.util.List;
import java.util.Map;


public interface ProviderCommons<T extends BaseEntity, K > /* extends Provider*/ {

    public  T create(T t);
    public  T findById(K Id ) ;
    public  T update(T t);
    public  T save(T t); 
    
    public void delete(K id);


    public List<T> getAll();

    public List<T> findLikeMe(T t);

    public T getInstanceOf();


    public PaginatedResults<T>     findObjects(int start, int max, String sortField, boolean desc, T t);

    public List<T> findWithNamedQuery(String queryName);
    public List<T> findWithNamedQuery(String queryName,int resultLimit);
    public List<T> findWithNamedQuery(String namedQueryName, Map parameters);
    public List<T> findWithNamedQuery(String namedQueryName, Map parameters,int resultLimit);
}