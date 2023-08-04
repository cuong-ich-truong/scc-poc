```plantuml
@startuml Security Command Center Container Diagram
    ' https://github.com/plantuml-stdlib/C4-PlantUML#including-the-c4-plantuml-library
    !include <c4/C4_Context.puml>
    !include https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Container.puml

    ' import AWS icon
    !define AWSPuml https://raw.githubusercontent.com/awslabs/aws-icons-for-plantuml/v16.0/dist
    !include AWSPuml/AWSCommon.puml
    !include AWSPuml/Database/DynamoDB.puml
    !include AWSPuml/Compute/Lambda.puml
    !include AWSPuml/General/Client.puml
    !include AWSPuml/ApplicationIntegration/SimpleQueueService.puml
    !include AWSPuml/ApplicationIntegration/SimpleNotificationService.puml

    
    LAYOUT_WITH_LEGEND()
    LAYOUT_LEFT_RIGHT()

    title Security Command Center Container Diagram

    Person(operator, "Operator", "Operator works in the operations center")
    Person(guard, "Security Guard", "Security Guard patrols the facility")

    System_Ext(alertSys, "Alert System", "Raises Alerts When Abnormalities Occur", "System")
    System_Ext(cctv, "CCTV", "Provides Video Feed", "System")
    System_Ext(firebase, "Firebase", "Send Notifications", "Firebase")

    Boundary(system, "Security Command Center", "System") {  
      Lambda(backend, "Backend", "Lambda","Handles API Requests")
      Container(frontend, "Web Application", "React", "Allows users to monitor and respond to alerts")
      Container(phone, "Phone", "Flutter", "Mobile App")
      DynamoDB(db, "Database", "DynamoDB","Stores Data")
      SimpleQueueService(sqs, "Message Queue", "SQS", "Queues Alerts")
      SimpleNotificationService(sns, "Notification Service", "SNS", "Sends Notifications")
    }

    BiRel_Up(backend, db, "Reads/Writes Data")
    BiRel(backend,frontend, "Sends/Receives API Requests")
    Rel(cctv, frontend, "Sends Video Feed")
    Rel(alertSys, sqs, "Sends Alerts")
    Rel(sqs, backend, "Dispatch Alerts")
    Rel(backend, sns, "Publish Messages")
    Rel_Up(sns, firebase, "Sends Messages")
    Rel(firebase, phone, "Pushes Notifications")
    BiRel(frontend, phone, "Sends Alert Notifications/Receives Incident Reports")

    Rel(operator, frontend, "Monitor CCTV, View/Assign Alerts")
    Rel(guard, phone, "Acknowledge Alerts, Report Incidents")   
@enduml
```
