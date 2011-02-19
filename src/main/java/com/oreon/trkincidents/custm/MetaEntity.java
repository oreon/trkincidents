
		package com.oreon.trkincidents.custm;
		
	import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.Length;

		
	public class MetaEntity  {

	
		
	
		 
		
			
	@NotNull @Length(min=2, max=250)  @Column(unique=true)
	
	
	
		
		 @Field(index = Index.TOKENIZED) @Analyzer(definition = "customanalyzer") 
		protected String  name  
			 ;
	
	


		
	

	
		
		@OneToMany(mappedBy = "metaEntity",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		
		@JoinColumn(name = "metaEntity_ID", nullable = true)
		@OrderBy("dateCreated DESC")
		@IndexedEmbedded
		
		private Set<MetaField> metaFields = new HashSet<MetaField>();
	

		
	
		public void setName(String name){
			this.name = name;
		}
		
		public String getName( ){
			
				return name;
			
		}
	

	
		public void setMetaFields(Set<MetaField> metaFields){
			this.metaFields = metaFields;
		}
		
		public Set<MetaField> getMetaFields( ){
			return metaFields;
		}
	

		
	}


	