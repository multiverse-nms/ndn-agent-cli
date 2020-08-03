# nms-cli-app
CLI app to interact with the NMS agent

## How To Run

#### What you’ll need
 
- JDK 8 or later
- Install Maven

#### Build the Agent

`mvn compile`
- To run Maven, telling it to execute the compile goal.

`mvn package`
- To compile, run tests and package your code in a JAR file that can be found in the target directory.

- **Note:** for this project to build correctly run the following command `mvn package shade:shade` since we use Maven Shade Plugin to create a jar with its dependency jars into a single jar file

`mvn install`
- Compile, test, package your code and copy it in the local dependency repository.
 
 #### Run the Agent
 
 To run the cli-app use the following command, where `<target>` is the path to your fat .jar file that was generated during the `mvn package` step:
 
 `java -jar <target>`

**Note:** target folder will contain 2 jar files, `xxx.jar` and `xxx-fat.jar`.
 
 

