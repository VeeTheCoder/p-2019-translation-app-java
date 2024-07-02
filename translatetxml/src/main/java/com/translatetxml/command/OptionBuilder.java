package com.translatetxml.command;

import org.apache.commons.cli.Options;

public class OptionBuilder {

	public OptionBuilder() {
	}
	
    public static Options buildOptions() {
        Options options = new Options();
        options.addOption(new SourceFileCommand().getOptions());
        options.addOption(new OutputFileCommand().getOptions());
        options.addOption(new SourceLanguageCommand().getOptions());
        options.addOption(new TargetLanguageCommand().getOptions());
        options.addOption(new EvaluateCommand().getOptions());
        return options;
    }

}
