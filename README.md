# worldline-codetest
This Repository is a simple Rest-Api Microservice which provides Allow user to create Profile and add Documents to his/her profile.
The following end point are provided by this microservice:-
- Profile :- Allow User to create, update, retive information about his/her profile
- Document:- Allow User to Add, Retrive, Update, Delete documents to his/her profile

## TechStack
-Java 11  
-Maven  
-RestEasy  
-Jettey  
-Junit  
-Mockito  
-Swagger-UI

## How it Works
- clone this repository to your Machine.
- use the following maven commands to build the fat-Jar
   `mvn clean`
   `mvn package`  
   a new fat jar(worldline-codetest-1.0-SNAPSHOT.jar) file will be created under target folder
- run fat-jar file using the following command
   `java -jar target/worldline-codetest-1.0-SNAPSHOT.jar`


## SwaggerUI
This Microservice provides Swaager UI to test all of it endpoints from the web-broswer , so no need to use Curl,  
 or PostMan (Postman only needed for uploading Document)
 to test and check the end-points specification open the   
 following url from chrome Broswer `http://localhost:8080/`
  
## Rest-api pecification
### Profile
    createProfile  
     url:http://localhost:8080/api/profiles  
     method:post  
     RequestBody:  {"name": "mutaz","country": "Sudan"} #all fields are Required, name and country should be Alphaptic only  
     SuccessResponseBody: {"name": "mutaz","country": "Sudan"}   
     SuccessStatus:201  
     ErrorResponseBody: {"message": "Profile Parameters are not valid make sure name, and county are valid"}   
     ErrorStatus:400  
     
     updateProfile    
     url:http://localhost:8080/api/profiles  
     method:put  
     RequestBody:  {"name": "mutaz","country": "Sudan"} #all fields are Required, name and country should be Alphaptic only  
     SuccessResponseBody: {"name": "mutaz","country": "Sudan"}   
     SuccessStatus:201  
     ErrorResponseBody: {"message": "Profile Parameters are not valid make sure name, and county are valid"}   
     ErrorStatus:400
   
    getProfile
    url:http://localhost:8080/api/profiles/$name  
    method:get  
    SuccessResponseBody: {"name": "mutaz","country": "Sudan"}   
    SuccessStatus:200  
    ErrorResponseBody: {"message": "Profile Parameter name is not valid make sure name is valid"}   
    ErrorStatus:400  
    
### Document
    UploadDocument  
     url:http:localhost:8080/api/documents/image-upload  
     method:post  
     RequestBody:  {"name": "mutaz","country": "Sudan"} #all fields are Required, name and country should be Alphaptic only  
     SuccessResponseBody: {"name": "mutaz","country": "Sudan"}   
     SuccessStatus:201  
     ErrorResponseBody: {"message": "Profile Parameters are not valid make sure name, and county are valid"}   
     ErrorStatus:400  
     
     deleteDocuemnt    
     url:http://localhost:8080/api/documents/$name  
     method:delete  
     SuccessResponseBody: {"message": "Document Deleted Successfully"}   
     SuccessStatus:200  
     ErrorResponseBody: {""message": "Document Deletion Fail, try agin or contact System admin"}   
     ErrorStatus:400
   
    getDocument
    url:http://localhost:8080/api/documents/$name  
    method:get  
    SuccessResponseBody: { "documentUrls": [
                                            "documents/test2",
                                            "documents/test1"
                                             ]}   
    SuccessStatus:200  
    ErrorResponseBody: { "documentUrls": null}   
    ErrorStatus:200
  
  
### Note:-
For Uploading Document(Image) using curl  
`curl  -F "attachment=path-to-image-file" -F "fileName=file-name" -F "userName=profile-name"  http://localhost:8080/api/documents/image-upload`



