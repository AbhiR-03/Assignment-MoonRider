# Identity Reconciliation API – Zamazon

This project solves the **identity reconciliation problem** where the same user may use different email and phone combinations. The `/identify` API accepts these inputs and returns all linked identities under a single user.

---

## Features

- Identify unique users across different contact inputs
- Merge records under a single "primary" user
- Dynamically create secondary contact entries
- Automatically updates relationships when overlaps are found

---

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (in-memory)
- Maven
- Postman/curl for testing

---

##  API Endpoint

### `POST /identify`

#### Request Body:
```json
{
  "email": "doc@future.com",
  "phoneNumber": "9876543210"
}
```

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
