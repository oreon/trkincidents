@FilterDef(name = "archiveFilterDef", defaultCondition = "archived = :aArchived", parameters = @ParamDef(name = "aArchived", type = "string"))
package com.oreon.incidents.drugs;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
