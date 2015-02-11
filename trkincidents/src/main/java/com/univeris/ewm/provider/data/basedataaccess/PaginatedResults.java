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

import java.util.List;

/**
 * Base class to enacpsulate total and count
 * User: jasingh
 * Date: 27/01/15
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class PaginatedResults<T> {

    private List<T> _rows;

    private Integer count;

    public PaginatedResults(final List<T> results, final Integer count) {
        this._rows = results;
        this.count = count;
    }

    public List<T> getRows() {
        return _rows;
    }


    public Integer getCount() {
        return count;
    }


}
