
	
package com.oreon.incidents.web.action.customReports;
	

import org.jboss.seam.annotations.Name;

	
//@Scope(ScopeType.CONVERSATION)
@Name("customReportAction")
public class CustomReportAction extends CustomReportActionBase implements java.io.Serializable{
	
	
	public void onChangeMetaEntity(){
		
	}
	
	@Override
	public void prePopulateListAvailableFields() {
		if (getInstance().getMetaEntity() != null) {
			listAvailableFields.clear();
			listAvailableFields.addAll(getInstance().getMetaEntity()
					.getMetaFields());
			listAvailableFields.removeAll(getInstance().getFields());
			
			
		}
	}
	
	
	@Override
	public void prePopulateListAvailableGroupFields() {
		if (getInstance().getMetaEntity() != null) {
			listAvailableGroupFields.clear();
			listAvailableGroupFields.addAll(getInstance().getMetaEntity()
					.getMetaFields());
			listAvailableGroupFields.removeAll(getInstance().getFields());
		}
	}
}
	