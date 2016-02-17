[![Build Status](https://drone.io/github.com/manish-in-java/spring-eai/status.png)](https://drone.io/github.com/manish-in-java/spring-eai/latest)
[![Custom License](http://b.repl.ca/v1/License-CUSTOM-red.png)](#LICENSE)
[![Spring WebMVC Application](http://b.repl.ca/v1/spring-mvc-blue.png)](#SWMVC)

# Introduction

This sample application demonstrates integrating an Spring MVC application with
external systems.  This is a common requirement where an existing application
either needs to accept data from or needs to provide data to one or more external
systems.

This application demonstrates a simple integration scenario where the application
accepts data from external actors over a REST endpoint.  The data can be provided
either in JSON or XML format.  The application simply saves the data to a
relational database table from where is can be retrieved for viewing.  There is
no other business logic in the flow.

Two integration frameworks are tested:

1. [Spring Integration](http://projects.spring.io/spring-integration/) is an extension to the Spring framework that supports an XML based syntax for integration with external systems.
1. [Apache Camel](http://camel.apache.org) is an integration framework that supports an XML based syntax as well as fluent APIs for Java and Scala for integration with external systems.

# Running the sample

This application uses an embedded Tomcat server that can be run as `mvn tomcat7:run`.
This command starts Tomcat on [http://localhost:8080](http://localhost:8080).

Once the application has started, open a web browser and navigate to
[http://localhost:8080](http://localhost:8080) to get to the application home page.
The home page has instructions on how to proceed and the actions that can be
performed.

Alternatively, try this sample out on [Google App Engine](http://spring-eai.appspot.com).

# License

This sample application and its associated source code in its entirety is being made
available under the following licensing terms.

    Copyright (C) 2014

    Permission is hereby granted, free of charge, to any person obtaining a copy of
    this software and associated documentation files (the "Software"), to deal in the
    Software without restriction, including without limitation the rights to use, copy,
    modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
    and to permit persons to whom the Software is furnished to do so, subject to the
    following conditions:

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
    INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
    PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
    HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
    CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
    OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
