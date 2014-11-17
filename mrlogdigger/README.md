Description:
------------
This is a simple implementation of a map reduce job to detect the presence of an IP address in a web server access log file.

Instructions/Commands:
----------------------

To checkout this project, run:
git clone https://github.com/aprameya/LogDigger.git


(The below commands assume you are in the \MapReduceLogDigger directory, underneath which you must see src directory)

To build the module using Maven:
	mvn clean install
This builds(cleans, compiles) the project, runs junit tests, and if tests pass successfully, generates the mrlogdigger-0.0.1-SNAPSHOT.jar

To run the game as a Java console application using Maven:
	mvn clean install exec:java -Dexec.mainClass=org.tutorial.bigdata.mapreduce.mrlogdigger.MapReduceJobApp -Dexec.args="src/test/resources/input outputfolder"
(where inputfolder=folder containing all the files that need to be fed as inputs for the job, outputfolder=unique output folder, not existing before execution of the job)
This uses the maven-exec-plugin to run the hadoop job, without any Hadoop infrastructure, i.e., without HDFS and MR related daemons.

