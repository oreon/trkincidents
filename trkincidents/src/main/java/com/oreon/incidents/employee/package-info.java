@FilterDef(name = "archiveFilterDef", defaultCondition = "archived = :aArchived", parameters = @ParamDef(name = "aArchived", type = "string"))
package com.oreon.incidents.employee;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
