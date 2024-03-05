#!/bin/bash

read -p "[STOP] Enter the version number: " version

ssh root@192.168.100.120 "pkill -f /usr/local/www/backend-2024.03.$version-SNAPSHOT.jar"