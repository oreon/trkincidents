/*
 * Copyright (c) 2015 Univeris Corporation. All Rights Reserved.
 *
 * This computer program constitutes confgetPK()ential and proprietary information of Univeris Corporation
 * and is protected by copyright and other intellectual property laws.  Unless you have entered into a
 * written agreement with Univeris Corporation granting you rights to use this computer program in
 * source code form, you have no rights, and are not authorized, to possess, view, copy, distribute or
 * use this computer program in the form attached in any manner whatsoever and must promptly return this
 * program and all copies thereof in your possession or control to Univeris Corporation.
 * Unauthorized possession, viewing, copying, distribution or use of this computer program or any portion
 * thereof may result in liability and will be prosecuted to the maximum extent possible under the law.
 *
 * File getPK(): $getPK(): $
 * Current Revision: $Rev$
 * Last Modified: $LastChangedDate$
 * Last Modified By: $LastChangedBy$.
 */

package com.universe.base.basedataaccess;



import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<K> {
	
	public abstract K getPK();


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getPK() != null ? getPK().hashCode() : super.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // Warning - this method won't work in the case the getPK() fields are not set
        if (!(object instanceof BaseEntity)) {
            return false;
        }
        BaseEntity other = (BaseEntity) object;
        if ((this.getPK() == null && other.getPK() != null) || (this.getPK() != null && !this.getPK().equals(other.getPK()))) {
            return false;
        }
        return true;
    }
}
