package com.nas.recovery.web.action.users;

import com.oreon.trkincidents.users.User;

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

public abstract class UserActionBase extends BaseAction<User>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private User user;

	@DataModel
	private List<User> userRecordList;

	public void setUserId(Long id) {
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
	public void setUserIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getUserId() {
		return (Long) getId();
	}

	public User getEntity() {
		return user;
	}

	//@Override
	public void setEntity(User t) {
		this.user = t;
		loadAssociations();
	}

	public User getUser() {
		return (User) getInstance();
	}

	@Override
	protected User createInstance() {
		return new User();
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

	public User getDefinedInstance() {
		return (User) (isIdDefined() ? getInstance() : null);
	}

	public void setUser(User t) {
		this.user = t;
		loadAssociations();
	}

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	public com.oreon.trkincidents.users.User findByUnqUserName(String userName) {
		return executeSingleResultNamedQuery("findByUnqUserName", userName);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListRoles();
		initListAvailableRoles();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.trkincidents.users.Role> listRoles = new ArrayList<com.oreon.trkincidents.users.Role>();

	void initListRoles() {

		if (listRoles.isEmpty())
			listRoles.addAll(getInstance().getRoles());

	}

	public List<com.oreon.trkincidents.users.Role> getListRoles() {

		prePopulateListRoles();
		return listRoles;
	}

	public void prePopulateListRoles() {
	}

	public void setListRoles(List<com.oreon.trkincidents.users.Role> listRoles) {
		this.listRoles = listRoles;
	}

	protected List<com.oreon.trkincidents.users.Role> listAvailableRoles = new ArrayList<com.oreon.trkincidents.users.Role>();

	void initListAvailableRoles() {

		listAvailableRoles = getEntityManager().createQuery(
				"select r from Role r").getResultList();
		listAvailableRoles.removeAll(getInstance().getRoles());

	}

	@Begin(join = true)
	public List<com.oreon.trkincidents.users.Role> getListAvailableRoles() {

		prePopulateListAvailableRoles();
		return listAvailableRoles;
	}

	public void prePopulateListAvailableRoles() {
	}

	public void setListAvailableRoles(
			List<com.oreon.trkincidents.users.Role> listAvailableRoles) {
		this.listAvailableRoles = listAvailableRoles;
	}

	public void updateComposedAssociations() {

		if (listRoles != null) {
			getInstance().getRoles().clear();
			getInstance().getRoles().addAll(listRoles);
		}
	}

	public void clearLists() {

		listRoles.clear();

	}

}
