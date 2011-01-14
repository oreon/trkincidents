package com.nas.recovery.web.action.patient;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.trkincidents.patient.Document;

public class DocumentActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Document> {

	DocumentAction documentAction = new DocumentAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Document> getAction() {
		return documentAction;
	}

}
