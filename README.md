# Business Card OCR Parser

This application parses out the Name, Phone, and Email information given a multi-line input of fields scanned from a typical business card. 

Sample input:

```
Foobar Technologies
Analytic Developer
Lisa Haung
1234 Sentry Road
Columbia, MD 12345
Phone: 410-555-1234
Fax: 410-555-4321
lisa.haung@foobartech.com
```

Output:

```
Name: Lisa Haung
Phone: 4105551234
Email: lisa.haung@foobartech.com
```

#####Assumptions and output behavior:
* An empty line determines that the input process is complete.
* The first valid 10 digit phone number is retrieved as the Phone Number.
* The Name field is derived using pattern matching from the email address field.
* If the email address is not present or does not match parts of the last name, the Name field cannot be determined.   
* The parser will display "Not found" of it cannot determine the field from the list.

## Getting Started

The Business Card OCR Parser application is written in Java and requires compatible JRE to run. 

## Technology Used:  

Java 8, Junit 5, Maven 3.6.1 

## Built With

* [Maven 3.6.1](https://maven.apache.org/) 


### Prerequisites:

Install [Maven](https://maven.apache.org/)  on your local environment.

Make sure you can successfully run the java from your environment:

```
$ java -version
openjdk version "1.8.0_212"
OpenJDK Runtime Environment (build 1.8.0_212-b04)
OpenJDK 64-Bit Server VM (build 25.212-b04, mixed mode)
```


Make sure you can successfully run Maven from your environment:

```
$ mvn -version
Apache Maven 3.6.1 (d66c9c0b3152b2e69ee9bac180bb8fcc8e6af555; 2019-04-04T15:00:29-04:00)
Maven home: /opt/maven
Java version: 1.8.0_212, vendor: Oracle Corporation, runtime: /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.212.b04-0.el7_6.x86_64/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "3.10.0-957.10.1.el7.x86_64", arch: "amd64", family: "unix"`
```

## Application Build Steps:

Clone repository:

```
git clone https://github.com/zandym/OcrParser.git
```

Go to the application directory:

```
cd OcrParser
```

You should see the following in the current directory:

```
-rw-r--r-- 1 xxxxxxx xxxxxxxx 1588 May 10 08:56 pom.xml
drwxr-xr-x 4 xxxxxxx xxxxxxxx   30 May  6 19:59 src
```

Clean target directory:

```
mvn clean
```
   
Compile application:

```
mvn compile
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------< OcrParser:OcrParser >-------------------------
[INFO] Building OcrParser 1.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.768 s
[INFO] Finished at: 2019-05-10T12:19:11-04:00
[INFO] ------------------------------------------------------------------------
```

Run automated Tests:

```
mvn test
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.ocr.ParserTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.022 s - in com.ocr.ParserTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  9.690 s
[INFO] Finished at: 2019-05-10T08:57:53-04:00
[INFO] ------------------------------------------------------------------------
```

Build application:

```
mvn install
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------< OcrParser:OcrParser >-------------------------
[INFO] Building OcrParser 1.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ OcrParser ---
[INFO] Building jar: /home/v535451/OcrParser/target/OcrParser-1.0.1-SNAPSHOT.jar
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.202 s
[INFO] Finished at: 2019-05-10T12:33:52-04:00
[INFO] ------------------------------------------------------------------------
```

##Running the application:

Go to target directory where the JAR file has been built

```
cd target
```


Start the application

```
java -classpath OcrParser-1.0.1-SNAPSHOT.jar com.ocr.Parser
 ```

The app will now prompt you to key in your input:


```
$ java -classpath OcrParser-1.0.1-SNAPSHOT.jar com.ocr.Parser
Please enter Contact Info to parse and then hit the Enter key:
```

Enter your input:

```
Arthur Wilson
Software Engineer
Decision & Security Technologies
ABC Technologies
123 North 11th Street
Suite 229
Arlington, VA 22209
Tel: +1 (703) 555-1259
Fax: +1 (703) 555-1200
awilson@abctech.com
```

Hit the Enter key to view the output:

```
Name: Arthur Wilson
Email: awilson@abctech.com
Phone: 17035551259
```

## Authors

* **Lyzander Marantal** - *Initial work* - [zandym](https://github.com/zandym)



## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Thanks to Asymmetrik for inspiring me to upload my first Github project!
