package com.univeris.ewm.dataobjects.support;

import java.io.Serializable;

public abstract class BaseDto<K extends Serializable> {

    public abstract K getPK();


}
