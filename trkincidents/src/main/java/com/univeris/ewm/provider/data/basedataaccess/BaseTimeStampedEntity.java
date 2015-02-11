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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.universe.base.basedataaccess.BaseEntity;

@MappedSuperclass
public abstract class BaseTimeStampedEntity<K> extends BaseEntity<K> {

    //TODO: find a way to abstract these three types
    /*
	@Column(name = "CR_DT")
	private Date _createdDate;

	@Column(name = "CR_USER_SYSID")
	private Integer _createdUserId;

	@Column(name = "UPD_DT")
	private Date _updateDate;
	*/
}
