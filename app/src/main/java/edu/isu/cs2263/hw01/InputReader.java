package edu.isu.cs2263.hw01;

import org.apache.commons.cli.*;

// a class to manage the inputs from both the command line and batch files
public class InputReader {

    private Options options;
    private CommandLineParser parser;
    private CommandLine cmd;
    private HelpFormatter helpFormatter;

    public InputReader(String[] args) throws ParseException {
        this.options = new Options();

        options.addOption("h", "help", false, "print usage file")
                .addOption("b", "batch", true, "Load batch file with expression to be evaluated")
                .addOption("o", "output", true, "output file");


        this.parser = new DefaultParser();

        this.cmd = parser.parse(options, args);

        this.helpFormatter = new HelpFormatter();

    }

    public void checkInput(){


        if(this.cmd.hasOption("h")){
            helpFormatter.printHelp("eval [Options] \n Evaluation of simple mathematical equations", options);
        }else if(this.cmd.hasOption("b")){
            System.out.print("batch value: ");
            System.out.println(cmd.getOptionValue("b", "null"));
        }else if(this.cmd.hasOption("o")){
            System.out.print("output value: ");
            System.out.println(cmd.getOptionValue("o", "null"));
        }

    }

}
