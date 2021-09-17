package edu.isu.cs2263.hw01;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.ArrayList;

// a class to manage the inputs from both the command line and batch files
public class InputReader {

    private Options options;
    private CommandLineParser parser;
    private CommandLine cmd;
    private HelpFormatter helpFormatter;
    private Eval eval;
    private String outputFile;
    private ArrayList<String> equations;

    public InputReader(String[] args, Eval eval) throws ParseException {
        this.options = new Options();

        options.addOption("h", "help", false, "print usage file")
                .addOption("b", "batch", true, "Load batch file with expression to be evaluated")
                .addOption("o", "output", true, "output file");


        this.parser = new DefaultParser();

        this.cmd = parser.parse(options, args);

        this.helpFormatter = new HelpFormatter();

        this.eval = eval;
        this.outputFile = null;
        this.equations = new ArrayList<>();

    }

    public void checkInput(){


        if(this.cmd.hasOption("h")){
            helpFormatter.printHelp("eval [Options] \n Evaluation of simple mathematical equations", options);
        }
        if(this.cmd.hasOption("b")){
            System.out.print("batch value: ");
            System.out.println(cmd.getOptionValue("b", "null"));
            this.readFromFile(cmd.getOptionValue("b"));
        }
        if(this.cmd.hasOption("o")){
            System.out.print("output value: ");
            System.out.println(cmd.getOptionValue("o", "null"));
            this.outputFile = cmd.getOptionValue("o");
        }

    }

    public String getOutputFile(){//for getting the output file.
        return this.outputFile;
    }

    public ArrayList<String> getEquations(){//for getting all equations solved by input file already
        return this.equations;
    }

    private void readFromFile(String path){//method for reading file provided
        File file = new File(path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
            e.printStackTrace();
        }

        String equation = "";

        while(true){//loop through all lines and solve all equations
            try {
                if (!((equation = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            float solution = this.eval.eval(equation);
            System.out.println(solution);
            this.equations.add(equation + " = " + solution);
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
