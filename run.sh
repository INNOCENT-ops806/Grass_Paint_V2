#!/bin/bash
echo "Building and running Maven project: Grass_Paint_V2..."

mvn clean package exec:java

if [ $? -ne 0 ]; then
  echo "Maven build/run failed. Check the output for details."
  exit 1
fi
