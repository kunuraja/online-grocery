Online-Grocery is a Spring Boot e-commerce application. The application has provision to add customers, add groceries. A customer can place order of grocery/groceries via 
teh application.

Requirements:

For building and running the application you need:

JDK 21

Gradle

MySql

Running the application locally:

You can run the spring-boot application locally by using the gradle command:

./gradlew bootrun

Endpoints for accessing the application:

POST /customers - For adding customers

PUT /customers/{customerId} - For updating a customer

DELETE /customers/{customerId} - For deleting a customer

POST /groceries - For adding grocery

PUT /groceries/{groceryId} - For updating a grocery item

DELETE /groceries/{groceryId} - For deleting a grocery item

POST /customers/{customerId}/orders - For placing an order

DELETE /customers/{customerId}/orders/{orderId} - For removing an order



The Postman collection has been added to this project repository.
