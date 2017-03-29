# Spring Points of Integration
This repository encapsulates what I covered during the [Arity](http://arity.com) Learn Day on Friday, March 3, 2017. Specifically, we experimented with the following Spring features:
- `ApplicationRunner`
- `HandlerMethodArgumentResolver`
- `@ExceptionHandler`
- `HandlerInterceptorAdapter`

## Our working example
This repository contains code for a RESTful API that allows a client to upload driving data in the form of a trip. Each trip is associated with one user. Each user has a client-provided id that is unique to the client (org), but cannot be considered globally unique.

## Running the application locally
To download and run the application on your local machine, complete the following steps:

#### Clone the respository
```
$ cd path/to/your/workspace
$ git clone git@github.com:christherama/spring-goodies.git
$ cd spring-goodies
```

#### Run the application
```
$ ./gradlew bootRun
```

#### View all users
Use a REST client or cURL to view all users:
```
$ curl -X GET "http://localhost:8080/users"

[{"id":"f96da254-15cd-460d-ba78-35738eddc462","orgId":"my-org","userId":"rama"}]
```

#### Add a trip
Use a REST client or cURL to add a trip:
```
$ curl -X POST -H "Content-Type: application/json" -H "Org-Id: my-org" -d '{"distance": 22.34,"duration": 1608}' "http://localhost:8080/users/rama/trips"

{"data":{"id":"8a9eb574-890d-40b4-9710-1348d40f579e","user":{"id":"f96da254-15cd-460d-ba78-35738eddc462","orgId":"my-org","userId":"rama"},"distance":22.34,"duration":1608}}
```

#### View all trips from a user
Use a REST client or cURL to add a trip:
```
$ curl -X GET -H "Org-Id: my-org" "http://localhost:8080/users/rama/trips"

{"data":[{"id":"ad3098cf-fbeb-42d7-960f-95273de234fe","user":{"id":"f96da254-15cd-460d-ba78-35738eddc462","orgId":"my-org","userId":"rama"},"distance":22.34,"duration":1608}]}
```

#### Add a user
```
$ curl -X POST -H "Content-Type: application/json" -H "Org-Id: my-org" -d '{"orgId": "my-org","userId": "yourname"}' "http://localhost:8080/users"

{data:{"id":"355f7bd9-80c3-4839-ae55-f273e7771c6f","orgId":"my-org","userId":"yourname"}}
```

## Using an `ApplicationRunner` to load initial data
You could use an *import.sql* file at the root of your classpath for initial data upon booting your application. This works if you're using Hibernate *and* Hibernate is creating your schema. However, why not exercise the logic of your application to persist entities?

To run a method at boot time, you can implement an `ApplicationRunner` and annotate it with `@Component`. Treat this just as you would any other component, autowiring any beans that you'll need at runtime. In [src/main/java/io/rama/DataLoader.java](https://github.com/christherama/spring-goodies/blob/master/src/main/java/io/rama/DataLoader.java), we use constructor autowiring to inject a `UserRepository` for saving an initial user. Notice the omission of the `@Autowired` annotation, since the component has only one constructor.

You'll see that the `DataLoader` component is annotated with `@Profile("local")`. When the "local" profile is active, the component will be loaded, and because it has implemented `ApplicationRunner`, its `run` method will be executed, thereby persisting a user. The inclusion of the `@Profile` annotation allows you to control whether or not initial data is loaded by setting the active profile accordingly.

## Resolving custom controller arguments with a `HandlerMethodArgumentResolver`
Controller methods in Spring are configured to understand certain argument types out of the box. Consider the following controller method:

```java
@RequestMapping(path = "/people/{id}")
public Person getThePerson(@PathVariable Long id) {
  // Implement the method (duh)
}
```

When Spring receives the request `GET /people/123`, Spring will know to call the `getThePerson` method as a result of the `@RequestMapping` annotation. But how does Spring provide a value for the `id` parameter?

Spring ships with built-in classes that implement the `HandlerMethodArgumentResolver` interface. This interface defines how controller methods resolve values for their defined parameters. For example, Spring uses the [PathVariableArgumentResolver](http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/mvc/method/annotation/PathVariableMethodArgumentResolver.html) to pass a value into any parameter annotated with `@PathVariable`.

To see which parameter types Spring supports out of the box, see the [supported method argument types](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc-ann-arguments).

In our application, let's say we want to tell Spring how to resolve a `User` argument, with the `orgId` provided as a request header, and the `userId` provided as a path parameter. If we are going to be doing this across many methods in our API, we might find it convenient to consolidate this logic into a single `HandlerMethodArgumentResolver`.

See [src/main/java/io/rama/web/request/UserHandlerMethodArgumentResolver.java](https://github.com/christherama/spring-goodies/blob/master/src/main/java/io/rama/web/request/UserHandlerMethodArgumentResolver.java) as an example.

Overall, you'll implement the `supportsParameter` method to return `true` when you want Spring to use a given `HandlerMethodArgumentResolver` to resolve a controller method argument. Then, you'll implement the `resolveArgument` method so that it returns the value that you want to pass into the controller method.

If you define a custom resolver, don't forget to tell Spring about it via a `WebMvcConfigurerAdapter` that's been annotated with `@Configuration`. As an example, see [src/main/java/io/rama/config/WebConfig.java](https://github.com/christherama/spring-goodies/blob/master/src/main/java/io/rama/config/WebConfig.java#L20).

## Handling application exceptions with `@ExceptionHandler` methods

## Pre- and post-request processing with a `HandlerInterceptorAdapter`