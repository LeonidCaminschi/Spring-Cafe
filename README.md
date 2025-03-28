# Simple Cafe application 

## Common parts if you use the project as is

access http://localhost:9001/ for the minio itself also remember 
user: minioadmin
password: minioadmin
!) These can always be changed from docker-compose file itself at any time

Minio buckets will be made in accordance to what profile you run the java application
if you do:
1) locally i suggest using dev profile it will create "development" bucket for testing
2) docker-compose it automatically runs as prod with the bucket "production"

/dishes - get the menu with all the dishes
/order - gets the dish you want to order and delegates it to one of the free cooks
/cook/add - adds another cook to the kitchen
/file/upload - uploads a multimedia file to the minio service
/file/download - returns a multimidia file by its name from minio

## Standalone java application

!) Make sure to start the docker containers for database and minio or replace the connection string for both in order for the app to work localy
access http://localhost:8080/swagger-ui/index.html for the swagger itself

## Docker compose application

!) Make sure to start the docker compose file before attempting to test or use the application
access http://localhost:8081/swagger-ui/index.html for the swagger itself
