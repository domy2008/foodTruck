FROM openjdk:latest

# Create a directory for the application
WORKDIR /app

# Copy the JAR and SQLite files
COPY my-app.jar /app/
COPY foodDB.db /app/

# Install the SQLite JDBC driver (optional)
#RUN apt-get update && apt-get install -y sqlite-jdbc

# Expose the port your application listens on (if applicable)
EXPOSE 9090

# Set the command to run your application
CMD ["java", "-jar", "my-app.jar"]

