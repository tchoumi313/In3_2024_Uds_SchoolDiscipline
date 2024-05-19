# Use Ubuntu 22.04 as the base image
FROM ubuntu:22.04

# Install necessary packages
RUN apt-get update && apt-get install -y curl gnupg

# Install Java 17 (as specified in pom.xml)
RUN apt-get install -y openjdk-17-jdk

# Install Node.js (version 18.2.0 as specified in package.json)
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash -
RUN apt-get install -y nodejs

# Set the working directory in the container
WORKDIR /app

# Copy the backend and frontend code to the container
COPY src/backend/SchoolDisciplin ./backend/SchoolDisciplin
COPY src/frontend/SchoolDisciplin-React ./frontend/SchoolDisciplin-React

# Install backend dependencies
WORKDIR /app/backend/SchoolDisciplin
RUN ./mvnw install

# Install frontend dependencies
WORKDIR /app/frontend/SchoolDisciplin-React
RUN npm install

# Expose the port the app runs on
EXPOSE 8080
EXPOSE 3000

# Command to run the applications
# Note: This setup runs both applications sequentially for simplicity. Consider using a process manager for a more robust solution.
CMD ./mvnw spring-boot:run & cd ../frontend/SchoolDisciplin-React && npm start