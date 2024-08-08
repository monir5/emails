# emails : 
An Email Service exposes a REST API to execute CRUD operations. 
## Technologies: 
Java 22, spring boot 3.2.8, PostgreSQL 16, Flyway, mapstruct, springdoc/swagger, spring actuator, Docker

## Getting started with development
## Source Code
1. With `git clone https://github.com/monir5/emails` the comlete repository is cloned.
2. Please install Postgresql 16 locally or run docker image.
    ```cd docker```
    Please create a file db.env as per instruction of db.env.sample file.
    ```docker-compose -f docker-compose-postgresql.yml up -d```
3. Set up three environment variables: 
    SPRING_DATASOURCE_URL=<database_url> e.g. jdbc:postgresql://localhost:5432/<database> 
    SPRING_DATASOURCE_USERNAME=<database_user> 
    SPRING_DATASOURCE_PASSWORD=<password>
4. build & run 
   ```./mvnw clean install```
   ```./mvnw spring-boot:run```

#### Docker (optional)
```./mvnw clean package```
```cd docker```
Please create a file db.env as per instruction of db.env.sample file. 
```docker-compose -f docker-compose-postgresql.yml up -d```
Please create a file api.env as per instruction of api.env.sample file.
```docker-compose -f docker-compose-emails.yml up -d```
Please create a file db-admin.env as per instruction of db-admin.env.sample file.
```docker-compose -f docker-compose-pgadmin.yml up -d```
To down/stop docker containers
```docker-compose -f docker-compose-postgresql.yml down```
```docker-compose -f docker-compose-emails.yml down```
```docker-compose -f docker-compose-pgadmin.yml down```


#### Now the following URLs are available.
swagger ui: http://localhost:8080/swagger-ui/index.html
api docs: http://localhost:8080/api-docs 
health: http://localhost:8080/actuator/health
pgadmin: http://localhost:8888/ 

##### Endpoints: 
POST    /emails/create
POST    /emails/create/bulk
PUT     /emails/update/{emailId}
DELETE  /emails/delete/{emailId}
DELETE  /emails/delete/bulk
GET     /emails/{emailId}
GET     /emails/all
GET     /emails/from/{emailFrom}




#######################################################################################################################
This is a use case to replicate the behavior of an email server. Endpoints are under the route "/emails"
and the information corresponding to the email contents are being stored in a database.

#### Requirements
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


