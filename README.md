# CS542: Assignment 2
## Name: Sagar Toke

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in numberPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile numberPlay/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile numberPlay/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

Requirement: input file should be in numberPlay folder
#### Use the below command to run the program.

```commandline
ant run -buildfile numberPlay/src/build.xml 
-DinputNumStream="<input file path>" 
-DrunAvgWindowSize="<size of the window for running average calculations>" 
-DrunAvgOutFile="<output file path to which running averages are written>" 
-Dk="<max size of the list containing the top K numbers>" 
-DtopKNumOutFile="<path of output file to which the top K numbers are written>" 
-DnumPeaksOutFile="<path of output file to which the peaks in the number stream are written>"
```

-----------------------------------------------------------------------
## Description:


##References and citation
https://www.geeksforgeeks.org/queue-interface-java/  : queue interface 
https://www.geeksforgeeks.org/bigdecimal-compareto-function-in-java/ bigDecimal ComapreTo method usage
https://www.baeldung.com/java-observer-pattern - Observer Pattern Concepts
Used File structure provided in the class for few methods in observers

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [02/25/2020]


