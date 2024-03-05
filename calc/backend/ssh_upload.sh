#!/bin/bash

read -p "[UPLOAD] Enter the version number: " version

scp build/libs/backend-2024.03.$version-SNAPSHOT.jar root@192.168.100.120:/usr/local/www
ssh root@192.168.100.120 "nohup java -jar /usr/local/www/backend-2024.03.$version-SNAPSHOT.jar > /usr/local/www/spring.log 2>&1 &"