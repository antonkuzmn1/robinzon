#!/bin/bash

read -p "[UPLOAD] Enter the version number: " version

gradle build && java -jar build/libs/backend-2024.03.$version-SNAPSHOT.jar