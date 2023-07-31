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

1. Install and run DynamoDB in local

    ```bash
    serverless plugin install -n serverless-offline
    ```
    
2. Invoke function locally

    ```bash
    sls offline
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