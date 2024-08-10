#!/bin/bash

docker-compose -f docker-compose-postgresql.yml up -d
docker-compose -f docker-compose-emails.yml up -d
docker-compose -f docker-compose-pgadmin.yml up -d
