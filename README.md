# fish-with-odata
OData version of Rui`s FISH import and export examplo for HCP and IoT

Sample app based on http://scn.sap.com/docs/DOC-56616 Uses SAP HCP to host a Java app as an IoT service for storing device sensor data. Demonstrates the basic of IoT. Consists of two components: server and client.

## Server
Server app uses Olingo to expose IoT data from database via JPA (eclipselink). Server will be started via maven and listen on port 7080. A basic HTML dashboard is included.

## Client
Client app is a pure Java app that simulates sensor data by reading the weather data from openweathermap.org (you`ll need a free API key for this work). As a convenience, a jMeter test is included that simulates random data.

# More information
https://www.itsfullofstars.de/2016/04/fish-with-odata/
