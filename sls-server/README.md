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

###### It's important to note that Serverless is only compatible with Java versions up to 11. If your Java version is higher than 11, please downgrade it.

1. Install and run in local

    ```bash
    serverless plugin install -n serverless-offline
    serverless plugin install -n serverless-dynamodb-seed
    ```

2. Build the package
   ```bash
   mvn clean package
   ```

3. Invoke function locally

    Serverless Offline will start a local server at http://localhost:3000 and connect to all resources in AWS of the given stage

    ```bash
    sls offline -stage=<environment_name> #e.g sls offline -stage=dev
    ```

## Build, Deploy and Cleanup

1. Build

    ```bash
    mvn clean package -Dstage=<environment_name> #e.g mvn clean package -Dstage=dev
    ```

2. Deploy

    ```bash
    serverless deploy -stage=<environment_name> #e.g serverless deploy -stage=dev
    ``

3. Cleanup
  
    ```bash
    serverless remove -stage=<environment_name> #e.g serverless remove -stage=dev
    ```

## Code Coverage (Jacoco)

1. Run `mvn package` or `mvn clean install` to build, run tests and generate coverage report

2. Report location: `sls-server/target/site/jacoco/index.html`

## Run DynamoDB Seed

Please aware that the seeding will insert new records into table and not clear the existing data. If there is existing record with the same primary key, it will be replaced with seed data

1. Prepare/Update seed data in sls-server/database-seed, each json file store data for a table
2. Update the table names and seed file path in serverless.yml file: custom.seed.{seadName}
3. Run `sls dynamodb:seed -s ${stage}`. Ex: `sls dynamodb:seed -s uat`
