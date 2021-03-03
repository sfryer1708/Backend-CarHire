To run this file build the project using maven, mvn clean install and the deploy the war file into an application server, I used tomEE.

The endpoints are:

Vehicle Endpoint
----------------
/vehicleHire/vehicle?onlyAvailable=true

The parameters indicates whether to display all available cars to hire or all of the cars.

Vehicle Hire Endpoint
---------------------
vehicleHire/vehicle/hireRate?regNumber=VN44%20ZXL&start=11-01-2021&end=13-01-2021

The parameters are the registration number of the car and the start and end date of the hire.

Improvements
------------

1. If I had more time I would have parsed the imported json file against a schema so only petrol/diesel cars would have been accepted, along with any other validations required.    The schema file then could be use in middleware to validate the request.
2. I would have added JSON annotations to the DTO classes, again this is helpful with validation of the data.
3. I would have create 2 mx's one for vehicles and one for hire functional, this way the design would adhere to DDD.
4. I would have got the data from a database and not from files, this mean I would have had to have used session beans.
5. I would have added some security to the application again this would have required session beans.
6. Given more time I would have improved the vehicle hire class so that it uses more functional programming techniques.
7. I would have JavaDoc'ed the classes.
8. I also don't think I validated that the boolean onlyAvailable parameter was true of false.

I would also like to rewrite this using SpringBoot, this would have made it easier to run.
