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
   
## Code Coverage (Jacoco)

1. Run `mvn package` or `mvn clean install` to build, run tests and generate coverage report

2. Report location: `sls-server/target/site/jacoco/index.html`


## Run DynamoDB Seed

Please aware that the seeding will insert new records into table and not clear the existing data. If there is existing record with the same primary key, it will be replaced with seed data

1. Prepare/Update seed data in sls-server/database-seed, each json file store data for a table
2. Update the table names and seed file path in serverless.yml file: custom.seed.{seadName}
3. Run `sls dynamodb:seed -s ${stage}`.
Ex: `sls dynamodb:seed -s uat`