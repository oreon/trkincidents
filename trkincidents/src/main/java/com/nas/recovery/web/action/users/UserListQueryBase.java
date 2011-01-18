package com.nas.recovery.web.action.users;

import com.oreon.trkincidents.users.User;

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

import com.oreon.trkincidents.users.User;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class UserListQueryBase extends BaseQuery<User, Long> {

	private static final String EJBQL = "select user from User user";

	protected User user = new User();

	public User getUser() {
		return user;
	}

	private com.oreon.trkincidents.users.Role roleToSearch;

	public void setRoleToSearch(com.oreon.trkincidents.users.Role roleToSearch) {
		this.roleToSearch = roleToSearch;
	}

	public com.oreon.trkincidents.users.Role getRoleToSearch() {
		return roleToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"user.id = #{userList.user.id}",

			"lower(user.userName) like concat(lower(#{userList.user.userName}),'%')",

			"user.enabled = #{userList.user.enabled}",

			"#{userList.roleToSearch} in elements(user.roles)",

			"lower(user.email) like concat(lower(#{userList.user.email}),'%')",

			"user.dateCreated <= #{userList.dateCreatedRange.end}",
			"user.dateCreated >= #{userList.dateCreatedRange.begin}",};

	@Observer("archivedUser")
	public void onArchive() {
		refresh();
	}

}
