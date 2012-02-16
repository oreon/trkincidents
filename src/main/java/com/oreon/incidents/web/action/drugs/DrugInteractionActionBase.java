package com.oreon.incidents.web.action.drugs;

import com.oreon.incidents.drugs.DrugInteraction;

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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

public abstract class DrugInteractionActionBase
		extends
			BaseAction<DrugInteraction> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private DrugInteraction drugInteraction;

	@In(create = true, value = "drugAction")
	com.oreon.incidents.web.action.drugs.DrugAction drugAction;

	@In(create = true, value = "drugAction")
	com.oreon.incidents.web.action.drugs.DrugAction interactingDrugAction;

	@DataModel
	private List<DrugInteraction> drugInteractionRecordList;

	public void setDrugInteractionId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
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
	public void setDrugInteractionIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setDrugId(Long id) {

		if (id != null && id > 0)
			getInstance().setDrug(drugAction.loadFromId(id));

	}

	public Long getDrugId() {
		if (getInstance().getDrug() != null)
			return getInstance().getDrug().getId();
		return 0L;
	}

	public void setInteractingDrugId(Long id) {

		if (id != null && id > 0)
			getInstance().setInteractingDrug(
					interactingDrugAction.loadFromId(id));

	}

	public Long getInteractingDrugId() {
		if (getInstance().getInteractingDrug() != null)
			return getInstance().getInteractingDrug().getId();
		return 0L;
	}

	public Long getDrugInteractionId() {
		return (Long) getId();
	}

	public DrugInteraction getEntity() {
		return drugInteraction;
	}

	//@Override
	public void setEntity(DrugInteraction t) {
		this.drugInteraction = t;
		loadAssociations();
	}

	public DrugInteraction getDrugInteraction() {
		return (DrugInteraction) getInstance();
	}

	@Override
	protected DrugInteraction createInstance() {
		DrugInteraction instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.incidents.drugs.Drug drug = drugAction.getDefinedInstance();
		if (drug != null && isNew()) {
			getInstance().setDrug(drug);
		}

		com.oreon.incidents.drugs.Drug interactingDrug = interactingDrugAction
				.getDefinedInstance();
		if (interactingDrug != null && isNew()) {
			getInstance().setInteractingDrug(interactingDrug);
		}

	}

	public boolean isWired() {
		return true;
	}

	public DrugInteraction getDefinedInstance() {
		return (DrugInteraction) (isIdDefined() ? getInstance() : null);
	}

	public void setDrugInteraction(DrugInteraction t) {
		this.drugInteraction = t;
		if (drugInteraction != null)
			setDrugInteractionId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<DrugInteraction> getEntityClass() {
		return DrugInteraction.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (drugInteraction.getDrug() != null) {
			criteria = criteria.add(Restrictions.eq("drug.id", drugInteraction
					.getDrug().getId()));
		}

		if (drugInteraction.getInteractingDrug() != null) {
			criteria = criteria.add(Restrictions.eq("interactingDrug.id",
					drugInteraction.getInteractingDrug().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (drugInteraction.getDrug() != null) {
			drugAction.setInstance(getInstance().getDrug());
		}

		if (drugInteraction.getInteractingDrug() != null) {
			interactingDrugAction.setInstance(getInstance()
					.getInteractingDrug());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewDrugInteraction() {
		load(currentEntityId);
		return "viewDrugInteraction";
	}

}
