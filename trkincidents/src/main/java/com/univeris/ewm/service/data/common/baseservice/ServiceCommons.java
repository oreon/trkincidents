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

import java.util.List;

import com.univeris.ewm.dataobjects.support.BaseDto;
import com.univeris.ewm.provider.data.basedataaccess.PaginatedResults;

/**
 * Created with IntelliJ IDEA.
 * User: jasingh
 * Date: 22/01/15
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ServiceCommons<D extends BaseDto, K > /* extends Service*/{

    //TODO uncomment the following line whenever the FunctionalSecurity exception is resolved in AbstractBaseService.
    //public  D findById(K Id ) ;

    public  D save(D t);
    public void delete(K id);
    public List<D> findLike(D t);
    public List<D> getAll();


    public Long getRowCount();

    public PaginatedResults<D> findObjects(int start, int max, String sortField, boolean desc, D d);


}
