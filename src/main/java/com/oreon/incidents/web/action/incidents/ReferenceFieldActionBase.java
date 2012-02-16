package com.oreon.incidents.web.action.incidents;

import com.oreon.incidents.incidents.ReferenceField;

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

public abstract class ReferenceFieldActionBase
		extends
			BaseAction<ReferenceField> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ReferenceField referenceField;

	@In(create = true, value = "incidentTypeAction")
	com.oreon.incidents.web.action.incidents.IncidentTypeAction incidentTypeAction;

	@DataModel
	private List<ReferenceField> referenceFieldRecordList;

	public void setReferenceFieldId(Long id) {
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
	public void setReferenceFieldIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setIncidentTypeId(Long id) {

		if (id != null && id > 0)
			getInstance().setIncidentType(incidentTypeAction.loadFromId(id));

	}

	public Long getIncidentTypeId() {
		if (getInstance().getIncidentType() != null)
			return getInstance().getIncidentType().getId();
		return 0L;
	}

	public Long getReferenceFieldId() {
		return (Long) getId();
	}

	public ReferenceField getEntity() {
		return referenceField;
	}

	//@Override
	public void setEntity(ReferenceField t) {
		this.referenceField = t;
		loadAssociations();
	}

	public ReferenceField getReferenceField() {
		return (ReferenceField) getInstance();
	}

	@Override
	protected ReferenceField createInstance() {
		ReferenceField instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.incidents.incidents.IncidentType incidentType = incidentTypeAction
				.getDefinedInstance();
		if (incidentType != null && isNew()) {
			getInstance().setIncidentType(incidentType);
		}

	}

	public boolean isWired() {
		return true;
	}

	public ReferenceField getDefinedInstance() {
		return (ReferenceField) (isIdDefined() ? getInstance() : null);
	}

	public void setReferenceField(ReferenceField t) {
		this.referenceField = t;
		if (referenceField != null)
			setReferenceFieldId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<ReferenceField> getEntityClass() {
		return ReferenceField.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (referenceField.getIncidentType() != null) {
			criteria = criteria.add(Restrictions.eq("incidentType.id",
					referenceField.getIncidentType().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (referenceField.getIncidentType() != null) {
			incidentTypeAction.setInstance(getInstance().getIncidentType());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewReferenceField() {
		load(currentEntityId);
		return "viewReferenceField";
	}

}
