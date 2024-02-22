Stories 1  : Perform sonar analysis  for LMS Repo 


# create Project


# Create token 


- PAT: utabolisuhmjekab2q2p4z62sxq3foopz6b442t2dn6bnq4sl2ia


# Launch VM and install sonarqube

- VM type: D2s_v3 [2 cpus, 8gb]
- username: murali
- password: murali12345@

- run sonar container

- password: admin123


## create project in sonarqube using Azure Pipeline

### Analyze your project with Azure DevOps Pipelines
- Install SonarQube extension for Azure DevOps
  - goto your azure devops org settings 
  - click on extentions -> browse marketplace
  - search for SonarQube
  - install it for your org
  - check in extentions, you will find sonarqube extention



### Add a new SonarQube Service Endpoint in your project
- In Azure DevOps, go to Project settings > Pipeline ->Service connections.
- create new service connection -> SonarQube.
- Enter your SonarQube server url:http://172.190.107.3:9000

- Enter an existing token, or a newly generated one:Generate a token
- Enter a memorable connection name.
- save the connection
- you will see created service connection

- goto your sonarqube portal and select Configure Analysis [ .NET, Maven,.....]


### Azure pipeline setup

- goto Pipeline -> click on Create Pipeline
- select azure repo
- select starter pipeline

#### Part-1

- click on Show assistant and select prepare 
- Configuration task before your build task:
  - Select the SonarQube server endpoint you created in Step 2.
  - Under Choose the way to run the analysis,      select Use standalone scanner.
  - Select the Manually provide configuration mode.
  - In the
Project Key
field, enter
sonar_sonar_c4487fbe-4cb1-42f2-ae24-f3dc0645a4af

#### Part-2

- Add a new Run Code Analysis task after your build task.

#### Part-3

- Add a new Publish Quality Gate Result task to  publish SonarQube's Quality Gate results on your build pipeline summary.

- Under the Triggers tab of your pipeline, check Enable continuous integration and select the main branch

- save and run the pipeline


## Pipeline Script
```
# Starter pipeline
# Start with a minimal pipeline that you can customize to build and deploy your code.
# Add steps that build, run tests, deploy, and more:
# https://aka.ms/yaml

trigger:
- minikube-1

pool:
  vmImage: ubuntu-latest

steps:
- task: SonarQubePrepare@5
  inputs:
    SonarQube: 'lms-java-sonar'
    scannerMode: 'Other'
    extraProperties: |
      # Additional properties that will be passed to the scanner,
      # Put one key=value per line, example:
      # sonar.exclusions=**/*.bin
      sonar.projectKey=lms-java_lms-java_9053f852-7773-4b9e-9946-b91a0a413e73
      sonar.projectName=lms-java
- task: JavaToolInstaller@0
  inputs:
    versionSpec: '17'
    jdkArchitectureOption: 'x64'
    jdkSourceOption: 'PreInstalled'
- task: Maven@4
  inputs:
    mavenPomFile: 'LMS-BE/pom.xml'
    goals: 'clean verify sonar:sonar'
    options: '-DskipTests'
    publishJUnitResults: true
    testResultsFiles: '**/surefire-reports/TEST-*.xml'
    testRunTitle: 'maven scan'
    javaHomeOption: 'JDKVersion'
    mavenVersionOption: 'Default'
    mavenAuthenticateFeed: false
    effectivePomSkip: false
    sonarQubeRunAnalysis: true
    sqMavenPluginVersionChoice: 'pom'
```

