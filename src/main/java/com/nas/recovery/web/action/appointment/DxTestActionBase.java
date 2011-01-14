package com.nas.recovery.web.action.appointment;

import com.oreon.trkincidents.appointment.DxTest;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public abstract class DxTestActionBase extends BaseAction<DxTest>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private DxTest dxTest;

	@DataModel
	private List<DxTest> dxTestRecordList;

	public void setDxTestId(Long id) {
		if (id == 0) {
			clearInstance();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDxTestIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getDxTestId() {
		return (Long) getId();
	}

	public DxTest getEntity() {
		return dxTest;
	}

	//@Override
	public void setEntity(DxTest t) {
		this.dxTest = t;
		loadAssociations();
	}

	public DxTest getDxTest() {
		return (DxTest) getInstance();
	}

	@Override
	protected DxTest createInstance() {
		return new DxTest();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public DxTest getDefinedInstance() {
		return (DxTest) (isIdDefined() ? getInstance() : null);
	}

	public void setDxTest(DxTest t) {
		this.dxTest = t;
		loadAssociations();
	}

	@Override
	public Class<DxTest> getEntityClass() {
		return DxTest.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
