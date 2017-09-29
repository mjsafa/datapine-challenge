# DataPine challenge project
This project implements the code challenge specifications from datapine.

#Build
use `mvn clean install` to build the project

#Executing Queries
To improve performance and also controlling the impact on customer database, queries are executed using a thread pool.
 To control number of threads that execute queries, change the following value in application.properties file:
 
`query.executor.threads=10`

#Validating requests
all requests are validated using java bean validation. if a invalid parameters are passed, service will respond with 400 error code (bad request) and the body of response will contain error messages.  