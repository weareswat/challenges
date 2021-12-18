FROM openjdk:17-jdk-alpine

ADD build/distributions/registration-boot-*.zip /registration.zip

RUN unzip /registration.zip && \
  rm -rf *.zip && \
  mv /registration-* /registration
    
EXPOSE 80

ENTRYPOINT [ "/registration/bin/registration" ]
    