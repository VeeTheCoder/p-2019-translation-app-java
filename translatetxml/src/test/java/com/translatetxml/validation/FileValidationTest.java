package com.translatetxml.validation;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileValidationTest {

	@Test
	public void testIsValid() {
		FileValidation fileValidation;
		
		fileValidation= new FileValidation("source.txml", "tXML");
		assertTrue(fileValidation.isValid());
		
		fileValidation = new FileValidation("source.txml", "XML");
		assertFalse(fileValidation.isValid());
		
	}

}
