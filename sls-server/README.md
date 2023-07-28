# Serverless Server for SCC

## Prerequisites

* Maven
* Serverless CLI
* AWS Account

## Setup

1. Install Serverless CLI

    ```bash
    npm install -g serverless
    ```

2. Install Maven

    ```bash
    sudo apt install maven
    ```

3. Install AWS CLI: <https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html>

    ```bash
    curl "https://awscli.amazonaws.com/AWSCLIV2.pkg" -o "AWSCLIV2.pkg"
    sudo installer -pkg AWSCLIV2.pkg -target /
    ```

4. Configure AWS CLI: <https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-files.html>

    ```bash
    aws configure
    ```

## Running in local

1. Install plugin

    ```bash
    serverless plugin install -n serverless-offline
    ```

2. Invoke function locally

    ```bash
    sls offline
    serverless offline
    ```

If there is error with message `java.lang.NoClassDefFoundError: Could not initialize class org.codehaus.groovy.vmplugin.v7.Java7`. Please try to install Java 8 and set JAVA_HOME to Java 8.

    ```bash
    > /usr/libexec/java_home -V
    Matching Java Virtual Machines (4):
    20.0.1 (x86_64) "Eclipse Adoptium" - "OpenJDK 20.0.1" /Library/Java/JavaVirtualMachines/temurin-20.jdk/Contents/Home
    18.0.2 (arm64) "Amazon.com Inc." - "Amazon Corretto 18" /Library/Java/JavaVirtualMachines/amazon-corretto-18.jdk/Contents/Home
    17.0.4.1 (x86_64) "Eclipse Adoptium" - "OpenJDK 17.0.4.1" /Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home
    1.8.0_292 (x86_64) "AdoptOpenJDK" - "AdoptOpenJDK 8" /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home
    /Library/Java/JavaVirtualMachines/temurin-20.jdk/Contents/Home
    
    > export JAVA_HOME=`/usr/libexec/java_home -v 1.8.0_292`
    ```

## Build and Deploy

1. Build

    ```bash
    mvn clean package
    mvn package
    ```

2. Deploy

    ```bash
    serverless deploy
    ```

3. Cleanup
  
    ```bash
    serverless remove
    ```

## Build and Deploy with stage (should apply in local only)

1. Change <version> in pom to any [stageName]
   ```
   <version>test</version>
   ```
2. Build

    ```bash
    mvn clean package
    mvn package
    ```

3. Deploy
deploy with -s (stage) variable that we used

    ```bash
    serverless deploy -s test
    ```

4. Cleanup

    ```bash
    serverless remove
    ```
