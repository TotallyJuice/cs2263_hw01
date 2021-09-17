package edu.isu.cs2263.hw01;

public class Eval {
//a class to perform the arithmetic on the strings passed into the program
    public Eval(){

    }

    public float eval(String equation){

        String[] symbols = equation.split("\\s+");

        float total = 0;

        try {

            total = Integer.parseInt(symbols[0]);

            for (int i = 1; i < symbols.length; i+=2) { //iterate through each pair of math symbol and number to do each operation
                if(symbols[i].equals("+")){
                    total += Integer.parseInt(symbols[i+1]);
                }else if(symbols[i].equals("-")){
                    total -= Integer.parseInt(symbols[i+1]);
                }else if(symbols[i].equals("/")){
                    total /= Integer.parseInt(symbols[i+1]);
                }else if(symbols[i].equals("*")){
                    total *= Integer.parseInt(symbols[i+1]);
                }else{
                    throw new Exception();
                }
            }

        }catch(Exception e){//catch if equation has any errors in its format
            System.out.println("Equation is not able to be read due to formatting errors");
        }

        return total;

    }

}
