/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.isu.cs2263.hw01;

import org.apache.commons.cli.ParseException;

public class App {

    public static void main(String[] args) {


        InputReader in = null;
        try {
            in = new InputReader(args);
            in.checkInput();
        } catch (ParseException e) {
            e.printStackTrace();
        }







    }
}
