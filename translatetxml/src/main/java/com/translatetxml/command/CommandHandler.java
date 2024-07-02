package com.translatetxml.command;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.translatetxml.process.ProcessTranslation;
import com.translatetxml.validation.CommandValidation;

public class CommandHandler {
	private static HelpFormatter formatter = new HelpFormatter();
    private static CommandLineParser parser = new DefaultParser();
    private static CommandLine cmd;

    public static void parseArguments(String[] args) {
        Options options = OptionBuilder.buildOptions();

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("TranslateTXML", options);
            System.exit(1);
        }

        try {
	        CommandValidation validateCommands = new CommandValidation(new SourceFileCommand().getValue(cmd),new OutputFileCommand().getValue(cmd),new SourceLanguageCommand().getValue(cmd),new TargetLanguageCommand().getValue(cmd));
	        
		    if(!validateCommands.isValid()) {
		    	throw new IllegalArgumentException("Please Check your input arguments");
		     }
         } catch(IllegalArgumentException e) {
        	 System.out.println(e.getMessage());
 			 e.printStackTrace();
             System.exit(1);
         }
        
         ProcessTranslation processTranslation = new ProcessTranslation(new SourceFileCommand().getValue(cmd), 
					        											 new OutputFileCommand().getValue(cmd), 
					        											 new SourceLanguageCommand().getValue(cmd), 
					        											 new TargetLanguageCommand().getValue(cmd), 
					        											 cmd.hasOption('e'));
	        
         processTranslation.execute();
 

    }
}
