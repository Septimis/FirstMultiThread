# Assign1

## Focus
This project was an assignment for CS3100, Operating Systems & Concurrency. I went the extra mile and implemented **multi-threading** to increase the speed of computing factorials of large numbers.  

## Description
The program is run from the command line and takes various arguments.  The three arguments the program recognizes are, '-e', '-fib', and '-fac'.  Each of these commands are followed by a numerical value. Several commands can be taken at once.  Below is a breakdown of the commands as well as example input.
***
#####-e
Accepted range of numeric input: *1 - 2,147,483,647*
Estimates the value of 'e' using a specified number of iterations of a Taylor Series.
***
##### -fib
Accepted range of numeric input: *0 - 40*
Computes the Fibonacci number at the desired input.  The sequence begins with 1; $F_0=1, F_1=1, F_2=2,3,4,8, ...$
***
##### -fac
Accepted range of numeric input: *1 - 2,147,483,647*
Utilizes multithreading to calculate the factorial of  a specified number.  The number of threads can be adjusted in Assign1.java as a constant variable.  
***
##### Accepted Input
*java Assign1 -e 1 -e 20 -e 20390928*
*java Assign1 -e 2147483647*
*java Assign1 -fib 0*
*java Assign1 -fib 10 -e 34 -e 20*
*java Assign1 -fac 1 -fac 2147483647*
*java Assign1 -fib 32 -e 2393 -fac 282888*
## Works Cited
BigInteger - https://www.geeksforgeeks.org/biginteger-class-in-java/
BigDecimal - https://www.geeksforgeeks.org/bigdecimal-class-java/ 
Multithreading - https://www.geeksforgeeks.org/multithreading-in-java/ 
Reading on estimating the value of *e* - https://www.dummies.com/education/math/calculus/expressing-and-approximating-functions-using-the-taylor-series/

## Misc info
This program was written in **Java** and took *roughly* 5 hours to make.

## Questions & Comments
Any questions & comments on my code can be addressed to connor.meads@protonmail.com
Created by: **Connor Meads**
