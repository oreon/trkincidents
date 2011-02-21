package com.nas.recovery.web.action.custm;

import com.oreon.trkincidents.custm.MetaEntity;

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

import com.oreon.trkincidents.custm.MetaField;

public abstract class MetaEntityActionBase extends BaseAction<MetaEntity>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private MetaEntity metaEntity;

	@DataModel
	private List<MetaEntity> metaEntityRecordList;

	public void setMetaEntityId(Long id) {
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
	public void setMetaEntityIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getMetaEntityId() {
		return (Long) getId();
	}

	public MetaEntity getEntity() {
		return metaEntity;
	}

	//@Override
	public void setEntity(MetaEntity t) {
		this.metaEntity = t;
		loadAssociations();
	}

	public MetaEntity getMetaEntity() {
		return (MetaEntity) getInstance();
	}

	@Override
	protected MetaEntity createInstance() {
		return new MetaEntity();
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

	public MetaEntity getDefinedInstance() {
		return (MetaEntity) (isIdDefined() ? getInstance() : null);
	}

	public void setMetaEntity(MetaEntity t) {
		this.metaEntity = t;
		loadAssociations();
	}

	@Override
	public Class<MetaEntity> getEntityClass() {
		return MetaEntity.class;
	}

	public com.oreon.trkincidents.custm.MetaEntity findByUnqName(String name) {
		return executeSingleResultNamedQuery("metaEntity.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListMetaFields();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.trkincidents.custm.MetaField> listMetaFields = new ArrayList<com.oreon.trkincidents.custm.MetaField>();

	void initListMetaFields() {

		if (listMetaFields.isEmpty())
			listMetaFields.addAll(getInstance().getMetaFields());

	}

	public List<com.oreon.trkincidents.custm.MetaField> getListMetaFields() {

		prePopulateListMetaFields();
		return listMetaFields;
	}

	public void prePopulateListMetaFields() {
	}

	public void setListMetaFields(
			List<com.oreon.trkincidents.custm.MetaField> listMetaFields) {
		this.listMetaFields = listMetaFields;
	}

	public void deleteMetaFields(int index) {
		listMetaFields.remove(index);
	}

	@Begin(join = true)
	public void addMetaFields() {
		initListMetaFields();
		MetaField metaFields = new MetaField();

		metaFields.setMetaEntity(getInstance());

		getListMetaFields().add(metaFields);
	}

	public void updateComposedAssociations() {

		if (listMetaFields != null) {
			getInstance().getMetaFields().clear();
			getInstance().getMetaFields().addAll(listMetaFields);
		}
	}

	public void clearLists() {
		listMetaFields.clear();

	}

}
