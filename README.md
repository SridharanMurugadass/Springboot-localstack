# Spring Boot API with LocalStack s3 Bucket



Create a Local Stack Environment

Install Docker 

https://docs.docker.com/docker-for-windows/install/

Step 1 :

docker-compose up -d.   (check "http://localhost:4572" for the localstack S3, "http://localhost:8080/#/infra" for S3 localstack dashboard)

To Create bucket in localstack :

aws --endpoint-url=http://localhost:4572 s3 mb s3://demo-bucket

Step 2 :

mvn clean install

Step 3 :

docker build -t="Springboot-localstack.jar" .

Step 4 :

docker run -p 8081:8081 -it --rm Springboot-localstack.jar

Testing the service :

Upload file :

http://localhost:8081/uploadFile

form-data
 key : files
 value : attach any file
 
List Files :

http://localhost:8081/listFiles

Delete File :

http://localhost:8081/deleteFile/{key}


Note : You can test without docker by starting the service 

/target/

java -jar Springboot-localstack-0.0.1-SNAPSHOT.jar


Please find all the testing snapshots here 
https://github.com/SridharanMurugadass/Springboot-localstack/tree/master/data





 
 





