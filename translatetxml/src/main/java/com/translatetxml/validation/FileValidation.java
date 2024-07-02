package com.translatetxml.validation;

public class FileValidation {
	
	private String file;
	private String fileExtension;

	public FileValidation(String file, String fileExtension) {
		this.file = file;
		this.fileExtension = fileExtension;
	}
	
	public boolean isValid() {
		if(!isExtensionValid(file, fileExtension)) {
			return false;
		}
		
		return true;
	}
	
    private boolean isExtensionValid(String file, String extension) {
    	if(!file.contains(".")) {
    		return false;
    	}

    	if(getExtension(file).equalsIgnoreCase(extension)) {
    		return true;
    	}
    	
    	return false;
    }
    
    private String getExtension(String file) {
        return file.substring(file.lastIndexOf(".") + 1);
    }

}
