#### Author: Ndung'u Mbiyu
#### Date: 29th May 2016


##### Architecture

This module is a stand alone application that starts up an embedded
Jetty server that queries the remote service when a request to
http://localhost:8090/waterpoints/summary.do is received. The main class
is io.ona.company.waterpoints.config.WaterPointsServer.

Maven is used for dependency management and also manages the build.

##### More Information
The following files contain more information on this project.

- codingChallenge.txt details the problem that this project attempts to
solve.

##### Running the module
###### Required Software

- JDK 7
- Maven 3

To run the module, open a terminal and navigate to the root directory of
the project.

To run the tests tests, run

	mvn clean test
	
To package and run the application, run

	mvn clean package
	java -jar target/waterpoints-1.0-SNAPSHOT-jar-with-dependencies.jar
	
Navigate to http://localhost:8090/waterpoints/summary.do to view the
summary of community water points.

To close the program, issue the Control-C command on the terminal that
lauched the program.
	
##### Importing the module in an IDE

To import the module into an IDE, run

	mvn eclipse:eclipse  (for Eclipse IDE)
	mvn idea:idea	     (for IntelliJ IDEA IDE)
	
to create projects for the IDE of your choice and then import the project.

##### Tests

There are unit tests in WaterPointJsonHelperTest that run against a
much smaller but similar dataset. There is also an integration test in
WaterPointServiceTest that asserts that the query to the remote service
yields a result.
