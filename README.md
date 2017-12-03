# spring-pets

This java based application consumes a JSON web service located on the following URL

> http://agl-developer-test.azurewebsites.net/people.json

Outputs a list of all the cats in alphabetical order under a heading of the value of their owner.

To run the application you have to execute the following command

> If gradle is installed on the machine
> `gradle clean bootRun`

If gradle is not installed 

> In Windows
> `gradlew.bat clean bootRun`

> In Linux or Mac OSX
> `./gradlew clean bootRun`

# features

 - This application exposes some monitoring API using `spring-boot-actuator`. 
 - I have also used AOP to handle exceptions in a generic way using Throws Advice. 
 - To make the application perform faster I have made use of asynchronous programming using `spring-boot-async`

# frameworks
- java 8
- spring-boot
- spring-boot-async
- spring-boot-actuator
- spring-boot-aop

# testing

The application has test cases which can be executed using following command

> If gradle is installed on the machine
> `gradle test`

If gradle is not installed 

> In Windows
> `gradlew.bat test`

> In Linux or Mac OSX
> `./gradlew test`

# code review and code coverage

PMD is used to automate the code review and can be executed using the following command

> If gradle is installed on the machine
> `gradle pmdMain`

If gradle is not installed 

> In Windows
> `gradlew.bat pmdMain`

> In Linux or Mac OSX
> `./gradlew pmdMain`
