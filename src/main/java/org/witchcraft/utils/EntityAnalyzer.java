package org.witchcraft.utils;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.apache.solr.analysis.StopFilterFactory;
import org.apache.solr.analysis.SynonymFilterFactory;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

@AnalyzerDef(name = "entityAnalzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
	@TokenFilterDef(factory = LowerCaseFilterFactory.class),
	@TokenFilterDef(factory = StopFilterFactory.class),
	@TokenFilterDef(factory = SynonymFilterFactory.class),
	@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class EntityAnalyzer {

}
