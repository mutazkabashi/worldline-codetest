# worldline-codetest
This Repository is a simple Rest-Api Microservice which allows user to create Profile and add Documents to his/her profile.
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
     description:Create profile for a user 
     url:http://localhost:8080/api/profiles  
     method:post  
     RequestBody:  {"name": "mutaz","country": "Sudan"} #all fields are Required, name and country should be Alphaptic only  
     SuccessResponseBody: {"name": "mutaz","country": "Sudan"}   
     SuccessStatus:201  
     ErrorResponseBody: {"message": "Profile Parameters are not valid make sure name, and county are valid"}   
     ErrorStatus:400  
     
     updateProfile
     description: update user's country using profile's name , name is primary key so it can't be modified    
     url:http://localhost:8080/api/profiles  
     method:put  
     RequestBody:  {"name": "mutaz","country": "Sudan"} #all fields are Required, name and country should be Alphaptic only  
     SuccessResponseBody: {"name": "mutaz","country": "Sudan"}   
     SuccessStatus:201  
     ErrorResponseBody: {"message": "Profile Parameters are not valid make sure name, and county are valid"}   
     ErrorStatus:400
   
    getProfile
    description: get user's profile info by pfroile's name
    url:http://localhost:8080/api/profiles/$name  
    method:get  
    SuccessResponseBody: {"name": "mutaz","country": "Sudan"}   
    SuccessStatus:200  
    ErrorResponseBody: {"message": "Profile Parameter name is not valid make sure name is valid"}   
    ErrorStatus:400  
    
### Document
    UploadDocument
     description:Upload Document using profile name, profile should be already exist before uploading the image or error message will return  
     "No Profile Found for the following User user-name"  
     url:http:localhost:8080/api/documents/image-upload  
     method:post  
     RequestBody:  {"userName": "user-name","fileName": "file-name", "attachment":"file-path"} #all fields are Required, name and country should be Alphaptic only  
     SuccessResponseBody: {"message":"Document Uploaded Successfully"}   
     SuccessStatus:200  
     ErrorResponseBody: {"message": "No Profile Found for the following User user-name"}   
     ErrorStatus:400  
     
     deleteDocuemnt
     description: delete Profile's Iamges using Profile's name    
     url:http://localhost:8080/api/documents/$name  
     method:delete  
     SuccessResponseBody: {"message": "Document Deleted Successfully"}   
     SuccessStatus:200  
     ErrorResponseBody: {""message": "Document Deletion Fail, try agin or contact System admin"}   
     ErrorStatus:400
   
    getDocument
    description: retrieve Profile's images using Profile's name 
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
 - Image will be saved in a folder in the harddisk while the path to the image will be saved to the Map ,  
 it is best practice in term of space,     
 - For Uploading Document(Image) using curl  
`curl  -F "attachment=path-to-image-file" -F "fileName=file-name" -F "userName=profile-name"  http://localhost:8080/api/documents/image-upload`



