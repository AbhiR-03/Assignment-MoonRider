# Identity Reconciliation API – Zamazon

This project solves the **identity reconciliation problem** where the same user may use different email and phone combinations. Given(Showcased below) API accepts these inputs and returns all linked identities under a single user.

---
# TASK - 1 & 2

## Features

-Creates a new **primary** contact if no matching email/phone found.
- Creates a **secondary** contact if partial match found (new data).
- Maintains relational integrity across linked identities.
- Automatically reassigns primary/secondary roles on overlap.
- Seamless **CI/CD** with GitHub Actions and Render.
- Containerized using **Docker**, with multiple feature versions.
- Built with **Spring Boot + Maven**.

---

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (in-memory)
- Maven
- Postman/curl for testing
- GitHub Actions
- Render
---


## Build Locally
-Make sure you have JDK 21 installed
-Install Maven compatibe with JDK-21
- Run the command `./mvnw clean package` where your pom.xml lies in your folder
- Run the application `./mvnw spring-boot:run`
- 
##  API Endpoint

### `POST /identify`

#### Request Body:
```json
{
  "email": "doc@future.com",
  "phoneNumber": "9876543210"
}
```
### `GET /products`

### `GET /health`

## RUN LOCALLY
git clone https://github.com/AbhiR-03/<Repo-name>
cd identity-recon
./mvnw spring-boot:run

## H2 DataBase
Console URL: http://localhost:8080/h2-console
Headers: Content-Type: application/json
Body(Json): ```{
  "email": "doc@future.com",
  "phoneNumber": "9876543210"
}```

## Test with Postman
Method: POST
URL: http://localhost:8080/identify
Headers: Content-Type: application/json

## Error Handling 
- For an empty field of request in 'email' or 'password' results in 400 BadRequest.

## Docker (Containerization)
# Build Docker Image
- docker build -t rabhi03/java-assignment:v1 .
- docker run -p 8080:8080 rabhi03/java-assignment:v1

## Testing with KIND (Kubernetes IN Docker)
- created 3 different namespaces for each different version of the application.
- Deployed application to the respective namespace.
- Performed autoscaling(HPA) with stres-test
- Used Ingress for routing the application

## Used Render & GitHub Actions for CI-CD
- `https://assignment-moonrider.onrender.com/products`
- Render auto-deploys the app on every push to each deply.
