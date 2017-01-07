#!/bin/bash
gradle clean
gradle bootRepackage
mv core/build/libs/core-1.0-SNAPSHOT.war core/build/libs/rest.war

