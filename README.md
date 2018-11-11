# Geospatial pricing software
A coding challenge by [ProdutorAgro](https://www.linkedin.com/company/produtor-agro/)

# Libraries used
## Java
- [Spring Framework](https://spring.io/) Web App framework, used for the base server and the REST API handler
- [org.json](https://mvnrepository.com/artifact/org.json/json) Light-weight java json handler
- [Commons csv](https://commons.apache.org/proper/commons-csv/) Apache implementation for reading data from CSV files
## Javascript
- [Leaflet.js](https://leafletjs.com/) Light-weight open-source library for interactive maps
- [Turf.js](http://turfjs.org/) Geospatial analysis library used for data manipulation using geoJSONs

# Running
First be sure to build and run all the tests with

`gradlew build`

Then for serving simply

`gradlew bootRun`

To be sure it worked you should see these two lines:
```
Tomcat started on port(s): {port} (http)
Started App in {some} seconds (JVM running for {some})
```

# Structure
```
src/
├── main
│  ├── java
│  │  ├── api
│  │  │  ├── App.java [spring aplication starter]
│  │  │  ├── Formatter.java [will format the csv response to json]
│  │  │  └── Rest.java [rest api handler]
│  │  ├── database
│  │  │  └── CSVLoader.java [load the cvs files and return their data in a list]
│  │  └── model [csv response models, for ease in data transference]
│  │     ├── Fazenda.java
│  │     └── Servico.java
│  └── resources
│     ├── *.csv[files for the api to handle]
│     └── static [spring will load files here in the "/" path]
│        ├── index.html [html file to load the map]
│        ├── styles.css
│        └── scripts.js [map loading, map drawing and price calculator]
├── test/java/api
│  └── RestTest.java [Testing file for the REST API]
└──
```
