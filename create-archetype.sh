#!/bin/bash

mvn archetype:generate \
    -DgroupId=com.endplay.distribution.app \
    -DartifactId=redis-data-distribution \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DinteractiveMode=false


