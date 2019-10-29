package se.lexicon.jonst;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {

    public static Scanner inputReader;          //Reads inputs

    static boolean stayingAlive;                //Loops the main function of the calc until user is done. Kept as global so the quitCalc method can reach it
    static boolean stillTakingInput;            //Loops to allow further parameters


    public static void main(String[] args) {
        System.out.println("Welcome to the super-calculator!");

        inputReader = new Scanner(System.in);

        stayingAlive = true;
        stillTakingInput = true;



        String firstValString;
        String secondValString;

        double firstVal = 0;                        //Input values, doubles are pre-initialized just to be sure
        double secondVal = 0;
        String operator;


        System.out.println("\n\nPlease input values and operator. Parameters are cumulative and use previous results as base." +
                "\nLegitimate operators are +, -, *, /, and %. Type c to clear equation and start over. Type q as operator to quit.\n");

/*

  */

        while (stayingAlive) {                    //The loop starts here. Keep allowing calculations until we quit.


            firstVal = askUserForLegitDouble("First value");        //Get value
            stillTakingInput = true;

            while (stillTakingInput) {
                operator = askUserFor("Operator");                          //Get operator

                if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/") || operator.equals("%")) {
                    secondVal = askUserForLegitDouble("Next value");
                }  //Get second value. ONLY if we as for legit operator

                switch (operator) {
                    case "+":
                        firstVal = add(firstVal, secondVal);          //Go to add method
                        System.out.println("Result: " + firstVal);      //Print result so far, but keep adding.
                        break;

                    case "-":
                        firstVal = subtract(firstVal, secondVal);     //Go to sub method
                        System.out.println("Result: " + firstVal);
                        break;

                    case "*":
                        firstVal = multiply(firstVal, secondVal);     //Go to mult method
                        System.out.println("Result: " + firstVal);
                        break;

                    case "/":
                        firstVal = divide(firstVal, secondVal);      //Go to div method
                        System.out.println("Result: " + firstVal);
                        break;

                    case "%":
                        firstVal = modulo(firstVal, secondVal);       //Go to mod method
                        System.out.println("Result: " + firstVal);
                        break;

                    case "q":
                        quitCalc();     //Breaks both loops and displays quit message
                        break;

                    case "c":
                        System.out.println("Data cleared.\n");       //Totals the last operation and breaks inner loop, allowing for starting over
                        stillTakingInput = false;
                        break;


                    default:
                        System.out.println("Illegal operator.");                            //If you don't use defined operators you will go to jail
                        break;
                }


            }


        }


        inputReader.close();                    //Put away your tools when you're done

    }


    static String askUserFor(String term) {     //Multi-use method for getting strings
        System.out.println(term + "?");
        return inputReader.nextLine();
    }

    static double askUserForLegitDouble(String term){       //This function always return parsable doubles
        boolean legitDouble;
        double parsedNumber = 0;
        do {
            legitDouble = true;             //Assume legit until proven otherwise
            try {
                parsedNumber = Double.parseDouble(askUserFor(term));
            } catch (NumberFormatException e) {                             //Can't parse? Catch the error
                System.out.println("Sorry, that's not a legitimate value.");
                legitDouble = false;
            }
        } while (legitDouble == false);         //Do over if you didn't get it right
        return parsedNumber;
    }




    static Double add(Double first, Double second) {         //Add method, returns sum
        return first + second;
    }

    static Double subtract(Double first, Double second) {       //Sub method, returns diff
        return first - second;
    }

    static Double multiply(Double first, Double second) {       //Mult method, yadda yadda
        return first * second;
    }

    static Double divide(Double first, Double second) {         //Div method, same as before
        return first / second;
    }

    static Double modulo(Double first, Double second) {         //Mod method, because we're extra
        return first % second;
    }

    static void quitCalc() {
        System.out.println("Thank you for using the calculator!");          //Quit: end the loops and be polite
        stayingAlive = false;
        stillTakingInput = false;
    }


}
