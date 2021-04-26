# nms-cli-app
CLI app to interact with the NMS agent

## How To Run

#### What you’ll need
 
- JDK 8 or later
- Install Maven

#### Build the Agent

`mvn compile`
- To run Maven, telling it to execute the compile goal.

`mvn package shade:shade`
- To compile, run tests and package your code in a JAR file that can be found in the target directory.

-  **Note:** to build correctly since we use Maven Shade Plugin to create a jar with its dependency jars into a single jar file

`mvn install`
- Compile, test, package your code and copy it in the local dependency repository.
 
 #### Run the Agent
 
 - To run the cli-app: In the command line use the following command, where `<target>` is the path to your fat .jar file that was generated during the `mvn package` step:
 
 `Function nmscli {java -jar  <target> @args }`

**Note:** target folder will contain 2 jar files, `xxx.jar` and `xxx-fat.jar`.

 - To get started use the help option: 
 
 `nmscli -h `
 
 

