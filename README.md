# Weather Demo

> This is a SpringBoot Maven project with a REST service.

## Build Setup

``` bash
# generate jar package
mvn install

# start the service
cd target
java -jar demo-0.0.1-RELEASE.jar
```

## REST APIs

1. Get city options
http://host-address:8081/service/weather/cities

Response: 
{
    "1BABF": "Sydney",
    "754D9": "Melbourne",
    "F7F3": "Wollongong"
}

2. Get weather by cityId
http://localhost:8081/service/weather/get?cityId=

Response Example: 
{
    "cityId": "F7F3",
    "cityName": "Wollongong",
    "weather": "Rain",
    "temperature": 17.0,
    "windSpeed": "39",
    "updatedTime": "2021-11-26T07:00+11:00",
    "errorMsg": null
}


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.0/maven-plugin/reference/html/#build-image)

