
		package com.oreon.trkincidents.custm;
		
	import java.util.ArrayList;
	import java.util.HashSet;
	import java.util.List;
	import java.util.Set;
	import java.util.Date;

		
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


	