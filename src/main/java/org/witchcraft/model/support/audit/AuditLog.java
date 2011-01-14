package org.witchcraft.model.support.audit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.seam.action.EventTypes;

/** This class represents an audit log entry
 * @author jsingh
 *
 */
@Entity
//@Table(uniqueConstraints={@UniqueConstraint(columnNames={"dateCreated","username"})})
public class AuditLog<T> extends BusinessEntity{
	private EventTypes action;
	@Lob
	@Column(length=4194304)
	private BusinessEntity record;
	private String entityName;
	private String username;
	private Long entityId;
	/*private AbstractUser user; */ 
	
	public AuditLog(){}
	
	public AuditLog(EventTypes action, BusinessEntity record, String entityName, Long entityId,
			String username) {
		super();
		this.action = action;
		this.record = record;
		this.entityName = entityName;
		this.username = username;
		this.entityId = entityId;
	}
	
	public EventTypes getAction() {
		return action;
	}
	public void setAction(EventTypes action) {
		this.action = action;
	}
	
	
	public BusinessEntity getRecord() {
		return record;
	}
	public void setRecord(BusinessEntity record) {
		this.record = record;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName; 
	}
	/*
	@ManyToOne
	@JoinColumn(name = "user_ID", nullable = false)
	public AbstractUser getUser() {
		return user;
	}
	public void setUser(AbstractUser user) {
		this.user = user;
	}*/

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Transient
	public  T getEntity(){
		return (T) getRecord();
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getEntityId() {
		return entityId;
	}
	
}
