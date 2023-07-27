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
    npm install serverless-dynamodb
    serverless dynamodb install
    serverless dynamodb start
    ```
    
2. Invoke function locally

    ```bash
    serverless invoke local --function hello
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