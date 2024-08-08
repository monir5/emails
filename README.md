## emails : An Email Service exposes a REST API to execute CRUD operations. 
#### Technologies: 
Java 22, spring boot 3.2.8, PostgreSQL 16, Flyway, mapstruct, springdoc/swagger, spring actuator, Docker
### Getting started with development
#### Source Code
1. **Git Clone:**<br>
   `git clone https://github.com/monir5/emails`
2. **Database PostgreSQL 16:** <br>
   install locally or docker image (see below `Docker Compose Database` section).
3. **Environment variables:**<br>
  `SPRING_DATASOURCE_URL=<database_url>` e.g. `jdbc:postgresql://localhost:5432/<database-name-same-in-db.env>`<br>
  `SPRING_DATASOURCE_USERNAME=<database_user-same-in-db.env>`<br>
  `SPRING_DATASOURCE_PASSWORD=<password-same-in-db.env>`<br>
    example for macos environment variables setting in command line temporary.
   ```
   export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/emails_db
   export SPRING_DATASOURCE_USERNAME=admin
   export SPRING_DATASOURCE_PASSWORD=<secretPasswordWhatYouLike>
   ```
4. **build & run:** <br>
   in command line go to the project root folder `cd ..` <br>
   `./mvnw clean install`<br>
   `./mvnw spring-boot:run`
### Docker (optional)
1. **Docker Compose Database:**<br> 
   Please create a file `docker/db.env` as per instruction of `docker/db.env.sample` file. <br>
   `cd docker`<br>
  `docker-compose -f docker-compose-postgresql.yml up -d`
2. **Docker Compose API Service**<br>
   Please create a file `docker/api.env` as per instruction of `docker/api.env.sample` file.<br>
   go to root folder `cd ..`<br>
   maven package jar file: `./mvnw clean package`<br>
   `cd docker`<br>
   `docker-compose -f docker-compose-emails.yml up -d`
3. **Docker Compose PGAdmin4 **<br> 
   Please create a file `docker/db-admin.env` as per instruction of `docker/db-admin.env.sample` file.<br>
   `cd docker`<br>
   `docker-compose -f docker-compose-pgadmin.yml up -d`
#### To down/stop docker containers
1. `cd docker`
2. `docker-compose -f docker-compose-postgresql.yml down`
3. `docker-compose -f docker-compose-emails.yml down`
4. `docker-compose -f docker-compose-pgadmin.yml down`

### Now the following URLs are available.
1. swagger ui: http://localhost:8080/swagger-ui/index.html
2. api docs: http://localhost:8080/api-docs 
3. health: http://localhost:8080/actuator/health
4. pgadmin4 (if docker compose PGAdmin4 is orchestrated): http://localhost:8888/ 
#### API Endpoints: 
1. POST    /emails/create
2. POST    /emails/create/bulk
3. PUT     /emails/update/{emailId}
4. DELETE  /emails/delete/{emailId}
5. DELETE  /emails/delete/bulk
6. GET     /emails/{emailId}
7. GET     /emails/all
8. GET     /emails/from/{emailFrom}
   
# Project Description
This is a use case to replicate the behavior of an email server. Endpoints are under the route "/emails"
and the information corresponding to the email contents are being stored in a database.

### Requirements
- The probable status of an Email: DRAFT, SENT, DELETED, SPAM
- The basic information of an Email, such as, from, to, etc., update dates are being stored in the database.
- For inserting, query and delete the option of bulk operation are available.
- Only DRAFT emails could be updated (the content of the message, for instance).
- Every day at 10:00, all the messages containing the address carl@gbtec.com are marked as SPAM

__Email Example:__  A JSON representing an email.
````
{
    "emails": [
        {
            "emailId": 1234,
            "emailFrom": "abc@domain.de",
            "emailTo": [
                {
                    "email": "peter@domain.de"
                },
                {
                    "email": "sabine@domain.de"
                }
            ],
            "emailCC":  [
                {
                    "email": "mueller@domain.de"
                },
                {
                    "email": "nina@domain.de"
                }
            ],
            "emailBody": "Body Text",
            "state": 1
        },

            "emailId": 1234,
            "emailFrom": "abc@domain.de",
            "emailTo": [
                {
                    "email": "michael@domain.de"
                },
                {
                    "email": "sam@domain.de"
                }
            ],
            "emailCC":  [
                {
                    "email": "mueller@domain.de"
                },
                {
                    "email": "nina@domain.de"
                }
            ],
            "emailBody": "Body Text",
            "state": 2
        }
    ]
}
```


