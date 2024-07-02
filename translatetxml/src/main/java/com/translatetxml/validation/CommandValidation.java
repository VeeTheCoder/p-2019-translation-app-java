package com.translatetxml.validation;

public class CommandValidation {
	
	private String sourceFile;
	private String outputFile;
	private String sourceLanguage;
	private String targetLanguage;
	
	public CommandValidation(String sourceFile, String outputFile, String sourceLanguage, String targetLanguage){
		this.sourceFile = sourceFile;
		this.outputFile = outputFile;
		this.sourceLanguage = sourceLanguage;
		this.targetLanguage = targetLanguage;
	}

	public boolean isValid() {
		if(!isFileValid(sourceFile)) {
            System.out.println("Bad Source File");
			return false;
		}
		
		if(!isFileValid(outputFile)) {
            System.out.println("Bad Output File");
			return false;
		}
		
		if(!isLanguageValid(sourceLanguage)) {
            System.out.println("Bad Source Language");
			return false;
		}
		
		if(!isLanguageValid(targetLanguage)) {
            System.out.println("Bad Target Language");
			return false;	
		}
		
		return true;
	}
	
	private boolean isFileValid(String path) {
		return new FileValidation(path, "txml").isValid();
	}
	
	private boolean isLanguageValid(String language) {	
		return new LanguageValidation(language).isValid();
	}
	

}
