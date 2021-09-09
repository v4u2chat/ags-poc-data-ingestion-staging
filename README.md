# POC : Data Ingestion layer for AGS - Campaign Management 

- Data Ingestion Using Apache Camel
- Pre-Process and build Chronicle Map for caching and processing
- Jetty Rest End Point to verify the logic

![image](https://user-images.githubusercontent.com/5463838/132727197-1fe6361c-a68d-4a77-8f0f-4b314a1486c9.png)



### Note : 
  Make sure that we are using Java 1.8 to leverage Chronicle Map features

* export JAVA_HOME=`/usr/libexec/java_home -v1.8.0`

### Run the following commands from project directory through terminal/shell : 
* ```mvn clean install -DskipTests```

* ```java -jar target/data-ingestion-staging-0.0.1-SNAPSHOT.jar```
