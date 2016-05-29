========================================================================
Author: Ndung'u Mbiyu
Date:	29th May 2016
========================================================================

1. Required Software
========================================================================

- JDK 7
- Maven 3

========================================================================
2. More Information
========================================================================
The following files contain more information on this project.

- codingChallenge.txt details the problem that this project attempts to
solve.

========================================================================
3. Running the module
========================================================================

To run the module, open a terminal and navigate to the root directory of
the project.

To run the tests tests, run

	mvn clean test
	
To package and run the application, run

	mvn clean package
	java -jar target/waterpoints-1.0-SNAPSHOT-jar-with-dependencies.jar
	
========================================================================
4. Importing the module in an IDE
========================================================================

To import the module into an IDE, run

	mvn eclipse:eclipse  (for Eclipse IDE)
	mvn idea:idea	     (for IntelliJ IDEA IDE)
	
to create projects for the IDE of your choice and then import the project.
