package org.witchcraft.utils;

import java.util.Arrays;
import java.util.List;

import org.jboss.seam.annotations.Name;



@Name("utilsAction")
public class UtilsAction {
	
	@SuppressWarnings("unchecked")
	public List<String> getChoiceValues(String arg){
		String[] choices = arg.split("," );
		return Arrays.asList(choices);
	}


}
