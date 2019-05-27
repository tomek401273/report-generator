FROM adoptopenjdk/maven-openjdk11:latest
WORKDIR /home/tomek

RUN apt-get update && apt-get -y install \
                      apt-transport-https \
                      locales-all \
                      libpng16-16 \
                      libxinerama1 \
                      libgl1-mesa-glx \
                      libfontconfig1 \
                      libfreetype6 \
                      libxrender1 \
                      libxcb-shm0 \
                      libxcb-render0 \
                      adduser \
                      cpio \
                      findutils \
                      procps

RUN apt-get -y install libreoffice --no-install-recommends

#COPY ./target/report-generator-0.0.1-SNAPSHOT.jar ./app.jar
#CMD ["java", "-jar", "./app.jar"]

COPY . .
RUN mvn clean
RUN mvn install
CMD ["mvn", "spring-boot:run"]
