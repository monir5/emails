#!/bin/bash

docker-compose -f docker-compose-postgresql.yml down
docker-compose -f docker-compose-emails.yml down
docker-compose -f docker-compose-pgadmin.yml down
