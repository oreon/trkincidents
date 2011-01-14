package com.nas.recovery.web.action.appointment;

import com.oreon.trkincidents.appointment.DxTest;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.oreon.trkincidents.appointment.DxTest;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class DxTestListQueryBase extends BaseQuery<DxTest, Long> {

	private static final String EJBQL = "select dxTest from DxTest dxTest";

	protected DxTest dxTest = new DxTest();

	public DxTest getDxTest() {
		return dxTest;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<DxTest> getEntityClass() {
		return DxTest.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"dxTest.id = #{dxTestList.dxTest.id}",

			"lower(dxTest.name) like concat(lower(#{dxTestList.dxTest.name}),'%')",

			"lower(dxTest.description) like concat(lower(#{dxTestList.dxTest.description}),'%')",

			"dxTest.dateCreated <= #{dxTestList.dateCreatedRange.end}",
			"dxTest.dateCreated >= #{dxTestList.dateCreatedRange.begin}",};

	@Observer("archivedDxTest")
	public void onArchive() {
		refresh();
	}

}
