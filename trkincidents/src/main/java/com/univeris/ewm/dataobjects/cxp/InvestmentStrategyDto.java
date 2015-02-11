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

package com.univeris.ewm.dataobjects.cxp;

import java.io.Serializable;

import com.univeris.ewm.dataobjects.support.BaseDto;
import com.univeris.uifcommon.dataobjects.MultilingualText;

/**
 * DTO for Investor Profile of clientXPRESS
 */
//@ClassVersionDetails(revision = "$Rev$", id = "$Id$")
public class InvestmentStrategyDto extends BaseDto<String> implements Serializable {

    private String _code;
    private MultilingualText _name;
    private MultilingualText _description;

    private String _pictureUri;
    private Integer _conservativeIndicator;
    private Integer _lowerLimit;
    private Integer _upperLimit;
    private Integer _userSysid;
    private Integer _timeHorizonCode;
    private String _risks;
    private String _goals;

    public Integer getConservativeIndicator() {
        return _conservativeIndicator;
    }

    public void setConservativeIndicator(final Integer conservativeIndicator) {
        _conservativeIndicator = conservativeIndicator;
    }

    public String getDescriptionEnglish() {
        return _description == null ? "" : _description.getText(MultilingualText.LANG_CD_ENG);
    }

    public void setDescriptionEnglish(final String descriptionEnglish) {
        if (_description == null) {
            _description = new MultilingualText();
        }

        _description.addText(MultilingualText.LANG_CD_ENG, descriptionEnglish);
    }

    public String getDescriptionFrench() {
        return _description == null ? "" : _description.getText(MultilingualText.LANG_CD_FRE);
    }

    public void setDescriptionFrench(final String descriptionFrench) {
        if (_description == null) {
            _description = new MultilingualText();
        }

        _description.addText(MultilingualText.LANG_CD_FRE, descriptionFrench);
    }

    public Integer getLowerLimit() {
        return _lowerLimit;
    }

    public void setLowerLimit(final Integer lowerLimit) {
        _lowerLimit = lowerLimit;
    }

    public String getNameEnglish() {
        return _name == null ? "" : _name.getText(MultilingualText.LANG_CD_ENG);
    }

    public void setNameEnglish(final String nameEnglish) {
        if (_name == null) {
            _name = new MultilingualText();
        }

        _name.addText(MultilingualText.LANG_CD_ENG, nameEnglish);
    }

    public String getNameFrench() {
        return _name == null ? "" : _name.getText(MultilingualText.LANG_CD_FRE);
    }

    public void setNameFrench(final String nameFrench) {
        if (_name == null) {
            _name = new MultilingualText();
        }

        _name.addText(MultilingualText.LANG_CD_FRE, nameFrench);
    }

    public String getPictureUri() {
        return _pictureUri;
    }

    public void setPictureUri(final String pictureUri) {
        _pictureUri = pictureUri;
    }

    public String getCode() {
        return _code;
    }

    public MultilingualText getDescription() {
        return _description;
    }

    public void setDescription(final MultilingualText description) {
        _description = description;
    }

    public MultilingualText getName() {
        return _name;
    }

    public void setName(final MultilingualText name) {
        _name = name;
    }

    public void setCode(final String code) {
        _code = code;

    }

    public Integer getUpperLimit() {
        return _upperLimit;
    }

    public void setUpperLimit(final Integer upperLimit) {
        _upperLimit = upperLimit;
    }

    public Integer getUserSysid() {
        return _userSysid;
    }

    public void setUserSysid(final Integer userSysid) {
        _userSysid = userSysid;
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

    @Override
    public String getPK() {
        return getCode();  //To change body of implemented methods use File | Settings | File Templates.
    }
}
