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
TODO

# Deployment
## Push external news app
To push the spring-music-news-external app run the following command from the 
*spring-music-news-external* directory:
```
cf push
```
If the application push fails because of the route already being taken - open the `manifest.yml` and modify the [host](https://github.com/nvvalchev/spring-music/blob/master/spring-music-news-external/manifest.yml#L5) entry.

## Prepare the deployment specific app configuration
Our MTA has some deployment specific configuration which we should additionally provide in a `*.mtaext` (MTA extension descriptor) file. In the root folder there are two such files prepared `config.mtaext` which consumes a database service and a lighter version - `config-trial.mtaext` which runs with in-memory database and could be deployed on SAP Cloud Foundry Trial without consuming service quota.

### If you have modified *spring-music-news-external*'s host

Inside the extension descriptor of your choice edit the spring-music-news-external's config [url](https://github.com/nvvalchev/spring-music/blob/master/config.mtaext#L23) so that it matches your custom host.

Before the change:
```
        url: https://spring-music-news-external-demo.${default-domain}/news
```
After the change:
```
        url: https://my-custom-hostname.${default-domain}/news
```

## Deployment via CF MTA deploy service
To deploy the MTA archive via [CF MTA deploy service](https://github.com/SAP/cf-mta-deploy-service) run the following command from the *root* directory:
```
cf deploy mta-assembly/spring-music.mtar -e config.mtaext
```
Or if you are deploying the lite version:
```
cf deploy mta-assembly/spring-music.mtar -e config-trial.mtaext
```