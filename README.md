![logo](logo/Gen-Spring-Logo.jpg?raw=true) 

# generator-spring [![Build Status](https://travis-ci.org/davetownsend/generator-spring.svg?branch=master)](https://travis-ci.org/davetownsend/generator-spring)

A [Yeoman](http://yeoman.io) generator for scaffolding and bootstrapping [Spring Boot](http://projects.spring.io/spring-boot/) and [Spring Cloud](http://projects.spring.io/spring-cloud/) applications. Provides the same selectable options as [Spring Initializr](http://start.spring.io), but with and interactive CLI interface so your hands can stay where they belong, on the keyboard!


## Getting Started

**Install Yeoman**

```
$ npm install -g yo
```

**Install generator-spring-boot-microservice**

```
$ npm install -g generator-spring-boot-microservice
```

**Initiate spring-generator!**

```
$ yo spring-boot-microservice
```
_The interactive CLI menu will guide the way._


### Extras (Sub-generators)
Creates an Authorization Server

```
$ yo spring-boot-microservice:auth
```



## Release

```
1.  Increase the version number in package.json
2.  Commit code to repo
3.  npm publish
4.  yo and update your generator

```


## License

[MIT License](http://en.wikipedia.org/wiki/MIT_License)
