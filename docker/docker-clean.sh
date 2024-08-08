#!/bin/bash

docker-compose -f docker-compose-postgresql.yml down
docker-compose -f docker-compose-emails.yml down
docker-compose -f docker-compose-pgadmin.yml down
docker image prune -a -f
docker volume prune -a -f