package com.nas.recovery.web.action.custm;

import com.oreon.trkincidents.custm.MetaField;

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

public abstract class MetaFieldActionBase extends BaseAction<MetaField>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private MetaField metaField;

	@In(create = true, value = "metaEntityAction")
	com.nas.recovery.web.action.custm.MetaEntityAction metaEntityAction;

	@DataModel
	private List<MetaField> metaFieldRecordList;

	public void setMetaFieldId(Long id) {
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
	public void setMetaFieldIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setMetaEntityId(Long id) {

		if (id != null && id > 0)
			getInstance().setMetaEntity(metaEntityAction.loadFromId(id));

	}

	public Long getMetaEntityId() {
		if (getInstance().getMetaEntity() != null)
			return getInstance().getMetaEntity().getId();
		return 0L;
	}

	public Long getMetaFieldId() {
		return (Long) getId();
	}

	public MetaField getEntity() {
		return metaField;
	}

	//@Override
	public void setEntity(MetaField t) {
		this.metaField = t;
		loadAssociations();
	}

	public MetaField getMetaField() {
		return (MetaField) getInstance();
	}

	@Override
	protected MetaField createInstance() {
		return new MetaField();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.trkincidents.custm.MetaEntity metaEntity = metaEntityAction
				.getDefinedInstance();
		if (metaEntity != null && isNew()) {
			getInstance().setMetaEntity(metaEntity);
		}

	}

	public boolean isWired() {
		return true;
	}

	public MetaField getDefinedInstance() {
		return (MetaField) (isIdDefined() ? getInstance() : null);
	}

	public void setMetaField(MetaField t) {
		this.metaField = t;
		loadAssociations();
	}

	@Override
	public Class<MetaField> getEntityClass() {
		return MetaField.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (metaField.getMetaEntity() != null) {
			criteria = criteria.add(Restrictions.eq("metaEntity.id", metaField
					.getMetaEntity().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (metaField.getMetaEntity() != null) {
			metaEntityAction.setInstance(getInstance().getMetaEntity());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
