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

package com.univeris.ewm.service.data.common.profile;

import java.util.List;

import com.univeris.ewm.dataobjects.cxp.InvestmentStrategyDto;
import com.univeris.ewm.service.data.common.baseservice.ServiceCommons;

/**
 * Investor profile service  interface
 */
//@Local
//@ServiceDefinition(defaultService = "InvestmentStrategyServiceUVS")
//@ClassVersionDetails(revision = "$Rev$", id = "$Id$")
public interface InvestmentStrategyService extends ServiceCommons<InvestmentStrategyDto, String> {

    String KEY_INVESTOR_PROFILE_DATA = "investor-profile-data";

 

}
