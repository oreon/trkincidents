
		package com.oreon.trkincidents.custm;
		
	import java.util.ArrayList;
	import java.util.HashSet;
	import java.util.List;
	import java.util.Set;
	import java.util.Date;

		
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


	