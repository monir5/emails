#!/bin/bash

echo -e "\n Enter the postgresql database username:"
read db_user

echo -e "\n Enter the postgresql database password:"
read db_pass

echo -e "\n Enter the pgadmin default email:"
read db_admin_email

cd docker
[ -e db.env ] && rm db.env
echo "POSTGRES_USER="$db_user >> db.env
echo "POSTGRES_PASSWORD="$db_pass >> db.env
echo "POSTGRES_DB=emails_db" >> db.env
docker-compose -f docker-compose-postgresql.yml up -d

cd ..
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/emails_db
export SPRING_DATASOURCE_USERNAME=$db_user
export SPRING_DATASOURCE_PASSWORD=$db_pass
./mvnw clean package

cd docker
[ -e api.env ] && rm api.env
echo "# Instead of Docker, in host: SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/emails_db" >> api.env
echo "SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/emails_db" >> api.env
echo "SPRING_DATASOURCE_USERNAME="$db_user >> api.env
echo "SPRING_DATASOURCE_PASSWORD="$db_pass >> api.env
docker-compose -f docker-compose-emails.yml up -d

[ -e db-admin.env ] && rm db-admin.env
echo "PGADMIN_DEFAULT_EMAIL="$db_admin_email >> db-admin.env
echo "PGADMIN_DEFAULT_USER="$db_user >> db-admin.env
echo "PGADMIN_DEFAULT_PASSWORD="$db_pass >> db-admin.env
docker-compose -f docker-compose-pgadmin.yml up -d
