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

# Deployment via CF MTA deploy service
To deploy the MTA archive, built in the previous step, via [CF MTA deploy service](https://github.com/SAP/cf-mta-deploy-service) run the following command from the root directory:
```
cf deploy mta-assembly/spring-music.mtar
```
