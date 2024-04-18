#!/bin/sh
# Build the Docker image
docker build -t school-disciplin-app .

# Run the Docker container
docker run -p 3000:3000 -p 8080:8080 school-disciplin-app