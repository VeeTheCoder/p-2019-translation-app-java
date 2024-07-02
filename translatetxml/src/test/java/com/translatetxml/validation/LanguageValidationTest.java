package com.translatetxml.validation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.translatetxml.constants.LanguageEnum;
import com.translatetxml.utils.SegmentHelper;

public class LanguageValidationTest {

	@Test
	public void testIsValid() {
		LanguageValidation languageValidation;
		languageValidation = new LanguageValidation("en");
		assertTrue(languageValidation.isValid());
		
		languageValidation = new LanguageValidation("fakeville");
		assertFalse(languageValidation.isValid());
	}

}
