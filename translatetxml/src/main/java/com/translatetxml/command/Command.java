package com.translatetxml.command;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

public abstract class Command {
	
	public abstract String getName();

	public abstract String getDescription();

	public abstract Option getOptions();
	
	public abstract String getValue(CommandLine cmd);
    
}