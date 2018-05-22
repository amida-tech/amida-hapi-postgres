# Amida-Hapi

This is a basic restful Hapi server using Hapi and set up against DSTU2.  

## Build

``./gradlew build``

## Build Docker

first build

``docker build -t amida-hapi .`` 

## Create an instance from the image
By default the image will list on 8087  with a root path of /fhir

``docker run --name my-hapi -p 8087:8087 amida-hapi``

you should then be able to go to 

http://localhost:8087/fhir/metadata?_format=json 

to pull down the metadata statement.  