# circleCi-practice

This project is only with the goal to study about circleCi. Due to CICD needs test to run, in this project, there are 4 test suites.
These tests are related with fixtures in Scala. This project has 4 ways to use Fixtures.

See [TestSuit1](https://github.com/1304julian97/circleCi-practice/blob/master/src/test/scala/com/practice/circleci/TestSuit1.scala)

See [TestSuit2](https://github.com/1304julian97/circleCi-practice/blob/master/src/test/scala/com/practice/circleci/TestSuit2.scala)

See [TestSuit3](https://github.com/1304julian97/circleCi-practice/blob/master/src/test/scala/com/practice/circleci/TestSuit3.scala)

See [TestSuit4](https://github.com/1304julian97/circleCi-practice/blob/master/src/test/scala/com/practice/circleci/TestSuit4.scala)

In addition to that. You can see [circleci file](https://github.com/1304julian97/circleCi-practice/blob/master/.circleci/config.yml).
This one will have a **_basic_** configuration to know how you can set up this file. I have **WorkFlows**, **Filters** and other things.

#Prerequisites - Dependencies
* Circle ci installed in your local machine
* In your source project you need to have created folder called **_.circleci_**
* Into the folder **_.circleci_** is required the file **_config.yml_**
* Your account created in [CircleCi.com](https://circleci.com/)




## Run Specific Job in CircleCi

      circleci local execute --job <JobName>


## Validate **_config.yml_**

* To validate if the file _config.yml_ is worote correctlly only run this:
  
      circleci config validate


    
    
## Use the same docker image at different jobs

* Read this => [Click here](https://support.circleci.com/hc/en-us/articles/360019182513-Build-Docker-image-in-one-job-and-use-in-another-job)