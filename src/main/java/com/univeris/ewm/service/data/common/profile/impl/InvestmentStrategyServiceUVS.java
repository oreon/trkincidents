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

package com.univeris.ewm.service.data.common.profile.impl;

import javax.annotation.PostConstruct;

import com.univeris.ewm.dataobjects.cxp.InvestmentStrategyDto;
import com.univeris.ewm.provider.data.common.profile.InvestmentStrategyProvider;
import com.univeris.ewm.provider.data.common.profile.impl.InvestmentStrategyEntity;
import com.univeris.ewm.service.data.common.baseservice.AbstractBaseService;
import com.univeris.ewm.service.data.common.baseservice.Cacheable;
import com.univeris.ewm.service.data.common.profile.InvestmentStrategyService;

/**
 * Investor profile service implementation class
 */
//@Stateless
//@Interceptors(ServiceInterceptor.class)
//@ClassVersionDetails(revision = "$Rev$", id = "$Id$")
public class InvestmentStrategyServiceUVS extends AbstractBaseService<InvestmentStrategyDto, String, InvestmentStrategyEntity, InvestmentStrategyProvider> implements InvestmentStrategyService, Cacheable {


    private InvestmentStrategyProvider _investmentStrategyProvider;
    final static String CACHE_KEY_INVESTMENT_STRATEGY = "investment-strategy";


    @PostConstruct
    private void init() {
        //setInvestmentStrategyProvider(locateProvider(InvestmentStrategyProvider.class));
    }

    public InvestmentStrategyProvider getInvestmentStrategyProvider() {
        if(_investmentStrategyProvider == null) {
           // _investmentStrategyProvider = locateProvider(InvestmentStrategyProvider.class);
        }
        return _investmentStrategyProvider;
    }


	private void setInvestmentStrategyProvider(InvestmentStrategyProvider investmentStrategyProvider) {
        _investmentStrategyProvider = investmentStrategyProvider;
    }

  
    /**
     * retrieve all existing investor profile
     *
     * @return

    @Override
    @FunctionalSecurity
    public List<InvestmentStrategyDto> getAll() {
        Cache cache = getInvestorProfileCache();
        String key = CACHE_KEY_INVESTOR_PROFILES;

        if(profileEntities == null) {
            profileEntities = getInvestmentStrategyProvider().getAll();
            cache.put(key, profileEntities);
        }
        return buildInvestorProfiles(profileEntities);
    }  */

    /**
     * Method to retrieve investor profile data cache
     *
     * @return investor profile data cache
     */
    /*
    public Cache getInvestorProfileCache() {
        return UIF.getInstance().
                getCacheManager().
                findCacheByName(InvestmentStrategyService.KEY_INVESTOR_PROFILE_DATA,
                                Contexts.getServiceContext().getInstanceId());
    }*/

    @Override
    public InvestmentStrategyDto entityToDto(InvestmentStrategyEntity profileEntity) {

        InvestmentStrategyDto investmentStrategyDto = super.entityToDto(profileEntity);
        investmentStrategyDto.setPictureUri(profileEntity.getImageFileUri());
        investmentStrategyDto.setConservativeIndicator(profileEntity.getConservativeRanking());

        return investmentStrategyDto;
    }

    @Override
    public InvestmentStrategyEntity dtoToEntity(InvestmentStrategyDto investmentStrategyDto) {
        InvestmentStrategyEntity profileEntity = (InvestmentStrategyEntity)super.dtoToEntity(investmentStrategyDto);

        profileEntity.setConservativeRanking(investmentStrategyDto.getConservativeIndicator());
        profileEntity.setImageFileUri(investmentStrategyDto.getPictureUri());


        return profileEntity;
    }

    @Override
    public InvestmentStrategyProvider getMyProvider() {
        return getInvestmentStrategyProvider();
    }


   // @Override
    /*
    public Cache getCache() {
        return UIF.getInstance().
    getCacheManager().
    findCacheByName(InvestmentStrategyService.KEY_INVESTOR_PROFILE_DATA,
                    Contexts.getServiceContext().getInstanceId());
}*/

   // @Override
    public String getCacheKey() {
        return CACHE_KEY_INVESTMENT_STRATEGY;
    }


  

   

}

