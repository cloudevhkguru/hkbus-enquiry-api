<div id="top"></div>
# Hong Kong Bus Enquiry API
<!-- ABOUT THE PROJECT -->
## About The Project
Bus Service Provides in Hong Kong have launched Open API for the public to access the information of bus routes, bus stops and estimated-time-of-arrival (ETA).

There are two sets of API and each set are providing information from groups of providers:

### <a href="https://data.gov.hk/en-data/dataset/hk-td-tis_21-etakmb" target="_blank">1. Kowloon Motor Bus and Long Win Bus (KMB and LWB)</a>

### <a href="https://data.gov.hk/en-data/dataset/nwfb-eta-transport-realtime-eta" target="_blank">2. Citybus, New Lantao Bus and New World First Bus (CTB and NWFB)</a>

However, the two major bus service providers operated independently and their APIs are having differentiation in the request and response.
- Both sets of API allow the user to use the get method to extract the information but have different requirements on path variables. For example, API of KMB and LWB does not require putting the company symbol (i.e. kmb or lwb) in the path meanwhile company symbol is a mandatory parameter for API of CTB and NWFB.

- Bus route API of KMB and LWB offers the direction of bus route meanwhile the API of CTB and NWFB does not.

This project is to create APIs using uniformed request and response for the user to get the information from the different bus service providers.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- Usage-->
## Usage

Please feel free to visit the <a href="https://lightsail.cloudev.guru/hkbus-enquiry-api/swagger-ui/" target="_blank">Swagger UI</a> of the API to have a trial.

<image src="https://hkbus-enquiry-web-cloudevhkguru.s3.ap-southeast-1.amazonaws.com/swagger_screencapture.png">

We have also setup a <a href="https://hkbus-enquiry-web-cloudevhkguru.s3.ap-southeast-1.amazonaws.com/index.html" target="_blank">demo website</a> for the public to try the service.

<image src="https://hkbus-enquiry-web-cloudevhkguru.s3.ap-southeast-1.amazonaws.com/website_screencapture.png">

<p align="right">(<a href="#top">back to top</a>)</p>
<!-- Reference -->

## Reference
- <a href="https://www.kmb.hk/en/" target="_blank">The Kowloon Motor Bus Co.(1933) Ltd</a>
- <a href="https://www.bravobus.com.hk/home/default.aspx?intLangID=1" target="_blank"> Citybus Limited & New World First Bus Services Limited (incorporated in the British Virgin Islands with limited liability)</a>

<p align="right">(<a href="#top">back to top</a>)</p>