
		package com.oreon.trkincidents.custm;
		
	import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

		
	public class CustomReport  {

	
		
	
		
			
			@ManyToOne(optional=false, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
			@JoinColumn(name="metaEntity_id", nullable=false, updatable = true) 
    		@ContainedIn
		 
		
			
	
	
	
	
		
		
		protected MetaEntity  metaEntity  
			 ;
	
	


		
	

	
		
		@OneToMany(mappedBy = "customReport",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		
		@JoinColumn(name = "customReport_ID", nullable = true)
		@OrderBy("dateCreated DESC")
		@IndexedEmbedded
		
		private Set<MetaField> fields = new HashSet<MetaField>();
	

	
		
		@OneToMany(mappedBy = "customReport",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		
		@JoinColumn(name = "customReport_ID", nullable = true)
		@OrderBy("dateCreated DESC")
		@IndexedEmbedded
		
		private Set<MetaField> groupFields = new HashSet<MetaField>();
	

		
	
		public void setMetaEntity(MetaEntity metaEntity){
			this.metaEntity = metaEntity;
		}
		
		public MetaEntity getMetaEntity( ){
			
				return metaEntity;
			
		}
	

	
		public void setFields(Set<MetaField> fields){
			this.fields = fields;
		}
		
		public Set<MetaField> getFields( ){
			return fields;
		}
	

	
		public void setGroupFields(Set<MetaField> groupFields){
			this.groupFields = groupFields;
		}
		
		public Set<MetaField> getGroupFields( ){
			return groupFields;
		}
	

		
	}


	