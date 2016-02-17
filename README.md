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

# Using Spring Integration

Spring Integration framework is configured for the application using a Spring XML
configuration file.  This file `springIntegrationContext.xml` can be found under
the `src/main/resources` folder.  It has the following content:

    1.  <int:channel id="receiveChannel"/>
    2.
    3.  <http:inbound-channel-adapter id='restInputAdapter'
    4.                                channel='receiveChannel'
    5.                                path='/spring/person'
    6.                                request-payload-type="org.example.domain.Person"
    7.                                supported-methods='POST'>
    8.  </http:inbound-channel-adapter>
    9.
    10. <jdbc:outbound-channel-adapter id='jdbcOutputAdapter'
    11.                               channel='receiveChannel'
    12.                               data-source='dataSource'
    13.                               query='INSERT INTO "person" ("first_name", "last_name", "email_address") VALUES (:payload.firstName, :payload.lastName, :payload.emailAddress)'/>

* The configuration starts by declaring a messaging channel named `receiveChannel` (line 1).
* Next, an HTTP endpoint is configured to listen to HTTP `POST` requests at the URL `/spring/person` (lines 3-8).  This endpoint converts request data to objects of type `org.example.domain.Person` and then publishes those objects on the messaging channel configured above.
* Finally, a JDBC endpoint is configured to pick data from the messaging channel and insert it into a database table named `person` (lines 10-13).  This endpoint uses a JDBC data source named `dataSource` that is declared elsewhere in the applicaiton.

# Using Apache Camel

Apache Camel configuration is lengthier as compared to the Spring Integration configuration.
Camel allows REST semantics to be configured in many different ways.  For this sample,
REST support is enabled through the Restlet library supported by Camel.

Configuration starts with an XML file - `springCamelContext.xml` that can also be found under
the `src/main/resources` folder.  This file has the following content:

    1.  <bean id="restletComponent" class="org.restlet.Component"/>
    2.
    3.  <bean id="restletComponentService" class="org.apache.camel.component.restlet.RestletComponent">
    4.    <constructor-arg ref="restletComponent"/>
    5.  </bean>
    6.
    7.  <camelContext xmlns="http://camel.apache.org/schema/spring">
    8.    <restConfiguration bindingMode="auto" component="restlet"/>
    9.
    10.   <rest>
    11.     <post outType="org.example.domain.Person" type="org.example.domain.Person" uri="/person">
    12.       <to uri="direct:persons"/>
    13.     </post>
    14.   </rest>
    15.
    16.   <route>
    17.     <from uri="direct:persons"/>
    18.     <setBody>
    19.       <simple>INSERT INTO "person" ("first_name", "last_name", "email_address") VALUES ('${body.firstName}', '${body.lastName}', '${body.emailAddress}')</simple>
    20.     </setBody>
    21.     <to uri="jdbc:dataSource"/>
    22.   </route>
    23. </camelContext>

* To start with, a Restlet bean named `restletComponent` is declared (line 1).
* Restlet support is initialised by instantiating another Restlet bean (lines 3-5).
* Camel runtime is instructed to use Restlet as the REST framework (line 8).  At the same time, the Camel runtime is also instructed to automatically convert incoming requests to Java objects (`binding="auto"`).
* A REST endpoint is configured to listen to HTTP `POST` requests at the URL `/person` relative to the base URL configured for Camel (lines 10-14).  At the same time, Camel is instructed to convert request data to objects of the type `org.example.domain.Person`.
* Finally, a JDBC query is configured to insert data to the `person` table (lines 16-22).  This configuration also uses the JDBC data source named `dataSource`.

Camel support also requires the following configuration in the `web.xml` file:

    <servlet>
      <servlet-name>restlet-spring-server</servlet-name>
      <servlet-class>org.restlet.ext.spring.SpringServerServlet</servlet-class>
      <init-param>
        <param-name>org.restlet.component</param-name>
        <param-value>restletComponent</param-value>
      </init-param>
    </servlet>
    <servlet-mapping>
      <servlet-name>restlet-spring-server</servlet-name>
      <url-pattern>/camel/*</url-pattern>
    </servlet-mapping>

This configures a Restlet servlet for listening to requests on URLs of the form `/camel/...`.
The servlet is configured to pass all incoming requests to a Restlet component named
`restletComponent` (configured previously in `springCamelContext.xml`).

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
