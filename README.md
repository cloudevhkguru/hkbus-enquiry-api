<div id="top"></div>

# Hong Kong Bus Enquiry API
<!-- ABOUT THE PROJECT -->
## About The Project
This project is to create APIs with Maven for consumer to use uniformed request and response to get the information from the different bus service providers.

Bus Service Provides in Hong Kong have launched Open API for the public to access the information of bus routes, bus stops and estimated-time-of-arrival (ETA).

There are two sets of API and each set are providing information from groups of providers:

### <a href="https://data.gov.hk/en-data/dataset/hk-td-tis_21-etakmb" target="_blank">1. Kowloon Motor Bus and Long Win Bus (KMB and LWB)</a>

### <a href="https://data.gov.hk/en-data/dataset/nwfb-eta-transport-realtime-eta" target="_blank">2. Citybus, New Lantao Bus and New World First Bus (CTB and NWFB)</a>

However, the two major bus service providers operated independently and their APIs are having differentiation in the request and response.
- Both sets of API allow the user to use the get method to extract the information but have different requirements on path variables. For example, API of KMB and LWB does not require putting the company symbol (i.e. kmb or lwb) in the path meanwhile company symbol is a mandatory parameter for API of CTB and NWFB.

- Bus route API of KMB and LWB offers the direction of bus route meanwhile the API of CTB and NWFB does not.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- Usage-->
## Usage

Please feel free to visit the <a href="https://lightsail.cloudev.guru/hkbus-enquiry-api/swagger-ui/" target="_blank">Swagger UI</a> of the API to have a trial.

<image src="https://hkbus-enquiry-web-cloudevhkguru.s3.ap-southeast-1.amazonaws.com/swagger_screencapture.png">

We have also setup a <a href="https://hkbus-enquiry-web-cloudevhkguru.s3.ap-southeast-1.amazonaws.com/index.html" target="_blank">demo website</a> for the public to try the service.

<image src="https://hkbus-enquiry-web-cloudevhkguru.s3.ap-southeast-1.amazonaws.com/website_screencapture.png">

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- Build project on localhost-->

## Working with the source code
To build it, you will need to download and unpack the latest (or recent) version of Maven (https://maven.apache.org/download.cgi) and put the mvn command on your path. Then, you will need to install a Java 1.8 (or higher) JDK (not JRE!), and make sure you can run java from the command line. Now you can run mvn clean install and Maven will compile your project, an put the results it in a jar file in the target directory.

How you run this code is up to you, but usually you would start by using an IDE like NetBeans, Intellij IDEA, or Eclipse.

Once you have configured your project in your IDE you can build it from there. However if you prefer you can use maven from the command line. In that case you could be interested in this short list of commands:

* `mvn compile`: it will just compile the code of your application and tell you if there are errors
* `mvn test`: it will compile the code of your application and your tests. It will then run your tests (if you wrote any) and let you know if some fails
* `mvn package`: it will do everything `mvn test` does and then if everything looks file it will package the jar file in the ./target folder. Once the jar file is packaged, you can move it to any folder with a machine having Java JRE and start with the command `java -jar hkbus-enquiry-api.jar` 

<!-- Reference -->

## Reference
- <a href="https://www.kmb.hk/en/" target="_blank">The Kowloon Motor Bus Co.(1933) Ltd</a>
- <a href="https://www.bravobus.com.hk/home/default.aspx?intLangID=1" target="_blank"> Citybus Limited & New World First Bus Services Limited (incorporated in the British Virgin Islands with limited liability)</a>

<p align="right">(<a href="#top">back to top</a>)</p>