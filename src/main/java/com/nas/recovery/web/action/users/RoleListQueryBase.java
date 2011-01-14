package com.nas.recovery.web.action.users;

import com.oreon.trkincidents.users.Role;

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

import com.oreon.trkincidents.users.Role;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class RoleListQueryBase extends BaseQuery<Role, Long> {

	private static final String EJBQL = "select role from Role role";

	protected Role role = new Role();

	public Role getRole() {
		return role;
	}

	private com.oreon.trkincidents.users.User userToSearch;

	public void setUserToSearch(com.oreon.trkincidents.users.User userToSearch) {
		this.userToSearch = userToSearch;
	}

	public com.oreon.trkincidents.users.User getUserToSearch() {
		return userToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Role> getEntityClass() {
		return Role.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"role.id = #{roleList.role.id}",

			"lower(role.name) like concat(lower(#{roleList.role.name}),'%')",

			"#{roleList.userToSearch} in elements(role.users)",

			"role.dateCreated <= #{roleList.dateCreatedRange.end}",
			"role.dateCreated >= #{roleList.dateCreatedRange.begin}",};

	@Observer("archivedRole")
	public void onArchive() {
		refresh();
	}

}
