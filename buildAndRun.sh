#!/bin/sh
mvn clean package && docker build -t com.mycompany/BookARoom-v2 .
docker rm -f BookARoom-v2 || true && docker run -d -p 9080:9080 -p 9443:9443 --name BookARoom-v2 com.mycompany/BookARoom-v2