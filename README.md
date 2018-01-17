Spring Music
============

This is a fork of the [sping-music](https://github.com/scottfrederick/spring-music) sample application for [Cloud Foundry](http://cloudfoundry.org), which extended and  adapted to the [Multi-Target Application (MTA)](https://www.sap.com/documents/2016/06/e2f618e4-757c-0010-82c7-eda71af511fa.html) model and could be deployed by the [CF MTA deploy service](https://github.com/SAP/cf-mta-deploy-service).

# Components
## spring-music
This is the original [spring-music](https://github.com/scottfrederick/spring-music) application, which is contained in the root of the project. 

## spring-music-news
This is a Node.js server app that just returns a static JSON with some music news. 

## spring-music-news-external
This is a Node.js server app that just returns a static JSON with some music news. 

# MTA model adoption
TODO

# Building
## Prerequisites
TODO

## Compiling and Packaging
TODO
## Building MTA archive
DODO

# Deployment
## Push external news app
To push the spring-music-news-external app run the following command from the 
*spring-music-news-external* directory:
```
cf push
```
## Prepare the deployment specific app configuration
Our MTA has some deployment specific configuration which we should additionally provide in a *config.mtaext* (MTA extension descritpro) file. To do that first we need to find to URL of our spring-music-news-external app, pushed in the previous step via the following command:
```
cf a
```
which should produce the following output:
```
$ cf a
Getting apps in org deploy-service / space i069874 as i069874...
OK

name                         requested state   instances   memory   disk   urls
spring-music-news-external   started           1/1         128M     1G     i069874-spring-music-news-external.cfapps.sap.hana.ondemand.com
```
The app URL value should be set as value of the *url* parameter of the config.mtaext file.
## Deployment via CF MTA deploy service
To deploy the MTA archive via [CF MTA deploy service](https://github.com/SAP/cf-mta-deploy-service) run the following command from the *root* directory:
```
cf deploy mta-assembly/spring-music.mtar -e config.mtaext
```
