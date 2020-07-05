# circleCi-practice

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