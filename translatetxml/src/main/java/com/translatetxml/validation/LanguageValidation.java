package com.translatetxml.validation;

import com.translatetxml.constants.LanguageEnum;

public class LanguageValidation {
	
	private String language;

	public LanguageValidation(String language) {
		this.language = language;
	}
	
    public boolean isValid() {
        return LanguageEnum.getLanguageEnum(language) != null;
    }

}
