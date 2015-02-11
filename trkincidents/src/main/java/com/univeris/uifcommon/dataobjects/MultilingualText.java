package com.univeris.uifcommon.dataobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;



@Embeddable
//@ClassVersionDetails(revision = "$Rev$", id = "$Id$")
public class MultilingualText implements Serializable {
    public static final String XV_TRUE = "true";
    public static final String XA_DEFAULT = "default";
    public static final String XE_TEXT = "text";

    public static final String LANG_CD_ENG = "ENG";
    public static final String LANG_CD_FRE = "FRE";

    public static final String FIELD_ENG = "_english";
    public static final String FIELD_FRE = "_french";

    @Transient
    private String _defaultLanguage;

    @Column(name = "DESC_ENG")
    private String _english;

    @Column(name = "DESC_FRE")
    private String _french;

    private void validateLanguage(String languageCode) {
        if (languageCode == null || !(LANG_CD_ENG.equals(languageCode.toUpperCase()) || LANG_CD_FRE.equals(languageCode.toUpperCase()))) {
            throw new IllegalStateException("Language [" + languageCode + "] not supported");
        }
    }

    public void addText(String language, String text) {
        addText(language, text, false);
    }

    public void addText(String language, String text, boolean isDefault) {
        validateLanguage(language);

        if(language != null && language.trim().length() > 0) {
            if(_defaultLanguage == null || isDefault) {
                _defaultLanguage = language.toUpperCase();
            }
            setText(language, text);
        }
    }

    private void setText(String language, String text) {
        if(language.toUpperCase().equals(LANG_CD_ENG)) {
            _english = text;
        } else {
            _french = text;
        }
    }

    public boolean setDefault(String language) {
        validateLanguage(language);

        if(language.toUpperCase().equals(LANG_CD_ENG) && _english != null ||
           language.toUpperCase().equals(LANG_CD_FRE) && _french != null) {

            _defaultLanguage = language.toUpperCase();
            return true;
        }

        return false;
    }

    public String getText(String language, boolean useDefault) {
        validateLanguage(language);

        String retVal = language.toUpperCase().equals(LANG_CD_ENG) ? _english : _french;

        if(retVal == null && useDefault && _defaultLanguage != null) {
            retVal = getText(_defaultLanguage, false);
        }
        if(retVal == null && useDefault) {
            retVal = "";
        }
        return retVal;
    }

    public String getText(String language) {
        return getText(language, true);
    }

    public String toString() {
        StringBuilder retVal = new StringBuilder();
        retVal.append("\n[MulilingualText]");
        if(_defaultLanguage == null) {
            retVal.append("\nDefault Value not set");
        } else {
            retVal.append("\nDefault Value:").append(_defaultLanguage);
        }
        if(_english == null && _french == null) {
            retVal.append("\nNo Text!");
        } else {
            if(_english != null) {
                retVal.append("\nLanguage:").append(LANG_CD_ENG).append(" = ").append(_english);
            }
            if(_french != null) {
                retVal.append("\nLanguage:").append(LANG_CD_FRE).append(" = ").append(_french);
            }
        }

        return retVal.toString();
    }
}