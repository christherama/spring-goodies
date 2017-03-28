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

## Resolving custom controller arguments with a `HandlerMethodArgumentResolver`

## Handling application exceptions with `@ExceptionHandler` methods

## Pre- and post-request processing with a `HandlerInterceptorAdapter`