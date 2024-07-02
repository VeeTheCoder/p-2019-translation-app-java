package com.translatetxml.validation;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandValidationTest {

	@Test
	public void testIsValid() {
		CommandValidation commandValidation;
		commandValidation = new CommandValidation("source.txml", "output.txml", "en", "fr");
		assertTrue(commandValidation.isValid());
		
		commandValidation = new CommandValidation("source.xml", "output.txml", "en", "fr");
		assertFalse(commandValidation.isValid());
		
		commandValidation = new CommandValidation("source.txml", "output.xml", "en", "fr");
		assertFalse(commandValidation.isValid());
		
		commandValidation = new CommandValidation("source.txml", "output.txml", "fakelang", "fr");
		assertFalse(commandValidation.isValid());
		
		commandValidation = new CommandValidation("source.txml", "output.txml", "en", "fakelang");
		assertFalse(commandValidation.isValid());
	}


}
