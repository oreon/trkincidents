package com.universe.base;

import org.bouncycastle.asn1.ocsp.ServiceLocator;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Scope;

import com.univeris.ewm.dataobjects.cxp.InvestmentStrategyDto;
import com.univeris.ewm.service.data.common.profile.InvestmentStrategyService;

@Scope(ScopeType.CONVERSATION)

public class InvestorProfileView extends BaseCrudView<InvestmentStrategyDto, InvestmentStrategyService, String> {

    public static final String COMPONENT_NAME = "cxp.operations.profile";
    private static final String CONSTRAINT_VIOLATION_KEYWORD = "FK_INVESTOR_PROFILE";

    private InvestmentStrategyService _investmentStrategyService;

    @Create
    public void init() {
        if (_investmentStrategyService == null) {
            //_investmentStrategyService = ServiceLocator.locateService(InvestmentStrategyService.class);
        }
    }

    @Override
    public InvestmentStrategyService getMyService() {
        return getInvestmentStrategyService();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public InvestmentStrategyService getInvestmentStrategyService() {
        return _investmentStrategyService;
    }

    public void setInvestmentStrategyService(final InvestmentStrategyService investmentStrategyService) {
        _investmentStrategyService = investmentStrategyService;
    }


    @Begin(join=true)
    public void addProfile() {
        setCurrentInstance( new InvestmentStrategyDto());
    }

   // @Override
    protected String getConstraintViolationKeyWord() {
        return CONSTRAINT_VIOLATION_KEYWORD;
    }
}