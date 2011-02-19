package org.witchcraft.base.entity;
import java.io.Serializable;
import java.util.Date;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.async.QuartzTriggerHandle;
import org.jboss.seam.log.Log;



@Name("controller")
@AutoCreate
public class ScheduleController implements Serializable { 
   
	private static final long serialVersionUID = 7609983147081676186L;

	@In 
    ScheduleProcessor processor;    

    @Logger 
    Log log;  
     
	private QuartzTriggerHandle quartzAddTriggerHandle;
	
	private QuartzTriggerHandle quartzRoleTriggerHandle;
	
    public void scheduleTimer() {
		String addCronInterval = null;
		String roleCronInterval = null;
		try{
			//addCronInterval = BulkUtil.getProperty("addCronInterval");
			//roleCronInterval = BulkUtil.getProperty("roleCronInterval");
		} catch (Exception e){
			e.printStackTrace();
		}
		if ((addCronInterval != null)&(roleCronInterval != null)) {		
			quartzAddTriggerHandle = processor.createQuartzAddTimer(new Date(), addCronInterval);
			quartzRoleTriggerHandle = processor.createQuartzRoleTimer(new Date(), roleCronInterval);
		}
		
	}

}