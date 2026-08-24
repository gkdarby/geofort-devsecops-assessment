FROM tomcat:10.1-jdk21-temurin

LABEL org.opencontainers.image.title="GeoFort Global Dynamic"
LABEL org.opencontainers.image.description="Dynamic Java Servlet web application"
LABEL org.opencontainers.image.vendor="GeoFort-Tech"

RUN rm -rf /usr/local/tomcat/webapps/*

COPY target/geofort-global-dynamic.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
