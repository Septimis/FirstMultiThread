import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/* -- PROGRAM OVERVIEW --
 *
 * Created by: Connor Meads
 * Date: 19 JAN 2021
 * Due Date: 24 JAN 2021
 * Title: Assignment 1
 * 
 * Variable Naming Convention
 * a_varaible = argument (passed as an arg)
 * l_variable = local variable (declared in scope)
 * m_variable = member variable (declared in class)
 * g_variable = global variable (declared in program file)
 */
class MultiThread implements Runnable {
    private Thread m_thread;
    private String m_threadName;
    private int m_iterations;
    private int m_modNum;
    private BigInteger m_result;
    private int m_numOfThreads;

    //Constructor
    MultiThread(String a_threadName, int a_iterations, int a_modNum, int a_numOfThreads) {
        m_threadName = a_threadName;
        m_iterations = a_iterations;
        m_modNum = a_modNum;
        m_numOfThreads = a_numOfThreads;
    }

    //This function runs the actual processes of the thread
    public void run() {
        try {
            m_result = BigInteger.ONE;
            for(int l_i = m_modNum; l_i <= m_iterations; l_i += m_numOfThreads) {
                m_result = m_result.multiply(BigInteger.valueOf(l_i));
            }

        } catch (Exception l_e) {
            System.out.println("Thread " + m_threadName + " was interrupted with error listed below: \n" + l_e.toString());
        }
    }

    //What the user calls to start a thread after declaring it
    public void start() {        
        if (m_thread == null) {
            m_thread = new Thread (this, m_threadName);
            m_thread.start();
        } else {
            System.out.println("There's already a thread started!");
        }
    }

    public BigInteger getFactorial() {
        return this.m_result;
    }

    public Boolean getIsAlive() {
        return m_thread.isAlive();
    }
    public String getName() {
        return m_threadName;
    }
}

class Assign1 {
    //** You can adjust the number of threads that this program uses to calculate the factorial here **
    private static final int NUM_OF_THREADS = 10;

    public static void main(String[] a_args) {
        String l_errMsg = "                             THE FOLLOWING ERRORS WERE FOUND\n";
        Boolean l_noErrors = true;
        String l_actionArg= "empty";

        //intro
        System.out.println("Assignment #1 - CS3100");
        System.out.println("Created by: Connor Meads - A01853906");
        System.out.println("Number of threads being used in factorial computation: " + NUM_OF_THREADS + "\n");

        //Check input for validity
        for(int i = 0; i < a_args.length; i++) {
            if(i % 2 == 0) { //In the instance of a '^-(fib|fac|e)$'
                switch(a_args[i]) {
                    case "-fib": l_actionArg= a_args[i];
                    break;
                    case "-fac": l_actionArg= a_args[i];
                    break;
                    case "-e": l_actionArg= a_args[i];
                    break;
                    default: l_errMsg = l_errMsg + "The argument, " + a_args[i] + ", did not follow the correct input guidlines.  Please refer below for correct input...\n";
                    l_actionArg= "err";
                    l_noErrors = false;
                    break;
                }
            } else { //In the instance of a number
                //Checks if the current argument is numeric or not
                if(!isNumeric(a_args[i])) {
                    l_errMsg = l_errMsg + "The argument, " + a_args[i] + ", is not a number.  Please refer below for the correct input\n";
                    l_noErrors = false;
                } else {
                    //depending on the actionArg, the number needs to be within certain parameters
                    if(l_actionArg.equals("-fib")) {
                        //Checks the correct range of numbers for the fibonacci range
                        if(Integer.parseInt(a_args[i]) >= 0 && Integer.parseInt(a_args[i]) <= 40) {
                            printFibonacci(Integer.parseInt(a_args[i]));
                        } else {
                            l_errMsg = l_errMsg + a_args[i] + " is not within range for the fibonacci actionArg.  Please refer below for correct input...\n";
                            l_noErrors = false;
                        }
                    } else if(l_actionArg.equals("-fac")) {
                        //Checks the correct range of numbers for the factorial range
                        if(Integer.parseInt(a_args[i]) >= 0 && Integer.parseInt(a_args[i]) <= Integer.MAX_VALUE) {
                            printFactorial(Integer.parseInt(a_args[i]));
                        } else {
                            l_errMsg = l_errMsg + a_args[i] + " is not within range for the factorial actionArg.  Please refer below for correct input...\n";
                            l_noErrors = false;
                        }
                    } else if(l_actionArg.equals("-e")) {
                        //Checks the correct range of numbers for 'e'
                        if(Integer.parseInt(a_args[i]) >= 1 && Integer.parseInt(a_args[i]) <= Integer.MAX_VALUE) {
                            printE(Integer.parseInt(a_args[i]));
                        } else {
                            l_errMsg = l_errMsg + a_args[i] + " is not within the range for the 'e' actionArg.  Please refer below for correct input...\n";
                        }
                    } else {
                        l_errMsg = l_errMsg + "Your argument had errors.  Please refer below for correct input...\n";
                        l_noErrors = false;
                    }
                }

                
            }
        }
        if(!l_noErrors) {
            invalidInputErr(l_errMsg);
        }
    }

    //Function to calculate the Factorial of a specified number
    static void printFactorial(int a_iterations) {
        BigInteger l_factorial = BigInteger.ONE;
        int l_numThreads = NUM_OF_THREADS;
        if(l_numThreads >= 1) {
            //initiate threads
            ArrayList<MultiThread> l_threadList = new ArrayList<MultiThread>();
            for(int l_i = 1; l_i <= NUM_OF_THREADS; l_i++) {
                MultiThread l_newThread = new MultiThread("Thread " + l_i, a_iterations, l_i, NUM_OF_THREADS);
                l_threadList.add(l_newThread);
                l_newThread.start();
            }

            //loop to check on status of threads
            Boolean l_isDone = false;
            while(!l_isDone) {
                l_isDone = true;
                for(int i = 0; i < l_threadList.size(); i++) {
                    if(l_threadList.get(i).getIsAlive()) {
                        l_isDone = false;
                        try {
                            Thread.sleep(50); //Contributes to the speed
                        } catch (InterruptedException a_e) {
                            System.out.println("The following exception is being thrown: " + a_e.toString());
                        }
                        break;
                    }
                }
            }
            
            //Calculate the total factorial
            for(int i = 0; i < l_threadList.size(); i++) {
                l_factorial = l_factorial.multiply(l_threadList.get(i).getFactorial());
            }
        } else {
            // Code used BEFORE I started multi-threading (it works, but slowly)
            for(int i = 1; i <= a_iterations; i++) {
                l_factorial = l_factorial.multiply(BigInteger.valueOf(i));
                if(i % 100 == 0) {
                    int l_progress = i * 100 / a_iterations;
                    System.out.print("Progress: " + l_progress + "%\r");
                }
            }
        }      
        //Print out the final result of the factorial
        System.out.println("Factorial of " + a_iterations + " is " + l_factorial);
    }

    //Function to calculate the estimated value of 'e' to a specified degree
    private static void printE(int a_iterations) {
        BigDecimal l_sum = BigDecimal.valueOf(0.0);
        for(int i = 0; i < a_iterations; i++) {
            //for loop to calculate factorial
            double l_factorial = 1;
            for(int j = 1; j <= i; j++) {
                l_factorial *= j;
            }
            l_sum = l_sum.add(new BigDecimal(1/l_factorial));
        }
        DecimalFormat l_format = new DecimalFormat("#.0000000000000000");
        System.out.println("Value of e using " + a_iterations + " iteration(s) is: " + l_format.format(l_sum));
    }

    //Function to calculate the fibonacci sequence of a specified number
    private static void printFibonacci(int a_iterations) {
        int l_i1 = 0; 
        int l_i2 = 1;

        for(int i = 0; i < a_iterations; i++) {
            int i_n = l_i1 + l_i2;
            l_i1 = l_i2;
            l_i2 = i_n;
        }
        System.out.println("The Fibbonacci of " + a_iterations + " is " + l_i2);
    }

    // Function that checks to see if a string is numeric
    private static Boolean isNumeric(String a_strNum) {
        if(a_strNum == null) {
            return false;
        }
        try {
            int l_i = Integer.parseInt(a_strNum);
            if(l_i > Integer.MAX_VALUE || l_i < 0) {
                return false;
            }
        } catch (NumberFormatException l_nfe) {
            return false;
        }
        return true;
    }

    // Function that prints out error stating improper input
    private static void invalidInputErr(String a_additionalErr) {
        System.out.println("\n###############################################################################################");
        System.out.println(a_additionalErr);
        System.out.println("#################################################################################################\n\n");
        System.out.println("--- Assignment 1 Help ---");
        System.out.println(" -fib [n] : Compute the Fibonacci of [n]; valid range [0, 40]");
        System.out.println(" -fac [n] : Compute the factorial of [n]; valid range, [0, 2147483647]");
        System.out.println(" -e [n] : Compute the value of 'e' using [n] iterations; valid range [1, 2147483647\n");

    }
}