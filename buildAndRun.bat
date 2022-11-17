@echo off
call mvn clean package
call docker build -t com.mycompany/BookARoom-v2 .
call docker rm -f BookARoom-v2
call docker run -d -p 9080:9080 -p 9443:9443 --name BookARoom-v2 com.mycompany/BookARoom-v2