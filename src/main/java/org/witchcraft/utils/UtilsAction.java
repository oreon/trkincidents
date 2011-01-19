package org.witchcraft.utils;

import java.util.List;

import org.jboss.seam.annotations.Name;

import edu.emory.mathcs.backport.java.util.Arrays;

@Name("utilsAction")
public class UtilsAction {
	
	@SuppressWarnings("unchecked")
	public List<String> getChoiceValues(String arg){
		String[] choices = arg.split("," );
		return Arrays.asList(choices);
	}


}
