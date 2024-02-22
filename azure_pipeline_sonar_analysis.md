# Perform sonar analysis  for LMS Repo Using Azure Pipeline
## create Project
- goto your https://aex.dev.azure.com/
- create one project and select Git & Scrum
- goto Azure Repo and Import code

## Create token 

- goto your organization 
- click on **user settings** you will find in top right corner
- click on **Personal Access Token** and create new one
- PAT: utabolisuhmjekab2q2p4z62sxq3foopz6b442t2dn6bnq4sl2ia


## Launch VM and install sonarqube

- VM type: D2s_v3 [2 cpus, 8gb]
- username: murali
- password: murali12345@

## Installations
- install docker
- run sonar container
## Access SonarQube in Browser

- pub-ip:9000
- default username:password = admin:admin
- Set new password : admin123

## create project in sonarqube using Azure Pipeline

### Analyze your project with Azure DevOps Pipelines
- it will suggest you steps to do
- Goto your **Azure Devops portal**
- Install SonarQube extension for Azure DevOps
  - goto your azure devops org settings 
  - click on extentions -> browse marketplace
  - search for SonarQube
  - install it for your org
  - check in extentions, you will find sonarqube extention

### Add a new SonarQube Service Endpoint in your project
- In Azure DevOps, go to Project settings -> **Pipeline** -> **Service connections**.
- create new service connection -> SonarQube.
- Enter your SonarQube server url:http://172.190.107.3:9000

- **Note**: You need to generate one token in SonarQube
- Enter an existing token, or a newly generated one:Generate a token
- Enter a memorable connection name.
- save the connection
- you will see created service connection

- goto your sonarqube portal and select Configure Analysis [ .NET, **Maven**,.....]


### Azure pipeline setup

- goto Pipeline -> click on Create Pipeline
- select azure repo
- select starter pipeline

#### Part-1

- click on Show assistant and select prepare 
- Configuration task before your build task:
  - Select the SonarQube server endpoint you created in Step 2.
  - Under Choose the way to run the analysis, select **ntegrate with Maven or Gradle**.
  - In advanced Option Paste sonar key and project name
  ```
  # Additional properties that will be passed to the scanner,
  # Put one key=value per line, example:
  # sonar.exclusions=**/*.bin
  sonar.projectKey=lms-java_lms-java_9053f852-7773-4b9e-9946-b91a0a413e73
  sonar.projectName=lms-java
  ```
  - click on add

#### Part-2

- now type **Java tool installer** in Task assisstant
  - JDK Version: '17'
  - jdk Architecture: 'x64'
  - jdk Source: 'PreInstalled'
- click on add    

#### Part-3

- Now type **Maven** in Task assisstant
- maven Pom File: 'LMS-BE/pom.xml'
- goals: 'clean verify sonar:sonar'
- options: '-DskipTests'
- publish JUnit Results: true
- testResultsFiles: '**/surefire-reports/TEST-*.xml'
- test Run Title: 'maven scan'
- Advanced
  - Code Analysis
    - Run SonarQube or SonarCloud analysis
  - SonarQube scanner for Maven version
    - Use version declared in your pom.xml
- click on add  

- **save and run the pipeline**


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

