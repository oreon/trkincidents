package com.nas.recovery.web.action.customforms;

import java.util.Set;

import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.customforms.CustomField;
import com.oreon.trkincidents.customforms.CustomForm;
import com.oreon.trkincidents.customforms.FieldType;
import com.oreon.trkincidents.customforms.FilledField;

//@Scope(ScopeType.CONVERSATION)
@Name("filledFormAction")
public class FilledFormAction extends FilledFormActionBase implements
		java.io.Serializable {

	public String renderedField(FilledField field) {
		if(field == null)
			return "";
	
		if (field.getCustomField().getType().equals(FieldType.TEXT))
			return "/admin/entities/customforms/customTextField.xhtml";
		else
			return "/admin/entities/customforms/customTextArea.xhtml";
	}
	
	
	@Override
	public void prePopulateListFilledFields() {
		if(getInstance().getId() == null && listFilledFields.isEmpty()){
			CustomForm form = getInstance().getCustomForm();
			if(getInstance().getCustomForm() == null)
				return;
			Set<CustomField> flds = getInstance().getCustomForm().getCustomFields();
			for (CustomField fld : flds) {
				FilledField ffld = new FilledField();
				ffld.setFilledForm(this.getInstance());
				ffld.setCustomField(fld);
				listFilledFields.add(ffld);
			}
		}
	}
	
	/*
	@Override
	public void prePopulat
		if(getInstance().getId() == null && listExamScores.isEmpty()){
			GradeSubject gradeSubject = getInstance().getGradeSubject();
			if(gradeSubject == null)
				return;
			Set<Student> students = getInstance().getGradeSubject().getGrade().getStudents();
			for (Student student : students) {
				ExamScore score = new ExamScore();
				score.setStudent(student);
				score.setExamInstance(this.getInstance());
				listExamScores.add(score);
			}
		}
	}*/

}
