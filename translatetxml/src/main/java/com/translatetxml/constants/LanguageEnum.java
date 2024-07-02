package com.translatetxml.constants;

public enum LanguageEnum {
	en("en", "English"),
	ar("ar", "Arabic"),
	de("de", "German"),
	el("el", "Greek"),
	es("es", "Spanish"), 
	fr("fr", "French"), 
	it("it", "Italian"),  
	ja("ja", "Japanese"), 
	ko("ko", "Korean"), 
	nl("nl", "Dutch"), 
	pt("pt", "Portuguese"), 
	ru("ru", "Russian"), 
	sv("sv", "Swedish"), 
	zh_cn("zh_cn", "Simplified Chinese"), 
	zh_tw("zh_tw", "Traditional Chinese");
	
	private String language;
	private String languageDesc;

    LanguageEnum(String language, String languageDesc) {
        this.language = language;
        this.languageDesc = languageDesc;
    }
    
    public String getlanguage() {
        return language;
    }

    public void setCode(String language) {
        this.language = language;
    }

    public String getLanguageDesc() {
        return languageDesc;
    }

    public void setDesc(String languageDesc) {
        this.languageDesc = languageDesc;
    }
    
    
    public static LanguageEnum getLanguageEnum(String language) {
        for (LanguageEnum languageEnum : LanguageEnum.values()) {
            if (languageEnum.language.equalsIgnoreCase(language)) {
                return languageEnum;
            }
        }
        return null;
    }

}
