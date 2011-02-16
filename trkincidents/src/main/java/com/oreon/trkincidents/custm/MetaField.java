
		package com.oreon.trkincidents.custm;
		
	import java.util.ArrayList;
	import java.util.HashSet;
	import java.util.List;
	import java.util.Set;
	import java.util.Date;

		
	public class MetaField  {

	
		
	
		 
		
			
	@NotNull @Length(min=2, max=250)
	
	
	
		
		 @Field(index = Index.TOKENIZED) @Analyzer(definition = "customanalyzer") 
		protected String  name  
			 ;
	
	


		
	

	
		
			
			@ManyToOne(optional=false, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
			@JoinColumn(name="metaEntity_id", nullable=false, updatable = true) 
    		@ContainedIn
		 
		
			
	
	
	
	
		
		
		protected MetaEntity  metaEntity  
			 ;
	
	


		
	

	
		
			
			@ManyToOne(optional=false, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
			@JoinColumn(name="customReport_id", nullable=false, updatable = true) 
    		@ContainedIn
		 
		
			
	
	
	
	
		
		
		protected CustomReport  customReport  
			 ;
	
	


		
	

	
		
			
			@ManyToOne(optional=false, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
			@JoinColumn(name="customReport_id", nullable=false, updatable = true) 
    		@ContainedIn
		 
		
			
	
	
	
	
		
		
		protected CustomReport  customReport  
			 ;
	
	


		
	

		
	
		public void setName(String name){
			this.name = name;
		}
		
		public String getName( ){
			
				return name;
			
		}
	

	
		public void setMetaEntity(MetaEntity metaEntity){
			this.metaEntity = metaEntity;
		}
		
		public MetaEntity getMetaEntity( ){
			
				return metaEntity;
			
		}
	

	
		public void setCustomReport(CustomReport customReport){
			this.customReport = customReport;
		}
		
		public CustomReport getCustomReport( ){
			
				return customReport;
			
		}
	

	
		public void setCustomReport(CustomReport customReport){
			this.customReport = customReport;
		}
		
		public CustomReport getCustomReport( ){
			
				return customReport;
			
		}
	

		
	}


	