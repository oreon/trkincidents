/*
 * Copyright (c) 2014 Univeris Corporation. All Rights Reserved.
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

package com.univeris.ewm.provider.data.common.profile.impl;

import static com.univeris.uifcommon.dataobjects.MultilingualText.FIELD_ENG;
import static com.univeris.uifcommon.dataobjects.MultilingualText.FIELD_FRE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.univeris.ewm.provider.data.basedataaccess.BaseTimeStampedEntity;
import com.univeris.uifcommon.dataobjects.MultilingualText;

/**
 * S_INVESTMENT_STRATEGY entity
 */
@Entity
@Table(name = "S_INVESTMENT_STRATEGY")
@NamedQueries({
          @NamedQuery(name = InvestmentStrategyEntity.QUERY_FIND_ALL, query = "SELECT i FROM InvestmentStrategyEntity i")
  })
//@ClassVersionDetails(revision = "$Rev$", id = "$Id$")
public class InvestmentStrategyEntity extends BaseTimeStampedEntity<String> implements Serializable {

    public static final String QUERY_FIND_ALL = "InvestmentStrategyEntity.findAll";

    @Id
    @Column(name = "STRATEGY_CD", nullable = false)
    private String _code;

    @Embedded
    @AttributeOverrides({
                @AttributeOverride(name = FIELD_ENG, column = @Column(name = "NAME_ENG")),
                @AttributeOverride(name = FIELD_FRE, column = @Column(name = "NAME_FRE"))})
    private MultilingualText _name;

    @Embedded
    @AttributeOverrides({
                @AttributeOverride(name = FIELD_ENG, column = @Column(name = "DESC_ENG")),
                @AttributeOverride(name = FIELD_FRE, column = @Column(name = "DESC_FRE"))})
    private MultilingualText _description;

    @Column(name = "IMAGE_FILE_URI")
    private String _imageFileUri;

    @Column(name = "CONSERVATIVE_RANKING")
    private Integer _conservativeRanking;



    @Column(name = "TIME_HORIZON_CD")
    private Integer _timeHorizonCode;

    @Column(name = "RISKS")
    private String _risks;

    @Column(name = "GOALS")
    private String _goals;


    @Column(name = "USER_SYSID")
    private Integer _userSysid;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date _createDate;

    @Column(name = "LAST_UPDATE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date _lastUpdateDt;

    public InvestmentStrategyEntity(){

    }

    public InvestmentStrategyEntity(final String code, final MultilingualText name, final MultilingualText description, final String imageFileUri, final Integer conservativeRanking) {
        _code = code;
        _name = name;
        _description = description;
        _imageFileUri = imageFileUri;
        _conservativeRanking = conservativeRanking;
    }

    public String getCode() {
        return _code;
    }

    public void setCode(final String code) {
        _code = code;
    }

    public MultilingualText getName() {
        if(_name == null ){
            _name = new MultilingualText();
        }
        return _name;
    }

    public void setName(final MultilingualText name) {
        _name = name;
    }

    public MultilingualText getDescription() {
        if(_description == null ){
            _description = new MultilingualText();
        }
        return _description;
    }

    public void setDescription(final MultilingualText description) {
        _description = description;
    }

    public String getImageFileUri() {
        return _imageFileUri;
    }

    public void setImageFileUri(final String imageFileUri) {
        _imageFileUri = imageFileUri;
    }

    public Integer getConservativeRanking() {
        return _conservativeRanking;
    }

    public void setConservativeRanking(final Integer conservativeRanking) {
        _conservativeRanking = conservativeRanking;
    }

    public Integer getTimeHorizonCode() {
        return _timeHorizonCode;
    }

    public void setTimeHorizonCode(final Integer timeHorizonCode) {
        _timeHorizonCode = timeHorizonCode;
    }

    public String getRisks() {
        return _risks;
    }

    public void setRisks(final String risks) {
        _risks = risks;
    }

    public String getGoals() {
        return _goals;
    }

    public void setGoals(final String goals) {
        _goals = goals;
    }

    public Integer getUserSysid() {
        return _userSysid;
    }

    public void setUserSysid(final Integer userSysid) {
        _userSysid = userSysid;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(final Date createDate) {
        _createDate = createDate;
    }

    public Date getLastUpdateDt() {
        return _lastUpdateDt;
    }

    public void setLastUpdateDt(final Date lastUpdateDt) {
        _lastUpdateDt = lastUpdateDt;
    }

    //Callback methods
    @PrePersist
    public void onCreate(){
        //set the CREATE_DATE and LAST_UPDATE_DT
        setCreateDate(new Date());
        setLastUpdateDt(new Date());
    }

    @PreUpdate
    public void onUpdate(){
        //set the LAST_UPDATE_DT
        setLastUpdateDt(new Date());
    }

    @Override
    public String getPK() {
        return getCode();  //To change body of implemented methods use File | Settings | File Templates.
    }
}
