package edu.isu.cs2263.hw01;

import org.apache.commons.cli.*;

import java.io.*;

// a class to manage the inputs from both the command line and batch files
public class InputReader {

    private Options options;
    private CommandLineParser parser;
    private CommandLine cmd;
    private HelpFormatter helpFormatter;
    Eval eval;

    public InputReader(String[] args, Eval eval) throws ParseException {
        this.options = new Options();

        options.addOption("h", "help", false, "print usage file")
                .addOption("b", "batch", true, "Load batch file with expression to be evaluated")
                .addOption("o", "output", true, "output file");


        this.parser = new DefaultParser();

        this.cmd = parser.parse(options, args);

        this.helpFormatter = new HelpFormatter();

        this.eval = eval;

    }

    public void checkInput(){


        if(this.cmd.hasOption("h")){
            helpFormatter.printHelp("eval [Options] \n Evaluation of simple mathematical equations", options);
        }else if(this.cmd.hasOption("b")){
            System.out.print("batch value: ");
            System.out.println(cmd.getOptionValue("b", "null"));
            this.readFromFile(cmd.getOptionValue("b"));
        }else if(this.cmd.hasOption("o")){
            System.out.print("output value: ");
            System.out.println(cmd.getOptionValue("o", "null"));
        }

    }

    private void readFromFile(String path){
        File file = new File(path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
            e.printStackTrace();
        }

        String equation = "";

        while(true){
            try {
                if (!((equation = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(this.eval.eval(equation));
        }


    }

}
