package org.witchcraft.base.entity;

import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.async.Asynchronous;
import org.jboss.seam.annotations.async.Expiration;
import org.jboss.seam.annotations.async.IntervalCron;
import org.jboss.seam.async.QuartzTriggerHandle;

@Name("processor")
@AutoCreate
@Scope(ScopeType.APPLICATION)
public class ScheduleProcessor { 
    
	
	
    @Asynchronous
    @Transactional
    public QuartzTriggerHandle createQuartzAddTimer(@Expiration Date when, @IntervalCron String interval){
    	//bulkAddPerson();
    	return null;
    }
    
    @Asynchronous
    @Transactional
    public QuartzTriggerHandle createQuartzRoleTimer(@Expiration Date when, @IntervalCron String interval){
		String date = new Date().toString();
		try{
			//bulkUpdateRole();
		} catch (Exception e){
			//logrole.error(date + ": Error processing role log file.");
			e.printStackTrace();
		}
    	return null;
    }



}