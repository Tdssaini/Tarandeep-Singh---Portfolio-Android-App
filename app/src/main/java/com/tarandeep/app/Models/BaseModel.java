package com.tarandeep.app.Models;

import java.io.Serializable;

/**
 * Created by Tarandeep on 12/20/2015.
 */
public class BaseModel implements Serializable{

    public boolean isWindowNonEditable;

    public boolean isWindowNonEditable() {
        return isWindowNonEditable;
    }

    public void setIsWindowNonEditable(boolean isWindowNonEditable) {
        this.isWindowNonEditable = isWindowNonEditable;
    }
}
