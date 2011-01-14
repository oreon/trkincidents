package com.nas.recovery.web.action.appointment;

import com.oreon.trkincidents.appointment.History;

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

public abstract class HistoryActionBase extends BaseAction<History>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private History history;

	@In(create = true, value = "encounterAction")
	com.nas.recovery.web.action.appointment.EncounterAction encounterAction;

	@DataModel
	private List<History> historyRecordList;

	public void setHistoryId(Long id) {
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
	public void setHistoryIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public void setEncounterId(Long id) {

		if (id != null && id > 0)
			getInstance().setEncounter(encounterAction.loadFromId(id));

	}

	public Long getEncounterId() {
		if (getInstance().getEncounter() != null)
			return getInstance().getEncounter().getId();
		return 0L;
	}

	public Long getHistoryId() {
		return (Long) getId();
	}

	public History getEntity() {
		return history;
	}

	//@Override
	public void setEntity(History t) {
		this.history = t;
		loadAssociations();
	}

	public History getHistory() {
		return (History) getInstance();
	}

	@Override
	protected History createInstance() {
		return new History();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.trkincidents.appointment.Encounter encounter = encounterAction
				.getDefinedInstance();
		if (encounter != null && isNew()) {
			getInstance().setEncounter(encounter);
		}

	}

	public boolean isWired() {
		return true;
	}

	public History getDefinedInstance() {
		return (History) (isIdDefined() ? getInstance() : null);
	}

	public void setHistory(History t) {
		this.history = t;
		loadAssociations();
	}

	@Override
	public Class<History> getEntityClass() {
		return History.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (history.getEncounter() != null) {
			criteria = criteria.add(Restrictions.eq("encounter.id", history
					.getEncounter().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (history.getEncounter() != null) {
			encounterAction.setInstance(getInstance().getEncounter());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
