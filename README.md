# POC : Data Ingestion layer for AGS - Campaign Management 

- Data Ingestion using [Apache Camel](https://camel.apache.org/components/3.4.x/file-component.html)
  -  Streamed Ingestion
  -  Executor Pool to ingest the data to Chronicle Map | Database
- Pre-Process and build [Chronicle Map](https://github.com/OpenHFT/Chronicle-Map) for caching
  -   Off heap Map for caching considering the volume of data to be cached
  -   [Chronicle Map features](https://github.com/OpenHFT/Chronicle-Map/blob/ea/docs/CM_Features.adoc)
- Jetty | Undertow Containers for Rest End Points
  - Low memory footprint
  - Non Blocking (Event Loop Arch) & NIO based
  - Quick boostrap time


### Reference Dataflow Architecture : 

![image](https://user-images.githubusercontent.com/5463838/132727197-1fe6361c-a68d-4a77-8f0f-4b314a1486c9.png)



### Build and Run instructions : 

Run the following commands from project directory through terminal/shell

* ```mvn clean install -DskipTests```

* Configure JDBC URL and file drop location in ```application.yaml``` as per your enviroment

* ```java -jar target/data-ingestion-staging-0.0.1-SNAPSHOT.jar```


### Note : 
  Make sure to use Java > 1.8 to leverage Chronicle Map features

* export JAVA_HOME=`/usr/libexec/java_home -v1.8.0`
