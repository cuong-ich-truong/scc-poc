# Security Command Center POC

POC for Security Command Center

## Project Structure

The project consists of 3 main folders:

- frontend
- sls-server
- mobile

Each folder contains the source code for the corresponding component. Instructions on how to run each component can be found in the README file in each folder as well.

## System Architecture Overview

### Overview

- Frontend: React built with vite
- Backend: AWS Lambda with Serverless Framework
- Mobile: Flutter
- CI/CD: Github Actions with Serverless Framework

### Assumptions

These are the assumptions made for this POC to simplify the implementation and ensure the smooth process of development.

- The system will only support a single organization with multiple premises.
- The system will only support a single user role with full access to all premises and cameras. (no authentication and authorization implemented)
- The alerts can be sent to all on-duty Security Guards. (no premises or camera subscription implemented)

## Components Breakdown

### Frontend

The frontend was built with React and hosted with Github Pages. 

*Potential improvements:*

- Host the frontend with a custom domain instead of Github Pages to have better dynamic routing support.
- Use server-side rendering to improve security and performance.
- Use web sockets to allow real-time updates instead of simple polling mechanism.
- UI enhancements such as views for Incidents and Alerts, ability to create new Incidents and Alerts manually through the Security Command Center UI

### Backend APIs

The backend was built with Serverless Framework using Java and deployed to AWS Lambda with simple architecture consists 2 layers:

- **Handlers Layer**: Classes that acting as the Controllers and handle API requests
- **Data Access Layer**: Classes that acting as the Models and handle data access to DynamoDB

AWS Lambda is a serverless compute service that allows us to run code without provisioning or managing servers. This approach allows us to focus on the business logic and implementation. Serverless also comes with many advantages such as low cost, scalable and zero downtime deployment. However, this approach also has some limitations such as the cold start issue and the lack of support for Java SDKs and libraries.

*Potential improvements:*

- Use a traditional server for full support for Java SDKs and libraries. This will also allow us to directly integrate with Firebase instead of using AWS SNS as a bridge.
- Or pick another language that has better support for AWS Lambda such as Javascript. This approach will also allow us to use the same language for both frontend and backend which will reduce the learning curve for developers and increase productivity.

### Database

The database was created using DynamoDB which provides the flexibility to store data as documents and also the ability to scale easily. Moving forward, we can also use DynamoDB Streams to trigger Lambda functions to perform additional processing on the data.

*Potential improvements:*

- Use a traditional SQL database for better support for complex queries and transactions.
- Switch to use a different NoSQL database such as MongoDB for better scalability and performance.

### Alert System

The Alert System was mocked using a simple endpoint that sends messages to the alert queue created using AWS SQS. The messages will then be consumed by a Lambda function that will process the messages and create new alerts in the database. The frontend will be polling the API to get the latest alerts notifications which will can be assigned to the Security Guards by the Operator.

*Potential improvements:*

- Use a self-hosted message queue such as RabbitMQ for better control or Kafka for better message processing and streaming performance.

### Mobile App

The mobile app was built with Flutter for cross platform support. The app is currently only available for Android but can be easily extended to support iOS as well.

*Potential improvements:*

- Enable authentication and authorization for the mobile app to allow users to login and access their own data (e.g assigned premises or camera )
- Improve UI/UX.
- Allow to select files from the device when sending notes.
- Support iOS users.
- Implement CI/CD system.

### CI/CD

The CI/CD was built with Github Actions and Serverless Framework. The CI/CD pipeline consists 2 workflows:

- Frontend Check: will be triggered on every pull request to the `main` branch and will run build check as well as the unit tests for the frontend.
- Front Deployment: will be triggered on every push to the `main` branch and will deploy the frontend to Github Pages.

Deployment of the backend is done manually using Serverless Framework with one single command `sls deploy -stage <environment_name>`. Serverless Framework will then create a CloudFormation stack and deploy the Lambda functions along side with all the required resources such as DynamoDB tables, SQS queues, SNS topics, etc. This approach is chosen to allow better control over the deployment process following the Infrastructure as Code practices. This approach also allows each developer to have control on their own fully functional environment which will help avoid conflicts and improve productivity.

*Potential improvements:*

- Create a separate CI/CD pipeline using Github Actions for the backend to allow fully automated deployment.

## C4 Models

### Context

![Alt text](./Security Command Center Context Diagram.svg)
<img src="./out/docs/scc-context/Security Command Center Context Diagram.svg">

### Container

![Alt text](./Security Command Center Container Diagram.svg)
<img src="./out/docs/scc-container/Security Command Center Container Diagram.svg">
