package com.oreon.trkincidents.patient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Cascade;

import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;
import org.hibernate.annotations.Filter;

import org.witchcraft.utils.*;

@MappedSuperclass
public class Person extends BusinessEntity {
	private static final long serialVersionUID = -1283387220L;

	@NotNull
	@Length(min = 2, max = 250)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String firstName;

	@NotNull
	@Length(min = 2, max = 250)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String lastName;

	protected Date dateOfBirth;

	protected Gender gender;

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "primaryPhone", column = @Column(name = "contactDetails_primaryPhone")),

			@AttributeOverride(name = "secondaryPhone", column = @Column(name = "contactDetails_secondaryPhone")),

			@AttributeOverride(name = "email", column = @Column(name = "contactDetails_email"))

	})
	protected ContactDetails contactDetails = new ContactDetails();

	@Column(name = "age", unique = false)
	@Transient
	protected Integer age;

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gender getGender() {
		return gender;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return DateUtils.calcAge(dateOfBirth);
	}

	@Transient
	public String getDisplayName() {
		try {
			return lastName != null ? lastName + ", " + firstName : "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("firstName");

		listSearchableFields.add("lastName");

		listSearchableFields.add("contactDetails.primaryPhone");

		listSearchableFields.add("contactDetails.secondaryPhone");

		listSearchableFields.add("contactDetails.email");

		return listSearchableFields;
	}

}
