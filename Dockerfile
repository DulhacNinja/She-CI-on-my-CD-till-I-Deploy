# Use a base image with Java
FROM azul/zulu-openjdk:17.0.0-17.28.13-jre

# Install PostgreSQL
USER root
RUN apt-get update && \
    apt-get install -y postgresql postgresql-contrib && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Configure PostgreSQL to accept password authentication
RUN PG_VERSION=$(ls /etc/postgresql/) && \
    echo "local all postgres peer" > /etc/postgresql/$PG_VERSION/main/pg_hba.conf && \
    echo "host all all 127.0.0.1/32 md5" >> /etc/postgresql/$PG_VERSION/main/pg_hba.conf && \
    echo "host all all ::1/128 md5" >> /etc/postgresql/$PG_VERSION/main/pg_hba.conf

# Set working directory
WORKDIR /app

# Copy the war into the container
COPY target/*.war app.war

# Create startup script using RUN and echo
RUN echo '#!/bin/bash' > /app/start.sh && \
    echo 'service postgresql start' >> /app/start.sh && \
    echo 'until pg_isready -h localhost -p 5432; do' >> /app/start.sh && \
    echo '  echo "Waiting for PostgreSQL..."' >> /app/start.sh && \
    echo '  sleep 2' >> /app/start.sh && \
    echo 'done' >> /app/start.sh && \
    echo 'su - postgres -c "psql -c \"ALTER USER postgres WITH PASSWORD '\''postgres'\'';\""' >> /app/start.sh && \
    echo 'service postgresql restart' >> /app/start.sh && \
    echo 'sleep 3' >> /app/start.sh && \
    echo 'java -jar /app/app.war' >> /app/start.sh && \
    chmod +x /app/start.sh

# Expose ports
EXPOSE 8080
EXPOSE 5432

# Run the startup script
ENTRYPOINT ["/app/start.sh"]
